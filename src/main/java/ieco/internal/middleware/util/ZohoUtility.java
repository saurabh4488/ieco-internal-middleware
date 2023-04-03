package ieco.internal.middleware.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import ieco.internal.middleware.domain.response.ZohoTicketCache;
import ieco.internal.middleware.domain.response.ZohoTokenGenResponse;
import ieco.internal.middleware.feignclient.RedisCacheClient;
import ieco.internal.middleware.feignclient.ZohoTokenGenClient;
import ieco.internal.middleware.model.ZohoTokenDetails;
import ieco.internal.middleware.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ZohoUtility {

    @Autowired
    private ZohoTokenGenClient tokenClient;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private RedisCacheClient redisCacheClient;
    @Autowired
    private ObjectMapper objectMapper;

    static boolean findDifference(String start_date, String end_date) {

        boolean isExpired = false;
// SimpleDateFormat converts the
// string format to date object
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

// Try Class
        try {

// parse method is used to parse
// the text from a string to
// produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

// Calucalte time difference
// in milliseconds
            long difference_In_Time = d2.getTime() - d1.getTime();

// Calucalte time difference in seconds,
// minutes, hours, years, and days
            long difference_In_Seconds = TimeUnit.MILLISECONDS.toSeconds(difference_In_Time) % 60;

            long difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time) % 60;

            long difference_In_Hours = TimeUnit.MILLISECONDS.toHours(difference_In_Time) % 24;

            long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time) % 365;

            long difference_In_Years = TimeUnit.MILLISECONDS.toDays(difference_In_Time) / 365l;

// Print the date difference in
// years, in days, in hours, in
// minutes, and in seconds
            log.info("Difference between two dates is: ");
// Print result
            System.out.println(difference_In_Years + " years, " + difference_In_Days + " days, " + difference_In_Hours + " hours, " + difference_In_Minutes + " minutes, " + difference_In_Seconds + " seconds"); //NOSONAR
//compare the two user input
            int result = Long.compare(difference_In_Hours, 1);

            // interpret the result
            if (result == 0) {
                log.info("They are equal");
                isExpired = true;
            } else if (result > 0) {
                log.info("First Value is greater than the second");
                isExpired = true;
            } else {
                log.info("First Value is less than the second");
                isExpired = false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isExpired;
    }


    public String getToken() {
        try {
            String s = (String) redisCacheClient.getObjectFromCache("zoho_map", "Zoho_Oauth_token");
            if (StringUtils.isNotEmpty(s)) {
                log.info("Token presented in cache {}", s);
                return s.trim();
            } else {
                log.info("Token not presented in cache");
                return invokeTokenApi(true);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    String invokeTokenApi(boolean isCache) {
        ZohoTokenGenResponse tokenRes;
        log.info("Zoho Token api calling {}");
        ResponseEntity<ZohoTokenGenResponse> tokenResentity = tokenClient.generateAccessToken();
        log.info("zoho token response [tokenResentity]: {}", tokenResentity.getBody());
        if (tokenResentity.getStatusCodeValue() == 200) {
            tokenRes = tokenResentity.getBody();
            if (!StringUtils.isEmpty(tokenRes.getAccess_token()) && StringUtils.isEmpty(tokenRes.getError())) {
                if (isCache) {
                    try {
                        redisCacheClient.putObjectInCacheWithMapNameAndKeyWithParamWithEvictTime("zoho_map", "Zoho_Oauth_token", tokenRes.getAccess_token(), tokenRes.getExpires_in() - 50, TimeUnit.SECONDS);
                        log.info("Zoho token stored in cache");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ZohoTokenDetails tokenDetailsModel = ZohoTokenDetails.builder().createdTime(new Date())
                            .token(tokenRes.getAccess_token()).expiresIn(tokenRes.getExpires_in()).build();
                    log.info("Zoho token stored in db");
                    tokenRepository.save(tokenDetailsModel);
                }
                return tokenRes.getAccess_token();
            }
        }
        return null;
    }

    public void storeTicketDetailsInCache(ZohoTicketCache ticketCache) {

        try {
            LocalDate localDate = LocalDate.now();
            LocalDateTime endOfDay = LocalDateTime.of(localDate, LocalTime.MAX);
            log.info("--EndDay--" + endOfDay);
            long endSeconds = Duration.between(endOfDay.withSecond(0).withMinute(0).withHour(0), endOfDay).getSeconds();
            log.info("endSeconds...{}", endSeconds);
            LocalDateTime date = LocalDateTime.now();
            log.info("--StartDay--" + date);
            long startSeconds = Duration.between(date.withSecond(0).withMinute(0).withHour(0), date).getSeconds();
            log.info("startSeconds {}", startSeconds);
            Integer timeToStore = (int) (endSeconds - startSeconds);

            redisCacheClient.putObjectInCacheWithEvictTime("ticket_cache_map", ticketCache.getCustomerId(), ticketCache, timeToStore, TimeUnit.SECONDS);

            log.info("Zoho ticket details has been saved in cache");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ZohoTicketCache checkForZohoTicketCache(String customerId) {
        try {
            log.info("customerId {}", customerId);
            Object ticketCacheVal = redisCacheClient.getObjectFromCache("ticket_cache_map", customerId);
            String ticketCacheValJson = objectMapper.writeValueAsString(ticketCacheVal);
            ZohoTicketCache ticketCache = objectMapper.readValue(ticketCacheValJson, ZohoTicketCache.class);
            return ticketCache;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}