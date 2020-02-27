package pl.barksville.barksville.spring.dto.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"data"})
public class ShopReportScanFileDTO {

    private String fileName;

    private String contentType;

    private byte[] data;
}
