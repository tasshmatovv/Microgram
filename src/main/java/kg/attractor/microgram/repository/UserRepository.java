package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);

    Optional<UserModel> findByEmail(String email);

}
