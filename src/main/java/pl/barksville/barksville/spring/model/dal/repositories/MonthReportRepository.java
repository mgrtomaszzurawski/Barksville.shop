package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.reports.MonthReport;

public interface MonthReportRepository extends JpaRepository<MonthReport, Long> {
    MonthReport findByReportName(String reportName);

}
