package ieco.internal.middleware.repository;


import ieco.internal.middleware.model.WaitingListSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * The interface Customer repository.
 */
@Repository
public interface WaitingListSettingsRepository extends JpaRepository<WaitingListSettingsEntity, Integer> {

    //nextval currval
    @Query(value = "SELECT SEQ_WAITINGLIST_NUMBER.nextval FROM dual")
    Integer getCurrentWaitingId();

}