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
import pl.barksville.barksville.spring.core.service.ProductService;
import pl.barksville.barksville.spring.dto.data.ProductDTO;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final ProductService productService;

    public InvoiceController(InvoiceService invoiceService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.productService = productService;
    }


    @GetMapping
    public String prepareInvoiceForm() {
        return "adminPanel/invoiceForm";
    }

    @PostMapping(params = {"upload"})
    public String createInvoice(Principal principal, String invoiceNumber, String company, String invoiceDate, String cost) {

        invoiceService.createInvoiceDTOWithEmptyLists(invoiceNumber, principal.getName(), company, LocalDate.parse(invoiceDate), cost);

        return "redirect:/admin/invoice/addProduct";
    }


    @GetMapping("/addProduct")
    public String addProductToInvoice(Model model) {

        double sum = invoiceService.getInvoiceComponent().getInvoiceDTO().getBoughtProducts().stream().map(p -> {
            Double price = p.getPrice() * p.getQuantity();
            if(p.getIsDivided()){
                price=price*p.getParts();
            }
            return price;}).mapToDouble(p -> p).sum();

        model.addAttribute("products", invoiceService.getInvoiceComponent().getInvoiceDTO().getBoughtProducts());
        model.addAttribute("products2", productService.allProductsNames());
        model.addAttribute("sum", sum);
        return "adminPanel/invoiceAddProduct";
    }

    @PostMapping(value = "/addProduct", params = {"upload"})
    public String addProductToInvoice(String name, Double price, Double quantity, Double vat,Boolean isDivided,Integer parts) {
        invoiceService.addProduct(name, price, quantity, vat, isDivided, parts);
        return "redirect:/admin/invoice/addProduct";
    }

    @PostMapping(value = "/addProduct", params = {"delete"})
    public String deleteProductToInvoice(String name) {
        invoiceService.deleteProduct(name);
        return "redirect:/admin/invoice/addProduct";
    }

    @PostMapping(value = "/addProduct", params = {"save"})
    public String saveProductsToInvoice() {
        double sum = invoiceService.getInvoiceComponent().getInvoiceDTO().getBoughtProducts().stream().map(p -> p.getPrice() * p.getQuantity()).mapToDouble(p -> p).sum();
        if (invoiceService.getInvoiceComponent().getInvoiceDTO().getCost().equals(sum)) {
            return "redirect:/admin/invoice/scanUpload";
        } else {
            return "redirect:/admin/invoice/addProduct";
        }

    }

    @GetMapping("/scanUpload")
    public String ScanToInvoice(Model model) {
        model.addAttribute("scans", invoiceService.getInvoiceComponent().getInvoiceDTO().getInvoiceScanFile());
        return "adminPanel/invoiceScanUpload";
    }

    @PostMapping(value = "/scanUpload", params = {"upload"})
    public String addScanToInvoice(@RequestParam MultipartFile file) throws IOException {
        invoiceService.addScan(file);
        return "redirect:/admin/invoice/scanUpload";
    }

    @PostMapping(value = "/scanUpload", params = {"delete"})
    public String deleteInvoiceScan(String name) {
        invoiceService.deleteScan(name);
        return "redirect:/admin/invoice/scanUpload";
    }

    @PostMapping(value = "/scanUpload", params = {"next"})
    public String checkInvoice() {
        invoiceService.save();

        return "adminPanel/invoiceSaved";

        // return "redirect:/admin/invoice/checkProducts";
    }

    @GetMapping("/checkProducts")
    public String checkProductInInvoice(Model model) {
        List<ProductDTO> existing = invoiceService.getListOfExistingProducts();
        List<ProductDTO> nonExisting = invoiceService.getListOfNonExistingProducts(existing);
        existing.removeIf(product -> product.getSellPrice() != null);


        model.addAttribute("existing", existing);
        model.addAttribute("nonExisting", nonExisting);

        return "adminPanel/invoiceCheckProduct";
    }

    @PostMapping(value = "/checkProducts", params = {"upload"})
    public String updateProducts(String name, String price) throws IOException {
        //  invoiceService.createProductsBaseOnInvoice(nonExisting);
        invoiceService.addPriceToProductDTO(name, price);
        return "redirect:/admin/invoice/checkProducts";
    }


    @PostMapping(value = "/checkProducts", params = {"save"})
    public String saveInvoice() {
        invoiceService.save();

        return "adminPanel/invoiceSaved";
    }


}
