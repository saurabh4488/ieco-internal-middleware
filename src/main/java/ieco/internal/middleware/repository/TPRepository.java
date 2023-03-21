package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.TPTicketUpdateDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * The interface Customer repository.
 */
@Repository
public interface TPRepository extends JpaRepository<TPTicketUpdateDetails, Integer> {
	
	
	
	
	
	
   
}
