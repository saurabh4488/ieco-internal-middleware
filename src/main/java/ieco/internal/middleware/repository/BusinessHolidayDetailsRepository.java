package ieco.internal.middleware.repository;/*
 * package ieco.internal.middleware.repository;
 * 
 * import java.util.List;
 * 
 * import org.springframework.data.jpa.repository.JpaRepository; import
 * org.springframework.data.jpa.repository.Query; import org.springframework.stereotype.Repository;
 * 
 * import ieco.internal.middleware.model.BusinessHolidayDetails;
 * 
 * @Repository public interface BusinessHolidayDetailsRepository extends
 * JpaRepository<BusinessHolidayDetails, Integer> {
 * 
 * @Query(value = "SELECT HOLIDAY_DATE FROM HOLIDAY_DETAILS WHERE IS_ACTIVE ='Y'", nativeQuery =
 * true) public List<String> findAllHolidayDates();
 * 
 * //@Query(value =
 * "SELECT HOLIDAY_DATE FROM HOLIDAY_DETAILS WHERE IS_ACTIVE ='Y' AND HOLIDAY_DATE=:holidayDate",
 * nativeQuery = true) //public String findByHolidayDateAndIsActive(String holidayDate, String
 * isActive);
 * 
 * }
 */
