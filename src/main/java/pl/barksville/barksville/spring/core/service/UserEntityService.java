package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.UserEntityRepository;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;

@Service
public class UserEntityService {


    private final UserEntityRepository userEntityRepository;

    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public UserEntity findByUsername(String name) {
        return userEntityRepository.findByUsername(name);
    }
}
