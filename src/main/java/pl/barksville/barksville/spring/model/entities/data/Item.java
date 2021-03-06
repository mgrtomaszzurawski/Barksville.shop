package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Map;

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

    @Column(name="net_price")
    private Double netPrice;
    private Double vat;
    @Column(name="is_divided")
    private Boolean isDivided;
    private Integer parts;


   // @ElementCollection
   // @CollectionTable(name = "item_bought_products",
   //         joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
   // @MapKeyColumn(name = "item_name")
   // @Column(name = "sold")
  // private Map<String,Double> boughtProducts;

    @Column(nullable = false,name = "is_sold")
    private Boolean isSold;

    @Column(name="left_items")
    private Double leftItems;

}
