package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Invoices")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Invoice extends BaseEntity {
    @Column(nullable = false)
    @OneToMany
    private List<Item> boughtProducts;

    @Column(nullable = false)
    private String Company;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Double cost;

    @Column(name = "invoice_number",nullable = false, unique = true)
    private String invoiceNumber;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "invoice_scan_file_id")
    private InvoiceScanFile invoiceScanFile;
    @Column(nullable = false)
    private String opr;
}
