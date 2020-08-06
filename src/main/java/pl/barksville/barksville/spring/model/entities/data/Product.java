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
    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(nullable = false)
    private Double quantity;

    private Double rating;

    @Column(columnDefinition = "TEXT")
    private String description;


    @Column(name="minimal_quantity",nullable = false)
    private Double minimalQuantity;
    @Column(name="total_bought_quantity",nullable = false)
    private Double totalBoughtQuantity;
    @Column(name="total_sold_quantity",nullable = false)
    private Double totalSoldQuantity;
    @Column(name="total_gross_income",nullable = false)
    private Double totalGrossIncome;

    @Column(name="total_expenses",nullable = false)
    private Double totalExpenses;

    @ElementCollection
    @Column(name="alias_names",nullable = false)
    private List<String> aliasNames;


}
