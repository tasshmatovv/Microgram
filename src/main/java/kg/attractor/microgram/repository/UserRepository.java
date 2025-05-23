package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);

    Optional<UserModel> findByEmail(String email);



    @Query(value = "select * from users u " +
            "where LOWER(u.fullName) like LOWER(CONCAT('%', :query, '%')) or " +
            "LOWER(u.nickName) like LOWER(CONCAT('%', :query, '%')) or " +
            "LOWER(u.email) like LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<UserModel> findUserByNameOrNickName(@Param("query") String query);


    boolean existsByNickNameAndIdNot(String nickName, Integer id);


}
