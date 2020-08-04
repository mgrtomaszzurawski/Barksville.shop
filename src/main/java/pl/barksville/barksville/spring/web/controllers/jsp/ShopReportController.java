package pl.barksville.barksville.spring.web.controllers.jsp;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.barksville.barksville.spring.core.service.ProductService;
import pl.barksville.barksville.spring.core.service.ShopReportService;
import pl.barksville.barksville.spring.model.dal.repositories.ItemRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductIvoicePriceRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ShopReportRepository;
import pl.barksville.barksville.spring.model.entities.data.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/shop-report")
public class ShopReportController {


    private final ProductService productService;
    private final ShopReportService shopReportService;

    public ShopReportController(ShopReportRepository shopReportRepository, ProductRepository productRepository, ItemRepository itemRepository, ProductIvoicePriceRepository productIvoicePriceRepository, ProductService productService, ShopReportService shopReportService) {

        this.productService = productService;
        this.shopReportService = shopReportService;
    }

    @GetMapping
    public String prepareUserAccountPage(Model model, Principal principal) {


        return "adminPanel/shopReport";
    }
    
    @PostMapping(params = {"cancel"})
    public String cancelEditUserData() {
        return "adminPanel/shopReport";
    }

    @PostMapping(params = {"upload"})
    public String uploadProfileFile(@RequestParam MultipartFile file, Principal principal) throws IOException {

        shopReportService.createShopReport(file, principal.getName());

        return "redirect:/admin/panel";
    }


}
