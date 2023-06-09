package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.WaitingListDetailsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * The interface Customer repository.
 */
@Repository
public interface WaitingListRepository extends JpaRepository<WaitingListDetailsEntity, Integer> {
	
	
	
	WaitingListDetailsEntity findByEmailIdIgnoreCase(String email);
	
	
    WaitingListDetailsEntity findByReferenceCode(String refCode);
    
    List<WaitingListDetailsEntity> findByEmailIdContainingIgnoreCase(String chars);
    
    @Query(value = "SELECT SEQ_WAITINGLIST_NUMBER.nextval FROM dual", nativeQuery = 
            true)
     Integer getNextWaitingId();
    
    @Modifying
    @Query(value = "update WAITINGLIST_USER_DETAILS wd set wd.WAITING_LIST_NUMBER=:wlNum, wd.ISACCESS_PROVIDED=:status,wd.ACCESS_ALLOWED_ON=:date,wd.ACCESS_ALLOWED_BY=:allowedBy where wd.EMAIL_ID IN (:emailId)",nativeQuery=true)
    void updateAccessFields(@Param("emailId") List<String> emailId,@Param("wlNum") Integer wlNum, @Param("status") String status,Date date,String allowedBy);
    
    
    @Modifying
    @Query(value = "delete from WAITINGLIST_USER_DETAILS wd where wd.EMAIL_ID IN (:emailId)",nativeQuery=true)
    void deleteUser(@Param("emailId") List<String> emailId);
    
    Page<WaitingListDetailsEntity> findByIsAccessProvided(String searchTerm,Pageable pageable);
    
    /*@Query(value = "select from WAITINGLIST_USER_DETAILS wd where wd.REFERED_COUNT>0",nativeQuery=true)
    List<WaitingListDetailsEntity> getReferedUsers();*/

    List<WaitingListDetailsEntity> findByReferedCountGreaterThanAndIsAccessProvidedOrderByReferedCountDesc(Integer count, String isAccessProvided);
    
    List<WaitingListDetailsEntity> findByReferedCountGreaterThanAndOffsetGreaterThanAndIsAccessProvidedOrderByReferedCountDesc(Integer count,Integer offset, String isAccessProvided);

    List<WaitingListDetailsEntity> findByWaitingListNumberGreaterThanEqual(Integer waitingNumber);
    
    List<WaitingListDetailsEntity> findAllByEmailIdIn(List<String> emailId);
    
    List<WaitingListDetailsEntity> findAllByEmailIdInAndWaitingListNumberGreaterThanEqual(List<String> emailId, Integer minValue);
}
