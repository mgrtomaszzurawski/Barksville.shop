package pl.barksville.barksville.spring.model.entities.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;


import javax.persistence.*;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@ToString(exclude = "owner")
public class UserDetailsEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String adress;
    @Column(name = "is_Active")
    private Boolean isActive;


}
