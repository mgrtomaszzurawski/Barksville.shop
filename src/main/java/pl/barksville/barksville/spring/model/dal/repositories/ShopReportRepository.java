package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.data.ShopReport;

import java.time.LocalDate;

public interface ShopReportRepository extends JpaRepository<ShopReport, Long> {

    @EntityGraph(attributePaths = {"profileFile.data"})
    ShopReport getWithShopReportScanFileById(Long id);

    ShopReport getShopReportByDate(LocalDate reportDate);
}
