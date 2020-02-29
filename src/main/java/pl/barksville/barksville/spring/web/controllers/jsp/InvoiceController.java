package pl.barksville.barksville.spring.web.controllers.jsp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.barksville.barksville.spring.core.service.InvoiceService;
import pl.barksville.barksville.spring.dto.data.ProductDTO;

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
    @PostMapping(value = "/addProduct",params = {"delete"})
    public String deleteProductToInvoice(String name){
        invoiceService.deleteProduct(name);
        return "redirect:/admin/invoice/addProduct";
    }
    @PostMapping(value = "/addProduct",params = {"save"})
    public String saveProductsToInvoice(){

        return "redirect:/admin/invoice/scanUpload";
    }

    @GetMapping("/scanUpload")
    public String ScanToInvoice(Model model){
        model.addAttribute("scans",invoiceService.getInvoiceComponent().getInvoiceDTO().getInvoiceScanFile());
        return "invoice/invoiceScanUpload";
    }

    @PostMapping(value = "/scanUpload",params = {"upload"})
    public String addScanToInvoice(@RequestParam MultipartFile file) throws IOException {
        invoiceService.addScan(file);
        return "redirect:/admin/invoice/scanUpload";
    }

    @PostMapping(value = "/scanUpload",params = {"delete"})
    public String deleteInvoiceScan(String name){
        invoiceService.deleteScan(name);
        return "redirect:/admin/invoice/scanUpload";
    }

    @PostMapping(value = "/scanUpload",params = {"next"})
    public String checkInvoice(){


        return "redirect:/admin/invoice/checkProducts";
    }

    @GetMapping("/checkProducts")
    public String checkProductInInvoice(Model model){
        List<ProductDTO> existing = invoiceService.getListOfExistingProducts();
        List<ProductDTO> nonExisting= invoiceService.getListOfNonExistingProducts(existing);
        existing.removeIf(product->product.getSellPrice()!=null);



        model.addAttribute("existing",existing);
        model.addAttribute("nonExisting",nonExisting);

        return "invoice/invoiceCheckProduct";
    }

    @PostMapping(value = "/checkProducts",params = {"upload"})
    public String updateProducts(String name,String price) throws IOException {
      //  invoiceService.createProductsBaseOnInvoice(nonExisting);
        invoiceService.addPriceToProductDTO(name,price);
        return "redirect:/admin/invoice/checkProducts";
    }


    @PostMapping(value = "/checkProducts",params = {"save"})
    public String saveInvoice(){
        invoiceService.save();

        return "invoice/invoiceSaved";
    }




}
