package pl.barksville.barksville.spring.dto.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.data.InvoiceScanFile;
import pl.barksville.barksville.spring.model.entities.data.Item;

import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class InvoiceDTO {
    private List<Item> boughtProducts;

    private Double cost;

    private String invoiceNumber;

    private List<InvoiceScanFile> invoiceScanFile;

    private String opr;
}
