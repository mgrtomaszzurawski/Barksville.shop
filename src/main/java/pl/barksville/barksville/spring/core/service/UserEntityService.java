package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.barksville.barksville.spring.dto.LoggedUserEntityDTO;
import pl.barksville.barksville.spring.model.dal.repositories.UserRepository;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;

@Service
@Transactional
public class UserEntityService {

    private final UserRepository userRepository;

    public UserEntityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
