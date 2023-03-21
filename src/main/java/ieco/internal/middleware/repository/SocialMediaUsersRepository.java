package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.UsersFromSocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The interface Customer repository.
 */
@Repository
public interface SocialMediaUsersRepository extends JpaRepository<UsersFromSocialMedia, Integer> {
	
	UsersFromSocialMedia findByTempCustomerIdAndEmail(String tempId,String email);

	@Query(nativeQuery = true, value = "select CUSTOMER_ID,DMS_DOC_ID,FILE_NAME,CONTENT_TYPE from IECO_SVC_CUSTOMERS.CUSTOMER_DOC_DETAILS where CUSTOMER_ID= :CUSTOMER_ID and DOC_TYPE_ID= :DOC_TYPE_ID ORDER BY CREATED_DATE DESC")
	List<String> fetchDMSDocs(@Param("CUSTOMER_ID") String customerId, @Param("DOC_TYPE_ID") String docTypeId);
	
	
}
