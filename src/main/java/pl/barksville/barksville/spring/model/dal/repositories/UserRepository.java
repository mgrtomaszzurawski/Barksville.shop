package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT CASE WHEN count (*)>=1 ELSE 'false'END " + "FROM users WHERE username =?1")
    Boolean checkIfUserEntityExistByName(String username);

    UserEntity getUserEntitiesByUsername(String username);

}
