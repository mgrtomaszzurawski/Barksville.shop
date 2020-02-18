package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true,value = "SELECT CASE WHEN count (*)>=1 ELSE 'false'END "+"FROM example_users WHERE username =?1")
    Boolean checkIfUserEntityExist(String username);
    UserEntity getUserEntitiesByUsername(String username);
    boolean existsByUsername(String username);
}
