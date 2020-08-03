package pl.barksville.barksville.spring.model.entities.reports;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;
import pl.barksville.barksville.spring.model.entities.data.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sold_item_report")
@Getter
@Setter
@ToString
public class SoldItemReport extends BaseEntity {

    @Column(nullable = false)
    private Double quantity;

    @Column(name="gross_income",nullable = false)
    private Double grossIncome;

    @Column(name="netIncome",nullable = false)
    private Double netIncome;

    @ManyToOne
    @Column(name="sold_invoice_items",nullable = false)
    private Item soldinvoiceItem;


}
