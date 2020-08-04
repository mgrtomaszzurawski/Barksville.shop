package pl.barksville.barksville.spring.core.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.barksville.barksville.spring.dto.data.ItemDTO;
import pl.barksville.barksville.spring.dto.data.ShopReportDTO;
import pl.barksville.barksville.spring.dto.data.ShopReportScanFileDTO;
import pl.barksville.barksville.spring.model.dal.repositories.ItemRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductIvoicePriceRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ShopReportRepository;
import pl.barksville.barksville.spring.model.entities.data.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopReportService {
    private final ShopReportRepository shopReportRepository;
    private final ProductService productService;
    private final ItemService itemService;
    private final ReportsService reportsService;

    public ShopReportService(ShopReportRepository shopReportRepository, ProductRepository productRepository, ItemRepository itemRepository, ProductIvoicePriceRepository productIvoicePriceRepository, ProductService productService, ItemService itemService, ReportsService reportsService) {
        this.shopReportRepository = shopReportRepository;
        this.productService = productService;
        this.itemService = itemService;
        this.reportsService = reportsService;
    }

@Transactional
    public void createShopReport(MultipartFile file,String oprName) throws IOException {

        ShopReportDTO newReport = new ShopReportDTO();
        List<ItemDTO> itemList = new ArrayList<>();
        newReport.setSoldProducts(itemList);


        ShopReportScanFileDTO shopReportScanFile = new ShopReportScanFileDTO();

        shopReportScanFile.setContentType(file.getContentType());
        shopReportScanFile.setFileName(file.getOriginalFilename());
        shopReportScanFile.setData(file.getBytes());

        if (isValidProfileFile(shopReportScanFile)) {
            newReport.setShopReportScanFile(shopReportScanFile);
            parsePDF(newReport, file);
            newReport.setOpr(oprName);

            createShopReportByShopReportDTO(newReport);

        }
    }
@Transactional
    private void createShopReportByShopReportDTO(ShopReportDTO shopReportDTO) {

        ShopReport shopReport = new ShopReport();

        shopReport.setDate(shopReportDTO.getDate());
        shopReport.setEarnings(shopReportDTO.getEarnings());
        shopReport.setName(shopReportDTO.getName());
        shopReport.setOpr(shopReportDTO.getOpr());
        shopReport.setTransactionsNumber(shopReportDTO.getTransactionsNumber());

        ShopReportScanFile shopReportScanFile = new ShopReportScanFile();
        shopReportScanFile.setContentType(shopReportDTO.getShopReportScanFile().getContentType());
        shopReportScanFile.setData(shopReportDTO.getShopReportScanFile().getData());
        shopReportScanFile.setFileName(shopReportDTO.getShopReportScanFile().getFileName());

        shopReport.setShopReportScanFile(shopReportScanFile);

        List<Item> soldProductList = new ArrayList<>();

        for (ItemDTO itemDTO:shopReportDTO.getSoldProducts()
             ) {
           soldProductList.add(itemService.itemDTOToItem(itemDTO));
        }

        shopReport.setSoldProducts(soldProductList);



        shopReportRepository.save(shopReport);


    }

    private boolean isValidProfileFile(ShopReportScanFileDTO shopReportScanFile) {
        if (shopReportScanFile.getContentType() == null) return false;
        if (shopReportScanFile.getFileName() == null || shopReportScanFile.getFileName().isEmpty()) return false;
        if (shopReportScanFile.getData() == null) return false;
        return true;
    }


    private void parsePDF(ShopReportDTO shopReportDTO, MultipartFile file) throws IOException {
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

                        ItemDTO itemDTO = new ItemDTO();

                        //tworzenie produktu
                        if(!productService.isExistByName(name.toString())) {
                            Product product = new Product();

                            //   ProductInvoicePrice productInvoicePrice = new ProductInvoicePrice();
                            //    productInvoicePrice.setInvoicePrice(Double.parseDouble(words[words.length - 1]));
                            //    productInvoicePrice.setQuantity(Double.parseDouble(words[words.length - 2]));
                            //    productIvoicePriceRepository.save(productInvoicePrice);
                            List<ProductInvoicePrice> productInvoicePriceList = new ArrayList<>();
                            //   productInvoicePriceList.add(productInvoicePrice);
                            product.setInvoicePriceList(productInvoicePriceList);

                            product.setName(name.toString());
                            product.setQuantity(0.);
                            product.setState(Boolean.TRUE);
                            product.setSellPrice(0.);

                            productService.saveProduct(product);
                            //koniec tworzenia prodkutku
                        }
                        itemDTO.setProduct(productService.getProductDTOByProductName(name.toString()));


                        itemDTO.setQuantity(Double.parseDouble(words[words.length - 2]));
                        productService.changeQuantityOfProduct(name.toString(),itemDTO.getQuantity());


                        itemDTO.setPrice(Double.parseDouble(words[words.length - 1]));

                        itemDTO.setIsSold(true);

                        shopReportDTO.getSoldProducts().add(itemDTO);

                    } else if (words[0].matches("Za")) {
                        // System.out.println( words[words.length-1]);
                        String[] date = words[words.length-1].split("\\.");
                        shopReportDTO.setName(date[0]+"-"+date[1]+"-"+date[2]);
                        shopReportDTO.setDate(LocalDate.parse(date[2]+"-"+date[1]+"-"+date[0]));

                        //  LocalDateTime reportDate = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]),0,0,0);

                        //  shopReportDTO.setCreatedOn(reportDate);
                    } else if (words[0].matches("Pozycji:")) {
                        shopReportDTO.setTransactionsNumber(Integer.parseInt(words[1]));
                        shopReportDTO.setEarnings(Double.parseDouble(words[words.length - 1]));

                    }
                }

            }

        }
    }
}


