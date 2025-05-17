package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {

    @Query("SELECT c FROM CommentModel c WHERE c.post.id = :postId ORDER BY c.createdAt DESC")
    List<CommentModel> findByPostId(@Param("postId") Integer postId);

    @Query("SELECT COUNT(c) FROM CommentModel c WHERE c.post.id = :postId")
    Integer countByPostId(@Param("postId") Integer postId);

}
