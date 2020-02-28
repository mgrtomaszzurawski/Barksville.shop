package pl.barksville.barksville.spring.dto.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.data.Item;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RemovedItemDTO {

    private List<ItemDTO> removedProducts;

    private Double losses;

    private String opr;
}
