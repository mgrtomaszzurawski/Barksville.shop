package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_invoice_price")
@Getter
@Setter
@ToString
public class ProductInvoicePrice extends BaseEntity {

    @Column(unique = true, nullable = false)
    private Double invoicePrice;
    @Column(nullable = false)
    private Integer quantity;


}
