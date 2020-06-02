package pl.barksville.barksville.spring.core.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.UserRepository;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String passwordEncode(String pass) {
        return passwordEncoder.encode(pass);
    }

    public void registerUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
/*
    public boolean checkIfUserExistByName(String name) {
        return userRepository.checkIfUserEntityExistByName(name);
    }

 */
}
