package pl.barksville.barksville.spring.model.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barksville.barksville.spring.model.entities.data.Invoice;

import java.time.LocalDate;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice getInvoiceByDate(LocalDate date);

   Invoice getInvoiceByInvoiceNumber(String invoiceNumber);

    void deleteByInvoiceNumber(String invoiceNumber);


}
