package pl.barksville.barksville.spring.dto.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.data.ShopReportScanFile;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShopReportDTO {

    private List<ItemDTO> soldProducts;

    private String name;

    private LocalDate date;

    private Integer transactionsNumber;

    private Double earnings;

    private ShopReportScanFileDTO shopReportScanFile;

    private String opr;
}
