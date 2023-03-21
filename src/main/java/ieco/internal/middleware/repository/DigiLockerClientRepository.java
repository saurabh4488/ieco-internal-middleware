package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.DigiLockerClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



/**
 * The interface Customer repository.
 */
@Repository
public interface DigiLockerClientRepository extends JpaRepository<DigiLockerClientDetails, Integer> {
	
	
	
	Optional<DigiLockerClientDetails> findByClientId(String clientId);
	
	
   
}
