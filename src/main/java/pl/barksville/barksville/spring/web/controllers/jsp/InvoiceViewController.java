package pl.barksville.barksville.spring.web.controllers.jsp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.InvoiceService;
import pl.barksville.barksville.spring.dto.data.ItemDTO;

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
    public String updateInvoiceRow(Long id, String invoiceNumber, Model model, Double nettoPrice, Double quantity, Double vat, Boolean isDivided, Integer parts, Double price) {
        invoiceService.updateIvoiceRowByInvoiceNymberAndRowID(invoiceNumber, id, nettoPrice, quantity, vat, isDivided, parts, price);
        model.addAttribute("row", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber).getBoughtProducts().stream().filter(row -> id.equals(row.getId())).findAny().get());
        model.addAttribute("invoiceNumber", invoiceNumber);
        return "adminPanel/invoiceRow";
    }

    @PostMapping(value = "invoice/row", params = {"add"})
    public String addInvoiceRow(Long id, String invoiceNumber, Model model, Double nettoPrice, Double quantity, Double vat, Boolean isDivided, Integer parts, Double price) {

        ItemDTO item = new ItemDTO();
        model.addAttribute("row", item);
        model.addAttribute("invoiceNumber", invoiceNumber);
        return "adminPanel/invoiceAddRow";
    }

    @PostMapping(value = "invoice/row", params = {"create"})
    public String createInvoiceRow(String name, String invoiceNumber, Model model, Double nettoPrice, Double quantity, Double vat, Boolean isDivided, Integer parts, Double price) {
       Long rowID = invoiceService.addRowToIvoice(invoiceNumber, name, nettoPrice, quantity, vat, isDivided, parts, price);
        model.addAttribute("row", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber).getBoughtProducts().stream().filter(row -> rowID.equals(row.getId())).findAny().get());
        model.addAttribute("invoiceNumber", invoiceNumber);
        return "adminPanel/invoiceView";
    }

    @PostMapping("invoice/data")
    public String editInvoiceData(String invoiceNumber, Model model) {
        model.addAttribute("invoice", invoiceService.getInvoiceByInvoiceNumber(invoiceNumber));
        return "adminPanel/invoiceData";
    }


}


