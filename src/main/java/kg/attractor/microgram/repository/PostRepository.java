package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {

    Integer countByUserId(Integer userId);
    List<PostModel> findAllByUserId(Integer userId);
}
