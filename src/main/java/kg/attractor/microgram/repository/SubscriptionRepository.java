package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.SubscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionModel, Integer> {
    @Query("SELECT s.followed.id FROM SubscriptionModel s WHERE s.follower.id = :userId")
    List<Integer> findFollowedUserIdsByFollowerId(Integer userId);
    boolean existsByFollowerIdAndFollowedId(Integer followerId, Integer followedId);
}
