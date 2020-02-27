package pl.barksville.barksville.spring.model.entities.data;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "removed_items")
@Getter
@Setter
@ToString
public class RemovedItems extends BaseEntity {


    @OneToMany
    private List<Item> removedProducts;

    private Double losses;

    private String opr;
}
