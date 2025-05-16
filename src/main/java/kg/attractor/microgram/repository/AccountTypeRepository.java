package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.AccountTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeModel, Integer> {

    Optional<AccountTypeModel> findByName(String name);

}
