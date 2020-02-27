package pl.barksville.barksville.spring.dto.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.data.Product;

@Getter
@Setter
@ToString(exclude = "product")
@EqualsAndHashCode
public class ItemDTO {

    private Product product;

    private Double quantity;

    private Double price;
}
