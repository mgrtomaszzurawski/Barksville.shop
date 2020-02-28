package pl.barksville.barksville.spring.dto.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderDTO {

    private List<ItemDTO> soldProducts = new ArrayList<>();

    private Double price;

    private String altAddress;

    private UserEntity userEntity;
}
