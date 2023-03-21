package ieco.internal.middleware.util;

import ieco.internal.middleware.enums.PatternType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;

import static ieco.internal.middleware.util.AppConstant.VALID_EMAIL_ADDRESS_REGEX;

/**
 * The type Common utils.
 *
 * @author "PwC"
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    // initialize a Random object somewhere; you should only need one
    private static Random random = new SecureRandom();

    /*
     * Due to fortify issue added securedRandom
     */
    private static SecureRandom secureRandom = new SecureRandom();

    /**
     * Create or update transaction data customer transaction details response.
     *
     * @param status            the status
     * @param transactionTypeId the transaction type id
     * @param transactionId     the transaction id
     * @return the customer transaction details response
     */
	/*public static CustomerTransactionDetailsResponse createOrUpdateTransactionData(CommonStatusEnum status,
			Integer transactionTypeId, String transactionId) {
		return CustomerTransactionDetailsResponse.builder().status(status.getValue())
				.transactionId(new NullCheck<>(transactionId).isNull() ? UUID.randomUUID().toString() : transactionId)
				.transTypeId(transactionTypeId).build();
	}*/

    /**
     * Decrypt front end password string.
     *
     * @param encryptedText the encrypted text
     * @return the string
     */
   /* public static String decryptFrontEndPassword(String encryptedText) {
        LOGGER.info("(decryptFrontEndPassword) Decrypt Front End Password started with Encrypted Text: {}",
                encryptedText);
        if (StringUtils.isEmpty(encryptedText)) {
            return encryptedText;
        }
        Security.addProvider(new BouncyCastleProvider());
        String text = null;
        try (PemReader pemReader = new PemReader(
                new InputStreamReader(new ClassPathResource("security/private_key.pem").getInputStream()))) {
            KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
            byte[] content = pemReader.readPemObject().getContent();
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(content);
            PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
            final Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");// "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            text = new String(cipherText, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error(
                    "(decryptFrontEndPassword) Decrypt Front End Password started with Encrypted Text: {} has error: {}",
                    encryptedText, e.getMessage());
        }
        LOGGER.info("(decryptFrontEndPassword) Decrypt Front End Password ended with Encrypted Text: {}",
                encryptedText);
        return text;
    }*/

    /**
     * Gets date by minutes.
     *
     * @param minutes the minutes
     * @return the date by minutes
     */
    public static Date getDateByMinutes(int minutes) {
        return new Date(System.currentTimeMillis() + minutes * 60 * 1000);
    }

    /**
     * Validate pattern boolean.
     *
     * @param matcherString the matcher string
     * @param patternType   the pattern type
     * @return the boolean
     */
    public static boolean validatePattern(String matcherString, PatternType patternType) {
        if (patternType == PatternType.EMAIL_ADDRESS) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(matcherString);
            return matcher.find();
        } else if (patternType == PatternType.DATE) {
            SimpleDateFormat sdfrmt = new SimpleDateFormat(AppConstant.COMMON_DATE_FORMAT);
            sdfrmt.setLenient(false);
            try {
                sdfrmt.parse(matcherString);
                return true;
            }
            /* Date format is invalid */ catch (ParseException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Gets local date from clock.
     *
     * @return the local date from clock
     */
    public static LocalDate getLocalDateFromClock() {
        return LocalDate.now();
    }

    /**
     * Gets next day.
     *
     * @param localDate the local date
     * @return the next day
     */
    public static LocalDate getNextDay(LocalDate localDate) {
        return localDate.plusDays(1);
    }

    /**
     * Gets previous day.
     *
     * @param localDate the local date
     * @return the previous day
     */
    public static LocalDate getPreviousDay(LocalDate localDate) {
        return localDate.minus(1, ChronoUnit.DAYS);
    }

    /**
     * Gets day of week.
     *
     * @param localDate the local date
     * @return the day of week
     */
    public static DayOfWeek getDayOfWeek(LocalDate localDate) {
        return localDate.getDayOfWeek();
    }

    /**
     * Gets first day of month.
     *
     * @return the first day of month
     */
    public static LocalDate getFirstDayOfMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * Gets start of day.
     *
     * @param localDate the local date
     * @return the start of day
     */
    public static LocalDateTime getStartOfDay(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    /**
     * Print current day month and year string.
     *
     * @return the string
     */
    public static String printCurrentDayMonthAndYear() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        return String.format("Year : %d Month : %d day : %d \t %n", year, month, day);
    }

    /**
     * Check date equals boolean.
     *
     * @param date  the date
     * @param today the today
     * @return the boolean
     */
    public static boolean checkDateEquals(LocalDate date, LocalDate today) {
        return date.equals(today);
    }

    /**
     * Gets current time.
     *
     * @return the current time
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * Add hours to time local time.
     *
     * @param hours the hours
     * @return the local time
     */
    public static LocalTime addHoursToTime(int hours) {
        LocalTime time = LocalTime.now();
        return time.plusHours(hours);
    }

    /**
     * Time zone zoned date time.
     *
     * @param timeZone the time zone
     * @return the zoned date time
     */
    public static ZonedDateTime timeZone(String timeZone) {
        ZoneId america = ZoneId.of(timeZone);
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        return ZonedDateTime.of(localtDateAndTime, america);
    }

    /**
     * Check leap year boolean.
     *
     * @return the boolean
     */
    public static boolean checkLeapYear() {
        LocalDate today = LocalDate.now();
        return today.isLeapYear();
    }

    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public static Instant getTimeStamp() {
        return Instant.now();
    }

    /**
     * Gets local time using factory of method.
     *
     * @param hour    the hour
     * @param min     the min
     * @param seconds the seconds
     * @return the local time using factory of method
     */
    public static LocalTime getLocalTimeUsingFactoryOfMethod(int hour, int min, int seconds) {
        return LocalTime.of(hour, min, seconds);
    }

    /**
     * Gets zoned date time.
     *
     * @param localDateTime the local date time
     * @param zoneId        the zone id
     * @return the zoned date time
     */
    public static ZonedDateTime getZonedDateTime(LocalDateTime localDateTime, ZoneId zoneId) {
        return ZonedDateTime.of(localDateTime, zoneId);
    }

    /**
     * Modify dates local time.
     *
     * @param localTime the local time
     * @param duration  the duration
     * @return the local time
     */
    public static LocalTime modifyDates(LocalTime localTime, Duration duration) {
        return localTime.plus(duration);
    }

    /**
     * Gets difference between dates.
     *
     * @param localTime1 the local time 1
     * @param localTime2 the local time 2
     * @return the difference between dates
     */
    public static Duration getDifferenceBetweenDates(LocalTime localTime1, LocalTime localTime2) {
        return Duration.between(localTime1, localTime2);
    }

    /**
     * Gets local date time using parse method.
     *
     * @param representation the representation
     * @return the local date time using parse method
     */
    public static LocalDateTime getLocalDateTimeUsingParseMethod(String representation) {
        return LocalDateTime.parse(representation);
    }

    /**
     * Convert date to local date local date time.
     *
     * @param date the date
     * @return the local date time
     */
    public static LocalDateTime convertDateToLocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Convert date to local date local date time.
     *
     * @param calendar the calendar
     * @return the local date time
     */
    public static LocalDateTime convertDateToLocalDate(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Gets string to date.
     *
     * @param dateStr the date str
     * @return the string to date
     */
    public static Date getStringToDate(String dateStr) {
        Date d = null;
        SimpleDateFormat dateFormat = null;
        try {
            if (dateStr != null) {
                dateFormat = new SimpleDateFormat(AppConstant.COMMON_DATE_FORMAT);
                d = dateFormat.parse(dateStr);
            }
        } catch (Exception e) {
            LOGGER.info("error in parsing dateStr [ {} ] , format [ {} ] , msg: {}", dateStr,
                    AppConstant.COMMON_DATE_FORMAT, e.getCause());
        }
        return d;
    }

    /**
     * Gets difference date.
     *
     * @param d2 the d 2
     * @param d1 the d 1
     * @return the difference date
     */
    public static int getDifferenceDate(Date d2, Date d1) {
        int day = 0;
        if (null != d2 && null != d1) {
            day = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        }
        return day;
    }

    /**
     * Gets formatted date.
     *
     * @param d the d
     * @return the formatted date
     */
    public static String getFormattedDate(Date d) {
        try {
            return new SimpleDateFormat(AppConstant.COMMON_DATE_FORMAT).format(d);
        } catch (Exception e) {
            LOGGER.error("exception occurred while formatting date, msg : {}", e.getMessage());
        }
        return null;
    }

    /**
     * Gets latest date.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return the latest date
     */
    public static String getLatestDate(String date1, String date2) {
        try {
            if (new NullCheck<>(date1).isNotNullOrEmpty() && new NullCheck<>(date2).isNotNullOrEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat(AppConstant.COMMON_DATE_FORMAT);
                LocalDate appStatusLocalDate = convertToLocalDate(sdf.parse(date1));
                LocalDate appModifiedLocalDate = convertToLocalDate(sdf.parse(date2));
                if (appStatusLocalDate.isAfter(appModifiedLocalDate)) {
                    return date1;
                } else if (appStatusLocalDate.isBefore(appModifiedLocalDate)) {
                    return date2;
                } else if (appStatusLocalDate.isEqual(appModifiedLocalDate)) {
                    return date1;
                }
            } else if (!new NullCheck<>(date1).isNotNullOrEmpty() && new NullCheck<>(date2).isNotNullOrEmpty()) {
                return date2;
            } else if (new NullCheck<>(date1).isNotNullOrEmpty() && !new NullCheck<>(date2).isNotNullOrEmpty()) {
                return date1;
            }
        } catch (Exception e) {
            LOGGER.warn("Date parse error handled, where date1: {} and date2: {}", date1, date2);
        }
        return null;
    }

    /**
     * Convert to local date local date.
     *
     * @param dateToConvert the date to convert
     * @return the local date
     */
    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Convert date to string.
     *
     * @param date the date
     * @return the string
     */
    public static String convertDateToString(Date date) {
        if (new NullCheck<>(date).isNotNull()) {
            SimpleDateFormat sdf = new SimpleDateFormat(AppConstant.COMMON_DATE_FORMAT);
            return sdf.format(date);
        }
        return null;
    }

    /**
     * Convert date to string.
     *
     * @param date the date
     * @return the string
     */
    public static String convertSolicitDateToString(Date date) {
        if (new NullCheck<>(date).isNotNull()) {
            SimpleDateFormat sdf = new SimpleDateFormat(AppConstant.SOLICT_DATE_FORMAT);
            return sdf.format(date);
        }
        return null;
    }

    /**
     * Gets the unique number.
     *
     * @param date the date
     * @return the unique number
     */
    public static String getUniqueNumber(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(date);
        // generate a random integer from 0 to 899, then add 100
        int randomInt = random.nextInt(900) + 100;
        return datetime + randomInt;
    }

    /**
     * Gets the string val.
     *
     * @param val the val
     * @return the string val
     */
    public static String getStringVal(String val) {
        return new NullCheck<>(val).isNull() ? "" : val;
    }

    /**
     * Add hours to date date.
     *
     * @param date  the date
     * @param hours the hours
     * @return the date
     */
    public static Date addHoursToDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    /**
     * Gets the random unique alphanumeric.
     *
     * @return the random unique alphanumeric
     */
    public static String getRandomUniqueAlphanumeric() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * Gets string to date.
     *
     * @param dateStr           the date str
     * @param dynamicDateFormat the dynamic date format
     * @return the string to date
     */
    public static Date getStringToDate(String dateStr, String dynamicDateFormat) {
        Date d = null;
        SimpleDateFormat dateFormat = null;
        try {
            if (dateStr != null) {
                dateFormat = new SimpleDateFormat(dynamicDateFormat);
                d = dateFormat.parse(dateStr);
            }
        } catch (Exception e) {
            LOGGER.info("error in parsing dateStr [ {} ] , format [ {} ] , msg: {}", dateStr, dynamicDateFormat,
                    e.getCause());
        }
        return d;
    }

    /**
     * Gets the date time string.
     *
     * @param dateString the dateString
     * @return the date time string
     */
    public static String getDateTimeString(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dateString);
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            LOGGER.error("exception occurred while formatting date in getDateTimeString method, msg : {}",
                    e.getMessage());
        }
        return dateString;
    }

    /**
     * Convert date to string.
     *
     * @param date              the date
     * @param dynamicDateFormat the dynamic date format
     * @return the string
     */
    public static String convertDateToString(Date date, String dynamicDateFormat) {
        if (new NullCheck<>(date).isNotNull()) {
            SimpleDateFormat sdf = new SimpleDateFormat(dynamicDateFormat);
            return sdf.format(date);
        }
        return null;
    }

    /**
     * Convert object fields from null to specified delimiter.
     *
     * @param <T>            the generic type
     * @param upComingObject the up coming object
     * @param delimiter      the delimiter
     * @param intData        the int data
     * @return the t
     */
    public static <T> T convertObjectFieldsFromNullToSpecifiedDelimiter(T upComingObject, String delimiter,
                                                                        Integer intData) {
        try {
            if (new NullCheck<>(upComingObject).isNotNull()) {
                Class<?> cls = upComingObject.getClass();
                Field[] fields = cls.getDeclaredFields();
                for (Field field : fields) {
                    setAndUpdateFieldDynamically(field, upComingObject, delimiter, intData);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
            LOGGER.error("convertObjectFieldsFromNullToSpecifiedDelimiter [ERROR]", e);
        }
        return upComingObject;
    }

    /**
     * Seet and update field dynamically.
     *
     * @param <T>            the generic type
     * @param field          the field
     * @param upComingObject the up coming object
     * @param delimiter      the delimiter
     * @param intData        the int data
     * @throws IllegalAccessException   the illegal access exception
     * @throws InstantiationException   the instantiation exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    private static <T> void setAndUpdateFieldDynamically(Field field, T upComingObject, String delimiter,
                                                         Integer intData) throws IllegalAccessException, InstantiationException {
        boolean accessible = field.isAccessible();
        /** Fortify issue started
         * Commented line 649  added line 650
         */
        //field.setAccessible(true);
        ReflectionUtils.makeAccessible(field);
        /**
         *Fortify issue ended
         */
        if (field.getType().toString().contains("String")) {
            if (new NullCheck<>(field.get(upComingObject)).isNull()) {
                field.set(upComingObject, delimiter);
            }
        } else if (field.getType().toString().contains("Integer") || field.getType().toString().contains("int")) {
            if (new NullCheck<>(field.get(upComingObject)).isNull()) {
                field.set(upComingObject, intData);
            }
            /**Fortify issue started commented line 664 nd added line 665
             */
            // field.setAccessible(accessible);
            ReflectionUtils.makeAccessible(field);
            /**Fortify issue ended
             */
        } else if (field.getType().toString().contains("Date") || field.getType().toString().contains("date")
                || field.getType().toString().contains("Long") || field.getType().toString().contains("long")
                || field.getType().toString().contains("BigDecimal") || field.getType().toString().contains("Short")
                || field.getType().toString().contains("short") || field.getType().toString().contains("Double")
                || field.getType().toString().contains("double") || field.getType().toString().contains("float")
                || field.getType().toString().contains("Float")) {
            LOGGER.info("No need to change anything, we are continue over here [CAUSE] : {}", field.getType());
        } else {
            if (new NullCheck<>(field.get(upComingObject)).isNull()) {
                field.set(upComingObject, field.getType().newInstance());
            }
            convertObjectFieldsFromNullToSpecifiedDelimiter(field.get(upComingObject), delimiter, intData);
        }
        /**Fortify issue started
         *Commented line 684 and added line 685
         */
        //field.setAccessible(accessible);
        ReflectionUtils.makeAccessible(field);
        /**
         Fortify issue ended
         */
    }

    /**
     * Distinct by key.
     *
     * @param <T>          the generic type
     * @param keyExtractor the key extractor
     * @return the predicate
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Gets the formatted date.
     *
     * @param d                 the d
     * @param dynamicDateFormat the dynamic date format
     * @return the formatted date
     */
    public static String getFormattedDate(Date d, String dynamicDateFormat) {
        try {
            return (new SimpleDateFormat(dynamicDateFormat)).format(d);
        } catch (Exception e) {
            LOGGER.error("exception occurred while formatting date, msg : {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Checks if is null or empty.
     *
     * @param str the str
     * @return true, if is null or empty
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Cleanup Solicit KRA mobile number
     *
     * @param mobileNo the mobile no
     * @return {@link String}
     */
    public static String takeCleanupMobileNo(String mobileNo) {
        if (new NullCheck<>(mobileNo).isNotNullOrEmpty()) {
            String semiFormattedMobile = mobileNo.replaceAll("[^0-9]", "");
            return semiFormattedMobile.length() > 10 ? semiFormattedMobile.substring(semiFormattedMobile.length() - 10)
                    : semiFormattedMobile;
        }
        return mobileNo;
    }

    /**
     * Creates a File if the file does not exist, or returns a reference to the File
     * if it already exists.
     *
     * @param target the target
     * @return the file
     * @throws IOException the io exception
     */
    public static File createOrRetrieve(final String target) throws IOException {
        final Path path = Paths.get(target);
        if (!path.toFile().exists()) {
            LOGGER.info("Target file {} will be created.", target);
            return Files.createFile(Files.createDirectories(path)).toFile();
        }
        LOGGER.info("Target file {} will be retrieved.", target);
        return path.toFile();
    }

    /**
     * Creates a File if the file does not exist, or returns a reference to the File
     * if it already exists.
     *
     * @param target the target
     * @return the file
     * @throws IOException the io exception
     */
    public static File createAndReplaceExisting(final String target) throws IOException {
        final Path path = Paths.get(target);
        Files.deleteIfExists(path);
        LOGGER.info("Target file {} will be retrieved.", target);
        return Files.createFile(Files.createDirectories(path)).toFile();
    }

    /**
     * Get Latest Date by comparing two dates
     *
     * @param date1 Date 1
     * @param date2 Date 2
     * @return Date latest date
     */
    public static Date getLatestDate(@Nullable Date date1, @Nullable Date date2) {
        try {
            if (new NullCheck<>(date1).isNotNullOrEmpty() && new NullCheck<>(date2).isNotNullOrEmpty()) {
                LocalDate appStatusLocalDate = convertToLocalDate(date1);
                LocalDate appModifiedLocalDate = convertToLocalDate(date2);
                if (appStatusLocalDate.isAfter(appModifiedLocalDate)) {
                    return date1;
                } else if (appStatusLocalDate.isBefore(appModifiedLocalDate)) {
                    return date2;
                } else if (appStatusLocalDate.isEqual(appModifiedLocalDate)) {
                    return date1;
                }
            } else if (!new NullCheck<>(date1).isNotNullOrEmpty() && new NullCheck<>(date2).isNotNullOrEmpty()) {
                return date2;
            } else if (new NullCheck<>(date1).isNotNullOrEmpty() && !new NullCheck<>(date2).isNotNullOrEmpty()) {
                return date1;
            }
        } catch (Exception e) {
            LOGGER.warn("Date parse error handled, where date1: {} and date2: {}", date1, date2);
        }
        return null;
    }

    /**
     * Convert image byte [ ].
     *
     * @param encodedImage the encoded image
     * @return the byte [ ]
     * @throws IecoRuntimeException the ieco runtime exception
     * @throws IOException          the io exception
     */
    public static byte[] convertImage(String encodedImage) throws IOException {
        byte[] decodedString = Base64.getDecoder().decode(encodedImage);
        InputStream in = new ByteArrayInputStream(decodedString);
        BufferedImage bufferedImage = ImageIO.read(in);
        float scaleFactor = 1.0f;
        double finalFileSize = 195;
        ByteArrayOutputStream f;
        double modFileSize;
        if (decodedString.length < finalFileSize * 1024) {
            return decodedString;
        }
        do {
            f = scaleImage(bufferedImage, scaleFactor);
            modFileSize = f.toByteArray().length >> 10;
            scaleFactor = (float) Math.sqrt(finalFileSize / modFileSize);
            bufferedImage = ImageIO.read(new ByteArrayInputStream(f.toByteArray()));
        } while (modFileSize > 195);
        return f.toByteArray();
    }

    /**
     * Scale image byte array output stream.
     *
     * @param bufferedImage the buffered image
     * @param scaleFactor   the scale factor
     * @return the byte array output stream
     * @throws IecoRuntimeException the ieco runtime exception
     * @throws IOException          the io exception
     */
    private static ByteArrayOutputStream scaleImage(BufferedImage bufferedImage, double scaleFactor)
            throws IOException {
        int origWidth = bufferedImage.getWidth();
        int origHeight = bufferedImage.getHeight();
        int scaledWidth = (int) (scaleFactor * origWidth);
        int scaledHeight = (int) (scaleFactor * origHeight);
        Image scaledImage = bufferedImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_DEFAULT);
        BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();
        ByteArrayOutputStream file = new ByteArrayOutputStream();
        ImageIO.write(newImage, "JPG", file);
        return file;
    }

    /**
     * Read file string.
     *
     * @param fileName the file name
     * @return the string
     * @throws IOException the io exception
     */
    public static String readFile(@NonNull Path fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName.toFile()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    /**
     * Read file string.
     *
     * @param fileName  the file name
     * @param delimeter the delimeter
     * @return the string
     * @throws IOException the io exception
     */
    public static String readFile(@NonNull Path fileName, @NonNull String delimeter) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName.toFile()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(delimeter);
                line = br.readLine();
            }
            return sb.toString();
        }
    }

    /**
     * Add Minutes to current date
     *
     * @param date   Date
     * @param minute added minute
     * @return
     */
    public static Date addMinutesToDate(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
 
    public static Date getCurrentDateWithTimeValues(String timeCollonSeparated) {
        Date currentDate;
        try {
            Calendar cal = Calendar.getInstance();
            if (timeCollonSeparated != null && timeCollonSeparated.contains("-")) {
                String[] timeDetails = timeCollonSeparated.split("-");
                cal.set(Calendar.HOUR_OF_DAY, timeDetails[0] != null ? Integer.parseInt(timeDetails[0]) : 00);
                cal.set(Calendar.MINUTE, timeDetails.length >= 3 ? Integer.parseInt(timeDetails[1]) : 00);
                cal.set(Calendar.SECOND, timeDetails.length >= 3 ? Integer.parseInt(timeDetails[2]) : 00);
                cal.set(Calendar.MILLISECOND, 0);
            } else {
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 0);
            }
            currentDate = cal.getTime();
        } catch (Exception e) {
            LOGGER.warn("Error while creating date with calendar,ERROR:{}", e.getMessage());
            currentDate = new Date();
        }
        return currentDate;
    }


    /**
     * Gets string to date.
     *
     * @param dateStr           the date str
     * @param dynamicDateFormat the dynamic date format
     * @return the string to date
     */
    public static Date getStringToDate(String dateStr, String dynamicDateFormat, String timezone) {
        Date d = null;
        SimpleDateFormat dateFormat = null;
        try {
            if (dateStr != null) {
                dateFormat = new SimpleDateFormat(dynamicDateFormat);
                dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
                d = dateFormat.parse(dateStr);
            }
        } catch (Exception e) {
            LOGGER.info("error in parsing dateStr [ {} ] , format [ {} ] , msg: {}", dateStr, dynamicDateFormat,
                    e.getCause());
        }
        return d;
    }

}