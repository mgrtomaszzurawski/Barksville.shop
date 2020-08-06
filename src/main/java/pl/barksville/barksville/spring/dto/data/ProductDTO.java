package pl.barksville.barksville.spring.dto.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.data.ProductInvoicePrice;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductDTO {

    private String name;

    private Boolean state;

    private List<ProductInvoicePriceDTO> invoicePriceList;

    private Double sellPrice;

    private Double quantity;

    private Double rating;

    private String description;


    private Double minimalQuantity;

    private Double totalBoughtQuantity;

    private Double totalSoldQuantity;

    private Double totalGrossIncome;

    private Double totalExpenses;

    private List<String> aliasNames;
}
