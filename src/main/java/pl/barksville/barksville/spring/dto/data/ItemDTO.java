package pl.barksville.barksville.spring.dto.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "product")
@EqualsAndHashCode
public class ItemDTO {

    private ProductDTO product;

    private Double quantity;

    private Double price;


    private Double nettoPrice;
    private Double vat;
    private Boolean isDivided;
    private Integer parts;
    private Boolean isSold;
}
