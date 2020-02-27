package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Items")
@Getter
@Setter
@ToString(exclude = "product")
public class Item extends BaseEntity {

    @ManyToOne
    private Product product;

    private Double quantity;

    //cena moze sie roznic od ceny w Product np. przez promocje
    private Double price;

}
