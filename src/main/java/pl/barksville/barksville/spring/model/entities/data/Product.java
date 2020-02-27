package pl.barksville.barksville.spring.model.entities.data;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean state;
    /*
    @Column(nullable = false, name="buy_price")
    private Double buyPrice;
*/
    @OneToMany
    private List<ProductInvoicePrice> invoicePriceList;

    //sell price jest uzywana tylko jako cena bazowa do wyświetlania w sklepie internetowym
    @Column(nullable = false, name = "sell_price")
    private Double sellPrice;

    @Column(nullable = false)
    private Double quantity;

    private Double rating;

    @Column(columnDefinition = "TEXT")
    private String description;


}
