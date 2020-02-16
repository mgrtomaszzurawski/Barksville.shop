package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.ParentEntity;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Shop")
@Getter
@Setter
@ToString
public class ShopRaport extends ParentEntity {

    @OneToMany
    private List<Item> soldProducts;

    @Column(name="transactions_number")
    private Integer transactionsNumber;

    private Double earnings;


    private String opr;
}
