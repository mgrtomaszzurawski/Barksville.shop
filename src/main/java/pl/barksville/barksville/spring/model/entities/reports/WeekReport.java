package pl.barksville.barksville.spring.model.entities.reports;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "week_reports")
@Getter
@Setter
@ToString
public class WeekReport extends BaseEntity {

    @Column(unique = true, nullable = false, name = "report_name")
    private LocalDate reportDate;

    @Column(unique = true, nullable = false, name = "report_name")
    private String reportName;

    @Column(nullable = false, name = "net_income")
    private double netIncome;

    @Column(nullable = false, name = "gross_income")
    private double grossIncome;

    @Column(nullable = false)
    private double expenses;

    @OneToMany
    @Column(unique = true, nullable = false, name = "day_report_list")
    private List<DayReport> dayReportList;


}
