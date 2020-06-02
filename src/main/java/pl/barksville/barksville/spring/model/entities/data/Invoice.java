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
    private String Company;//todo controller i jsp
    @Column(nullable = false)
    private LocalDate date; //todo controller i jsp
    @Column(nullable = false)
    private Double cost;

    @Column(name = "invoice_number",nullable = false)
    private String invoiceNumber;

    @OneToMany
    @JoinColumn(name = "invoice_scan_file_id")
    private List<InvoiceScanFile> invoiceScanFile;
    @Column(nullable = false)
    private String opr;
}
