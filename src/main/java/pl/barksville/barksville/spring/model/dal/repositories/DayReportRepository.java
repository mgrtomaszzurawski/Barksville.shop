package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.reports.DayReport;

import java.time.LocalDate;
import java.util.List;

public interface DayReportRepository extends JpaRepository<DayReport, Long> {

    DayReport findByReportName(String name);

    DayReport findByReportDate(LocalDate reportDate);

    boolean existsByReportDate(LocalDate reportDate);

    void deleteByReportDate(LocalDate localDate);
}
