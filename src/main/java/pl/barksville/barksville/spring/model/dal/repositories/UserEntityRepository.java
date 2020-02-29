package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String name);
}
