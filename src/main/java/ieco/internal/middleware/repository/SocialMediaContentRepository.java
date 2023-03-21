package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.SocialMediaContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



/**
 * The interface Customer repository.
 */
@Repository
public interface SocialMediaContentRepository extends JpaRepository<SocialMediaContent, Integer> {
	
	Optional<SocialMediaContent> findByCid(String cid);
	
	List<SocialMediaContent> findByIsActive(String val);
	
	
}
