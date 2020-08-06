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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.barksville.barksville.spring.core.service.ProductService;
import pl.barksville.barksville.spring.core.service.ReportsService;
import pl.barksville.barksville.spring.core.service.ShopReportService;
import pl.barksville.barksville.spring.model.dal.repositories.ItemRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductIvoicePriceRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ShopReportRepository;
import pl.barksville.barksville.spring.model.entities.data.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/admin/shop-report")
public class ShopReportController {



    private final ShopReportService shopReportService;


    public ShopReportController(ShopReportService shopReportService) {


        this.shopReportService = shopReportService;

    }

    @GetMapping
    public String chooseShopReport(Model model, Principal principal) {


        return "adminPanel/shopReport";
    }


    @PostMapping(params = {"upload"})
    public String uploadReportFile(@RequestParam MultipartFile file, Principal principal) throws IOException {

        shopReportService.createShopReport(file, principal.getName());

        return "redirect:/admin/panel";
    }

    @PostMapping(value = "/shop-report-view")
    public String viewShopReport(Model model, String reportDate) {

        model.addAttribute("shopReport", shopReportService.getShopReportByDate(LocalDate.parse(reportDate)));
        return "adminPanel/shopReportView";
    }


    @PostMapping(value = "download", params = {"download"})
    public ResponseEntity<Resource> downloadFile(String reportDate) {

        ShopReportScanFile scanFile = shopReportService.getShopReportByDate(LocalDate.parse(reportDate)).getShopReportScanFile();

        // Load file as Resource

        Resource resource = new ByteArrayResource(scanFile.getData());

        //Determine file's content type
        String contentType = scanFile.getContentType();



        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + scanFile.getFileName() + "\"")
                .body(resource);


    }

    @GetMapping(value="/list")
    public String shopReportList(Model model, String reportDate) {

        model.addAttribute("shopReportList", shopReportService.findAll());
        return "adminPanel/shopReportList";
    }

}
