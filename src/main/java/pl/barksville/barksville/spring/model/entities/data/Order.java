package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString
public class Order extends BaseEntity {

    @OneToMany
    private List<Item> soldProducts;

    private Double price;
    @Column(name="alternative_adress")
    private String altAdress;


    @ManyToOne
    private UserEntity userEntity;

}
