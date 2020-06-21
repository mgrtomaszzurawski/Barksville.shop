package pl.barksville.barksville.spring.dto.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class InvoiceDTO {
    private List<ItemDTO> boughtProducts;

    private Double cost;
    private String company;
    private LocalDate date;

    private String invoiceNumber;

    private InvoiceScanFileDTO invoiceScanFile;

    private String opr;
}
