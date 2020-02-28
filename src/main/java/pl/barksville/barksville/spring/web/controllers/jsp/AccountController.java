package pl.barksville.barksville.spring.web.controllers.jsp;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.barksville.barksville.spring.model.dal.repositories.ItemRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductIvoicePriceRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ShopReportRepository;
import pl.barksville.barksville.spring.model.entities.data.*;


import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/account")
public class AccountController {

    private final ShopReportRepository shopReportRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final ProductIvoicePriceRepository productIvoicePriceRepository;

    public AccountController(ShopReportRepository shopReportRepository, ProductRepository productRepository, ItemRepository itemRepository, ProductIvoicePriceRepository productIvoicePriceRepository) {
        this.shopReportRepository = shopReportRepository;
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;
        this.productIvoicePriceRepository = productIvoicePriceRepository;
    }

    @GetMapping
    public String prepareUserAccountPage(Model model, Principal principal) {


        return "elements/account";
    }

    /*
        @PostMapping(params = {"edit"})
        public String beginEditUserData(Model model, Principal principal) {

        }

        @PostMapping(params = {"save"})
        public String saveEditUserData(String firstName, String lastName, String pesel, String dateOfBirth, Principal principal) {

        }
    */
    @PostMapping(params = {"cancel"})
    public String cancelEditUserData() {
        return "redirect:elements/account";
    }

    @PostMapping(params = {"upload"})
    public String uploadProfileFile(@RequestParam MultipartFile file, Principal principal) throws IOException {

        // log.debug("Dodawanie zdjęcia profilowego dla użytkownika: {}", loggedUser);

        ShopReport newReport = new ShopReport();
        List<Item> itemList = new ArrayList<>();
        newReport.setSoldProducts(itemList);


        ShopReportScanFile shopReportScanFile = new ShopReportScanFile();

        shopReportScanFile.setContentType(file.getContentType());
        shopReportScanFile.setFileName(file.getOriginalFilename());
        shopReportScanFile.setData(file.getBytes());

        if (isValidProfileFile(shopReportScanFile)) {
            newReport.setShopReportScanFile(shopReportScanFile);
            parsePDF(newReport, file);
            newReport.setOpr(principal.getName());

            shopReportRepository.save(newReport);
        }
        return "redirect:/admin/panel";
    }

    /*
    @GetMapping("/profile-file")
    public ResponseEntity<Resource> getUserProfileFile(Principal principal) {
        UserEntity loggedUser = userRepository.getWithDetailsByUsername(principal.getName());
        log.debug("Pobieranie zdjęcia profilowego dla użytkownika: {}", loggedUser);

        if (hasProfileFile(loggedUser)) {
            log.debug("Zwrócono zdjęcie profilowe użytkownika: {}", loggedUser);
            return buildProfileFileResponse(loggedUser);
        }
        else {
            log.debug("Nie zwrócono zdjęcia profilowego użytkownika: {}", loggedUser);
            return buildNoProfileFileResponse();
        }
    }

    private ResponseEntity<Resource> buildNoProfileFileResponse() {
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Resource> buildProfileFileResponse(UserEntity loggedUser) {
        UserDetailsEntity details = userDetailsRepository.getWithProfileFileByOwnerUsername(loggedUser.getUsername());
        FileEntity profileFile = details.getProfileFile();
        ByteArrayResource data = getProfileFileData(profileFile);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(profileFile.getContentType()))
                .header("Content-Disposition", String.format("filename=%s", profileFile.getFileName()))
                .body(data);
    }

    private ByteArrayResource getProfileFileData(FileEntity profileFile) {
        return new ByteArrayResource(profileFile.getData());
    }

    private boolean hasProfileFile(UserEntity loggedUser) {
        if (loggedUser.getDetails() == null) return false;
        if (loggedUser.getDetails().getProfileFileId() == null) return false;
        return true;
    }
*/
    private boolean isValidProfileFile(ShopReportScanFile shopReportScanFile) {
        if (shopReportScanFile.getContentType() == null) return false;
        if (shopReportScanFile.getFileName() == null || shopReportScanFile.getFileName().isBlank()) return false;
        if (shopReportScanFile.getData() == null) return false;
        return true;
    }


    private void parsePDF(ShopReport shopReport, MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getBytes())) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
                //System.out.println("Text:" + st);


                // split by whitespace
                String lines[] = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    String[] words = line.split(" ");
                    if (words[0].matches("\\d+")) {
                        StringBuilder name = new StringBuilder();
                        for (int i = 1; i < words.length - 2; i++) {
                            name.append(words[i] + " ");
                        }
                        name.deleteCharAt(name.length() - 1);

                        Item item = new Item();

                        //tworzenie produktu
                        Product product = new Product();

                        ProductInvoicePrice productInvoicePrice = new ProductInvoicePrice();
                        productInvoicePrice.setInvoicePrice(Double.parseDouble(words[words.length - 1]));
                        productInvoicePrice.setQuantity(Double.parseDouble(words[words.length - 2]));
                        productIvoicePriceRepository.save(productInvoicePrice);
                        List<ProductInvoicePrice> productInvoicePriceList = new ArrayList<>();
                        productInvoicePriceList.add(productInvoicePrice);
                        product.setInvoicePriceList(productInvoicePriceList);

                        product.setName(name.toString());
                        product.setQuantity(Double.parseDouble(words[words.length - 2]));
                        product.setState(Boolean.TRUE);
                        product.setSellPrice(Double.parseDouble(words[words.length - 1]));

                        productRepository.save(product);
                        //koniec tworzenia prodkutku

                        item.setProduct(productRepository.findByName(name.toString()));
                        item.setQuantity(Double.parseDouble(words[words.length - 2]));
                        item.setPrice(Double.parseDouble(words[words.length - 1]));
                        itemRepository.save(item);


                        shopReport.getSoldProducts().add(item);

                    } else if (words[0].matches("Za")) {
                        // System.out.println( words[words.length-1]);
                        //  String[] date = words[words.length-1].split("\\.");

                        //  LocalDateTime reportDate = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]),0,0,0);

                        //  shopReport.setCreatedOn(reportDate);
                    } else if (words[0].matches("Pozycji:")) {
                        shopReport.setTransactionsNumber(Integer.parseInt(words[1]));
                        shopReport.setEarnings(Double.parseDouble(words[words.length - 1]));

                    }
                }

            }

        }
    }
}
