package pl.barksville.barksville.spring.model.entities.reports;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;
import pl.barksville.barksville.spring.model.entities.data.ShopReport;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "day_report")
@Getter
@Setter
@ToString
public class DayReport extends BaseEntity {


    @Column(unique = true, nullable = false, name = "report_date")
    private LocalDate reportDate;

    @Column(unique = true, nullable = false, name = "report_name")
    private String reportName;

    @OneToOne
    private ShopReport shopReport;

    @Column(nullable = false, name = "net_income")
    private double netIncome;

    @Column(nullable = false, name = "gross_income")
    private double grossIncome;

    @Column(nullable = false)
    private double expenses;

    @OneToMany(cascade=CascadeType.REMOVE)
    @Column(unique = true, nullable = false, name = "sold_item_report_list")
    private List<SoldItemReport> soldItemReportList;
}
