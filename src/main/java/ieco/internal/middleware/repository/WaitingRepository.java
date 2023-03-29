package ieco.internal.middleware.repository;

import ieco.internal.middleware.model.WaitingListDetailsEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public class WaitingRepository implements PanacheRepository<WaitingListDetailsEntity> {

    @Modifying
    public void updateAccessFields(@Param("emailId") List<String> emailId, @Param("wlNum") Integer wlNum, @Param("status") String status, Date date, String allowedBy){
            update("update WAITINGLIST_USER_DETAILS wd set wd.WAITING_LIST_NUMBER=:wlNum, wd.ISACCESS_PROVIDED=:status,wd.ACCESS_ALLOWED_ON=:date,wd.ACCESS_ALLOWED_BY=:allowedBy where wd.EMAIL_ID IN (:emailId)",wlNum,status,date,allowedBy,emailId);
    }

    @Modifying
    public void deleteUser(@Param("emailId") List<String> emailId){
        delete("delete from WAITINGLIST_USER_DETAILS wd where wd.EMAIL_ID IN (:emailId)",emailId);
    }

}
