package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.LikeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeModel, Integer> {

    @Query(value = "select * from likes pl where pl.user_id = :user and pl.post_id = :postId", nativeQuery = true)
    Optional<LikeModel> findByPostAndUser(Integer postId, Integer user);


    @Query(nativeQuery = true, value = "select count(*) from likes pl where pl.post_id = :postId")
    Integer countByPost(@Param("postId") Integer postId);

    boolean existsByPost_IdAndUser_Id(Integer postId, Integer userId);
}
