package pl.barksville.barksville.spring.dto;

import lombok.Data;
import pl.barksville.barksville.spring.model.entities.data.Product;
@Data
public class ItemDTO {

    private Product product;

    private Integer quantity;
}
