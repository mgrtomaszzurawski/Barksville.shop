package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.data.InvoiceScanFile;

public interface InvoiceScanFileRepository extends JpaRepository<InvoiceScanFile,Long> {
}
