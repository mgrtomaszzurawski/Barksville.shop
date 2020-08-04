package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.reports.YearReport;

import java.time.LocalDate;


public interface YearReportRepository extends JpaRepository<YearReport, Long> {
    YearReport findByReportName(String reportName);

    YearReport findByReportDate(LocalDate reportDate);
}
