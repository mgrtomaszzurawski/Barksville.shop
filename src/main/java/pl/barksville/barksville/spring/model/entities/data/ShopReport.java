package pl.barksville.barksville.spring.model.entities.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Shop")
@Getter
@Setter
@ToString
public class ShopReport extends BaseEntity {

    @OneToMany
    private List<Item> soldProducts;

    @Column(name="transactions_number")
    private Integer transactionsNumber;

    private Double earnings;

    @OneToMany
    private List<ShopReportScanFile> shopReportScanFiles;

    private String opr;
}
