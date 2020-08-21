package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.reports.SoldItemReport;


public interface SoldItemReportRepository extends JpaRepository<SoldItemReport, Long> {


}
