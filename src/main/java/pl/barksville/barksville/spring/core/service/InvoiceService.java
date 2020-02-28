package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.dto.data.InvoiceDTO;
import pl.barksville.barksville.spring.dto.data.InvoiceScanFileDTO;
import pl.barksville.barksville.spring.dto.data.ItemDTO;
import pl.barksville.barksville.spring.model.dal.repositories.InvoiceRepository;
import pl.barksville.barksville.spring.model.dal.repositories.InvoiceScanFileRepository;
import pl.barksville.barksville.spring.model.entities.data.ShopReportScanFile;
import pl.barksville.barksville.spring.session.InvoiceComponent;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceScanFileRepository invoiceScanFileRepository;
    private final InvoiceComponent invoiceComponent;
    private final ProductService productService;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceScanFileRepository invoiceScanFileRepository, InvoiceComponent invoiceComponent, ProductService productService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceScanFileRepository = invoiceScanFileRepository;

        this.invoiceComponent = invoiceComponent;
        this.productService = productService;
    }

    public void createInvoiceDTOWithEmptyLists(String cost, String invoiceNumber, String opr){


        //InvoiceDTO invoiceDTO = new InvoiceDTO();
        //invoiceComponent.setInvoiceDTO(invoiceDTO);
        invoiceComponent.getInvoiceDTO().setCost(Double.parseDouble(cost));
        invoiceComponent.getInvoiceDTO().setInvoiceNumber(invoiceNumber);
        invoiceComponent.getInvoiceDTO().setOpr(opr);

        List<InvoiceScanFileDTO> invoiceScanFileDTOList = new ArrayList<>();
        invoiceComponent.getInvoiceDTO().setInvoiceScanFile(invoiceScanFileDTOList);

        List<ItemDTO> itemDTOList = new ArrayList<>();
        invoiceComponent.getInvoiceDTO().setBoughtProducts(itemDTOList);


    }

    public void addProduct(String name, String price, String quantity) {
            ItemDTO itemDTO = new ItemDTO();

            itemDTO.setProduct(productService.createProductDTOBaseOnInvoice(name, price, quantity));

            itemDTO.setQuantity(Double.parseDouble(quantity));

            itemDTO.setPrice(Double.parseDouble(price));

            invoiceComponent.getInvoiceDTO().getBoughtProducts().add(itemDTO);
    }

    private boolean isValidProfileFile(ShopReportScanFile shopReportScanFile) {

        //TODO

        if (shopReportScanFile.getContentType() == null) return false;
        if (shopReportScanFile.getFileName() == null || shopReportScanFile.getFileName().isBlank()) return false;
        if (shopReportScanFile.getData() == null) return false;
        return true;
    }


    public void deleteProduct(String name) {
        invoiceComponent.getInvoiceDTO().getBoughtProducts().removeIf(itemDTO -> itemDTO.getProduct().getName().equals(name));
    }

    public InvoiceComponent getInvoiceComponent(){
        return invoiceComponent;
    }
}
