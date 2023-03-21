package ieco.internal.middleware.service.impl;/*
 * package ieco.internal.middleware.service.impl;
 * 
 * import java.time.LocalDateTime; import java.time.LocalTime; import
 * java.time.format.DateTimeFormatter; import java.util.List; import java.text.SimpleDateFormat;
 * import java.time.LocalDate; import java.util.Date; import java.time.ZoneId;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import ieco.internal.middleware.domain.response.BusinessWorkingDetailsVo; import
 * ieco.internal.middleware.model.BusinessHoursDetails; import
 * ieco.internal.middleware.repository.BusinessHolidayDetailsRepository; import
 * ieco.internal.middleware.repository.BusinessHoursDetailsRepository; import
 * ieco.internal.middleware.service.AssistanceSupportService;
 * 
 * @Service public class AssistanceSupportServiceImpl implements AssistanceSupportService {
 * 
 * private Logger log = LoggerFactory.getLogger(AssistanceSupportServiceImpl.class);
 * 
 * @Autowired private BusinessHolidayDetailsRepository holidayDetailsRepository;
 * 
 * @Autowired private BusinessHoursDetailsRepository businessHoursDetailsRepository;
 * 
 * @Override public BusinessWorkingDetailsVo getBusinessworkingDetails() { List<String> holidayDates
 * = holidayDetailsRepository.findAllHolidayDates(); BusinessWorkingDetailsVo businessWorkingDetails
 * = new BusinessWorkingDetailsVo(); businessWorkingDetails.setBusinessHolidayDates(holidayDates);
 * DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss"); LocalDateTime
 * currentLocalDateTime = LocalDateTime.now(); String currentDay =
 * currentLocalDateTime.getDayOfWeek().toString();
 * 
 * DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("DD-MMM-YY"); String currentDate=
 * dateFormat.format(LocalDateTime.now()).toUpperCase();
 * 
 * if(null!=holidayDates && holidayDates.contains(currentDate) ||
 * "SUNDAY".equalsIgnoreCase(currentDay)){ log.info("Out of Business for the currentDate:{}",
 * currentDate); businessWorkingDetails.setBusinessHrs(false); return businessWorkingDetails; }
 * 
 * BusinessHoursDetails workingHrs = businessHoursDetailsRepository.findByDay(currentDay); if (null
 * != workingHrs) { log.info("Request received Day:{}", workingHrs.getDay()); LocalTime
 * businessStartTime = LocalTime.parse(workingHrs.getStartTime(), timeFormat); LocalTime
 * businessEndTime = LocalTime.parse(workingHrs.getEndTime(), timeFormat); LocalTime currentTime =
 * LocalTime.parse(timeFormat.format(currentLocalDateTime), timeFormat); if
 * (currentTime.isBefore(businessStartTime) || currentTime.isAfter(businessEndTime)) {
 * log.info("Request received at Out of Business hours, Day:{}, Time:{}", currentDay, currentTime);
 * businessWorkingDetails.setBusinessHrs(false); } else {
 * log.info("Request received during business Hours, Day:{}, Time:{}", currentDay, currentTime);
 * businessWorkingDetails.setBusinessHrs(true); } } else {
 * log.info("Out of Business for the day:{}", currentDay);
 * businessWorkingDetails.setBusinessHrs(false); } return businessWorkingDetails; }
 * 
 * }
 */
