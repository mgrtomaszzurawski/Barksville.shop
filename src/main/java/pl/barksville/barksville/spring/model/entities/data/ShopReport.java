package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Shop")
@Getter
@Setter
@ToString
public class ShopReport extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany
    private List<Item> soldProducts;

    @Column(name = "transactions_number")
    private Integer transactionsNumber;

    private Double earnings;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "shop_report_scan_file_id")
    private ShopReportScanFile shopReportScanFile;

    private String opr;


}
