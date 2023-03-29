package ieco.internal.middleware.repository;

import ieco.internal.middleware.model.UsersFromSocialMedia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public class SocialMediaRepository implements PanacheRepository<String> {

    public List<String> fetchDMSDocs(@Param("CUSTOMER_ID") String customerId, @Param("DOC_TYPE_ID") String docTypeId){
        return list("select CUSTOMER_ID,DMS_DOC_ID,FILE_NAME,CONTENT_TYPE from IECO_SVC_CUSTOMERS.CUSTOMER_DOC_DETAILS where CUSTOMER_ID= :CUSTOMER_ID and DOC_TYPE_ID= :DOC_TYPE_ID ORDER BY CREATED_DATE DESC",customerId,docTypeId);
    }

}
