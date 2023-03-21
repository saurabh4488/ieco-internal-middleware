package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



/**
 * The interface Customer repository.
 */
@Repository
public interface AdminDetailsRepository extends JpaRepository<AdminDetails, Integer> {
	
	
	
	Optional<AdminDetails> findByEmailId(String email);
	
	Optional<AdminDetails> findByToken(String token);
   
}
