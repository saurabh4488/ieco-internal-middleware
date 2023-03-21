package ieco.internal.middleware.repository;

import ieco.internal.middleware.model.ZohoTokenDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Customer repository.
 */
@Repository
public interface TokenRepository extends JpaRepository<ZohoTokenDetails, Integer> {
	/**
	 * Find by email id customer details entity.
	 *
	 * @param email the email
	 * @return the customer details entity
	 */
	@Query(nativeQuery=true,value="select * from ZOHO_TOKEN_DETAILS where rownum=1 orderby CREATED_TIME DESC")
	Optional<ZohoTokenDetails> getRecentToken();
	
	Optional<ZohoTokenDetails> findTopByOrderByCreatedTimeDesc();

}
