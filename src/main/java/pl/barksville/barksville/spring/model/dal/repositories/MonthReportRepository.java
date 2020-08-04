package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.reports.MonthReport;

import java.time.LocalDate;

public interface MonthReportRepository extends JpaRepository<MonthReport, Long> {
    MonthReport findByReportDate(LocalDate reportDate);

    MonthReport findByReportName(String reportName);

    boolean existsByReportDate(LocalDate reportDate);
}
