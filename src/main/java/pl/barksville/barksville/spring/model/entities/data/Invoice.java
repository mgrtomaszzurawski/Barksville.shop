package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.ParentEntity;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Invoices")
@Getter
@Setter
@ToString
public class Invoice extends ParentEntity {



    @OneToMany
    private List<Item> boughtProducts;

    private Double cost;

    @Column(name="invoice_number")
    private String invoiceNumber;

    private String opr;
}
