package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.ZohoTicketsFromWhatsApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



/**
 * The interface Customer repository.
 */
@Repository
public interface WhatsAppTicketRepository extends JpaRepository<ZohoTicketsFromWhatsApp, Integer> {
	
	
	
	Optional<ZohoTicketsFromWhatsApp> findByCustomerId(String customerId);
	
	
   
}
