package pl.barksville.barksville.spring.dto.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"data"})
public class InvoiceScanFileDTO {


    private String fileName;

    private String contentType;

    private byte[] data;
}
