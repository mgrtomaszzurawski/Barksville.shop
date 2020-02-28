package pl.barksville.barksville.spring.web.controllers.jsp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.InvoiceService;
import pl.barksville.barksville.spring.core.service.ProductService;
import pl.barksville.barksville.spring.dto.data.ItemDTO;
import pl.barksville.barksville.spring.session.InvoiceComponent;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;


    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;

    }

    @GetMapping
    public String prepareInvoiceForm() {
        return "invoice/invoiceForm";
    }

    @PostMapping(params = {"upload"})
    public String createInvoice(Principal principal, String cost, String invoiceNumber  )  {

        invoiceService.createInvoiceDTOWithEmptyLists(cost,invoiceNumber,principal.getName());

        return "redirect:/admin/invoice/addProduct";
    }


    @GetMapping("/addProduct")
    public String addProductToInvoice(Model model){

        model.addAttribute("products",invoiceService.getInvoiceComponent().getInvoiceDTO().getBoughtProducts());


       return "invoice/invoiceAddProduct";
    }

    @PostMapping(value = "/addProduct",params = {"upload"})
    public String addProductToInvoice(String name, String price, String quantity){
        invoiceService.addProduct(name,price,quantity);
        return "redirect:/admin/invoice/addProduct";
    }
    @PostMapping(value = "/addProduct",params = {"delate"})
    public String deleteProductToInvoice(String name){
        invoiceService.deleteProduct(name);
        return "redirect:/admin/invoice/addProduct";
    }
    @PostMapping(value = "/addProduct",params = {"save"})
    public String saveProductsToInvoice(){

        return "invoice/invoiceScanUpload";
    }

    @GetMapping("/scanUpload")
    public String ScanToInvoice(){
        //TODO
        return "invoice/invoiceScanUpload";
    }

    @PostMapping(value = "/scanUpload",params = {"upload"})
    public String addScanToInvoice(){
      //TODO
        return "invoice/invoiceAddProduct";
    }

    @PostMapping(value = "/scanUpload",params = {"delate"})
    public String deleteScanToInvoice(String name){
        //TODO
        return "invoice/invoiceAddProduct";
    }

    @PostMapping(value = "/scanUpload",params = {"save"})
    public String addProductToInvoice(){
//TODO
        return "invoice/invoiceCheckProduct";
    }

}
