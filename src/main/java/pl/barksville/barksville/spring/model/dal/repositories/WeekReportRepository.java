package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.reports.WeekReport;

public interface WeekReportRepository extends JpaRepository<WeekReport, Long> {
    //todo
}
