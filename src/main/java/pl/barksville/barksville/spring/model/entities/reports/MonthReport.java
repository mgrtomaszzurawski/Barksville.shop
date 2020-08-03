package pl.barksville.barksville.spring.model.entities.reports;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;
import pl.barksville.barksville.spring.model.entities.data.ShopReport;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "month_reports")
@Getter
@Setter
@ToString
public class MonthReport extends BaseEntity {


    @Column(unique = true, nullable = false, name = "report_name")
    private String reportName;

    @Column(nullable = false, name = "net_income")
    private double netIncome;

    @Column(nullable = false, name = "gross_income")
    private double grossIncome;

    @Column(nullable = false)
    private double expenses;

    @OneToMany
    @Column(unique = true, nullable = false, name = "week_report_list")
    private List<WeekReport> weekReportList;

}
