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

}
