package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.DigiLockerCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



/**
 * The interface Customer repository.
 */
@Repository
public interface DigiLockerCustomerRepository extends JpaRepository<DigiLockerCustomers, Integer> {
	
	
	
	Optional<DigiLockerCustomers> findByCustomerId(String clientId);
	
	
   
}
