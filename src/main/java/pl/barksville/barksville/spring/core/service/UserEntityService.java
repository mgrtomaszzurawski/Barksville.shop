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
    public boolean checkCredentials(String username, String password) {
        // TODO Uzupełnij implementację z wykorzystaniem Spring Data

        return true;
    }
    public LoggedUserEntityDTO getUserEntity(String username) {
        LoggedUserEntityDTO loggedUserEntityDTO = null;
        UserEntity userEntity = userRepository.getUserEntitiesByUsername(username);
        if(userEntity !=null){
            loggedUserEntityDTO=new LoggedUserEntityDTO();
            loggedUserEntityDTO.setId(userEntity.getId());
            loggedUserEntityDTO.setUsername(userEntity.getUsername());
        }
        return loggedUserEntityDTO;
    }
}
