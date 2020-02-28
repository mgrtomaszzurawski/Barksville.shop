package pl.barksville.barksville.spring.session;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.barksville.barksville.spring.dto.data.InvoiceDTO;

@Component
//@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class InvoiceComponent {

    InvoiceDTO invoiceDTO = new InvoiceDTO();

}
