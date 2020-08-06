package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.reports.WeekReport;

import java.time.LocalDate;

public interface WeekReportRepository extends JpaRepository<WeekReport, Long> {
    WeekReport findByReportName(String reportName);


    Boolean existsByReportDate(LocalDate reportDate);
    WeekReport findByReportDate(LocalDate reportDate);

    void deleteByReportDate(LocalDate localDate);
}
