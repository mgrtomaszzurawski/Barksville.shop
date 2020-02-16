package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.ParentEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString(exclude = "owner")
public class Order extends ParentEntity {

    @OneToMany
    private List<Item> soldProducts;

    private Double price;

    private String altAdress;

}
