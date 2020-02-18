package pl.barksville.barksville.spring.model.entities.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.ParentEntity;
import pl.barksville.barksville.spring.model.entities.data.Invoice;
import pl.barksville.barksville.spring.model.entities.data.Order;
import pl.barksville.barksville.spring.model.entities.data.ShopRaport;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@ToString(exclude = "owner")
public class UserDetailsEntity extends ParentEntity {

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
