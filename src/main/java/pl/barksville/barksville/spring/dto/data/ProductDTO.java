package pl.barksville.barksville.spring.dto.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.data.ProductInvoicePrice;


import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductDTO {

    private String name;

    private Boolean state;

    private List<ProductInvoicePrice> invoicePriceList;

    private Double sellPrice;

    private Double quantity;

    private Double rating;

    private String description;
}
