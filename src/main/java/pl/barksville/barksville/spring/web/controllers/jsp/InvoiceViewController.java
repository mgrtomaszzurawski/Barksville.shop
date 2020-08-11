package pl.barksville.barksville.spring.web.controllers.jsp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.InvoiceService;
import pl.barksville.barksville.spring.model.entities.data.InvoiceScanFile;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/admin/invoice-list")
public class InvoiceViewController {

    private final InvoiceService invoiceService;

    public InvoiceViewController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public String getInvoiceList(Model model) {
        model.addAttribute("invoices", invoiceService.getInvoices());
        return "adminPanel/invoiceList";
    }

    @PostMapping("invoice")
    public String getInvoice(String invoiceNumber, Model model) {
        model.addAttribute("invoice", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber));
        return "adminPanel/invoiceView";
    }

    @PostMapping(value = "download", params = {"download"})
    public ResponseEntity<Resource> downloadFile(String invoiceNumber) {

        InvoiceScanFile scanFile = invoiceService.getInvoiceByInvoiceNumber(invoiceNumber).getInvoiceScanFile();
        // Load file as Resource

        Resource resource = new ByteArrayResource(scanFile.getData());

        //Determine file's content type
        String contentType = scanFile.getContentType();



        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + scanFile.getFileName() + "\"")
                .body(resource);


    }

    @PostMapping(value = "invoice", params = {"delete"})
    public String deleteInvoiceRow(Long id, String invoiceNumber, Model model) {
        invoiceService.deleteInvoiceRowByInvoiceNumberAndRowID(invoiceNumber, id);
        model.addAttribute("invoice", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber));
        return "adminPanel/invoiceView";
    }

    @PostMapping(value = "invoice/row", params = {"edit"})
    public String editInvoiceRow(Long id, String invoiceNumber, Model model) {
        model.addAttribute("row", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber).getBoughtProducts().stream().filter(row -> id.equals(row.getId())).findAny().get());
        model.addAttribute("invoiceNumber", invoiceNumber);
        return "adminPanel/invoiceRow";
    }

    @PostMapping(value = "invoice/row", params = {"delete"})
    public String deleteInvoiceRowOnEdit(Long id, String invoiceNumber, Model model) {
        invoiceService.deleteInvoiceRowByInvoiceNumberAndRowID(invoiceNumber, id);
        model.addAttribute("invoice", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber));
        return "adminPanel/invoiceView";
    }

    @PostMapping(value = "invoice/row", params = {"update"})
    public String updateInvoiceRow(Long id, String invoiceNumber, Model model, Double nettoPrice, Double quantity, Double vat, Boolean isDivided, Integer parts) {
        invoiceService.updateInvoiceRowByInvoiceNumberAndRowID(invoiceNumber, id, nettoPrice, quantity, vat, isDivided, parts);
        model.addAttribute("row", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber).getBoughtProducts().stream().filter(row -> id.equals(row.getId())).findAny().get());
        model.addAttribute("invoiceNumber", invoiceNumber);
        return "adminPanel/invoiceRow";
    }

    @PostMapping(value = "invoice/row", params = {"add"})
    public String addInvoiceRow(String invoiceNumber, Model model) {
        model.addAttribute("invoiceNumber", invoiceNumber);
        return "adminPanel/invoiceAddRow";
    }

    @PostMapping(value = "invoice/row", params = {"create"})
    public String createInvoiceRow(String name, String invoiceNumber, Model model, Double netPrice, Double quantity, Double vat, Boolean isDivided, Integer parts, Double price) {
        invoiceService.addRowToInvoice(invoiceNumber, name, netPrice, quantity, vat, isDivided, parts);
        model.addAttribute("invoiceNumber", invoiceNumber);
        model.addAttribute("invoice", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber));
        return "adminPanel/invoiceView";
    }

    @PostMapping(value ="invoice/data", params = {"edit"})
    public String editInvoiceData(String invoiceNumber, Model model) {
        model.addAttribute("invoice", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber));
        return "adminPanel/invoiceData";
    }

    @PostMapping(value ="invoice/data", params = {"change"})
    public String uchangeInvoiceData(Principal principal,String oldInvoiceNumber, Model model, String invoiceNumber, String company, String invoiceDate) {
        invoiceService.changeInvoiceData(oldInvoiceNumber,invoiceNumber, company, invoiceDate, principal.getName());
        model.addAttribute("invoice", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber));
        return "adminPanel/invoiceData";
    }

    @PostMapping(value ="invoice/data", params = {"delete"})
    public String deleteInvoice(String invoiceNumber, Model model) {
        invoiceService.deleteInvoiceByInvoiceNumber(invoiceNumber);
        model.addAttribute("invoices", invoiceService.getInvoices());
        return "adminPanel/invoiceList";
    }


}


