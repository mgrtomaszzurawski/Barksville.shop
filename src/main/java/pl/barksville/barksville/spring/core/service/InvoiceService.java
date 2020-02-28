package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.barksville.barksville.spring.dto.data.*;
import pl.barksville.barksville.spring.model.dal.repositories.InvoiceRepository;
import pl.barksville.barksville.spring.model.dal.repositories.InvoiceScanFileRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ItemRepository;
import pl.barksville.barksville.spring.model.entities.data.*;
import pl.barksville.barksville.spring.session.InvoiceComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceScanFileRepository invoiceScanFileRepository;
    private final InvoiceComponent invoiceComponent;
    private final ProductService productService;
    private final ItemRepository itemRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceScanFileRepository invoiceScanFileRepository, InvoiceComponent invoiceComponent, ProductService productService, ItemRepository itemRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceScanFileRepository = invoiceScanFileRepository;
        this.invoiceComponent = invoiceComponent;
        this.productService = productService;
        this.itemRepository = itemRepository;
    }

    public void createInvoiceDTOWithEmptyLists(String cost, String invoiceNumber, String opr) {


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

    public InvoiceComponent getInvoiceComponent() {
        return invoiceComponent;
    }

    public void addScan(MultipartFile file) throws IOException {
        InvoiceScanFileDTO invoiceScanFileDTO = new InvoiceScanFileDTO();
        invoiceScanFileDTO.setFileName(file.getOriginalFilename());
        invoiceScanFileDTO.setContentType(file.getContentType());
        invoiceScanFileDTO.setData(file.getBytes());

        invoiceComponent.getInvoiceDTO().getInvoiceScanFile().add(invoiceScanFileDTO);
    }

    public void deleteScan(String name) {
        invoiceComponent.getInvoiceDTO().getInvoiceScanFile().removeIf(scan -> scan.getFileName().equals(name));
    }

    public List<ProductDTO> getListOfExistingProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ItemDTO item : invoiceComponent.getInvoiceDTO().getBoughtProducts()) {
            if (productService.isExistByName(item.getProduct().getName())) {
                productDTOList.add(item.getProduct());
            }
        }
        return productDTOList;

    }


    public List<ProductDTO> getListOfNonExistingProducts(List<ProductDTO> existing) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ItemDTO item : invoiceComponent.getInvoiceDTO().getBoughtProducts()) {
            if (!existing.contains(item)) {
                productDTOList.add(item.getProduct());
            }
        }
        return productDTOList;
    }

    public void createProductsBaseOnInvoice(List<ProductDTO> nonExisting) {

        for (ProductDTO productDTO : nonExisting) {
            productService.createProduct(productDTO.getName(),
                    productDTO.getState(),
                    productDTO.getInvoicePriceList(),
                    productDTO.getSellPrice(),
                    productDTO.getQuantity());
        }
    }

    public void save() {
        Invoice invoice = new Invoice();

        invoice.setBoughtProducts(toItemList());

        invoice.setCost(invoiceComponent.getInvoiceDTO().getCost());

        invoice.setInvoiceNumber(invoiceComponent.getInvoiceDTO().getInvoiceNumber());

        invoice.setInvoiceScanFile(toScanFileList());

        invoice.setOpr(invoiceComponent.getInvoiceDTO().getOpr());

        invoiceRepository.save(invoice);

    }

    private List<InvoiceScanFile> toScanFileList() {

        List<InvoiceScanFile> invoiceScanFileList = new ArrayList<>();

        for(InvoiceScanFileDTO scanFileDTO:invoiceComponent.getInvoiceDTO().getInvoiceScanFile()){
            InvoiceScanFile invoiceScanFile = new InvoiceScanFile();
            invoiceScanFile.setFileName(scanFileDTO.getFileName());
            invoiceScanFile.setContentType(scanFileDTO.getContentType());
            invoiceScanFile.setData(scanFileDTO.getData());
            invoiceScanFileRepository.save(invoiceScanFile);
            invoiceScanFileList.add(invoiceScanFile);
        }
        return invoiceScanFileList;
    }

    private List<Item> toItemList() {
        List<Item> boughtProducts = new ArrayList<>();
        for(ItemDTO itemDTO: invoiceComponent.getInvoiceDTO().getBoughtProducts()){
            Item item = new Item();
            item.setPrice(itemDTO.getPrice());
            item.setProduct(productService.productByName(itemDTO.getProduct().getName()));
            item.setQuantity(itemDTO.getQuantity());
            itemRepository.save(item);
            boughtProducts.add(item);
        }
        return boughtProducts;
    }

    public void createProductBaseOnInvoice(String name, String price) {
        ProductDTO productDTO= new ProductDTO();
        List<ProductInvoicePriceDTO> productInvoicePriceDTOList = new ArrayList<>();

       for(ItemDTO item: invoiceComponent.getInvoiceDTO().getBoughtProducts()){
           if(item.getProduct().getName().equals(name)){
               productDTO=item.getProduct();
               ProductInvoicePriceDTO productInvoicePriceDTO = new ProductInvoicePriceDTO();
               productInvoicePriceDTO.setQuantity(item.getQuantity());
               productInvoicePriceDTO.setInvoicePrice(item.getPrice());
               productInvoicePriceDTOList.add(productInvoicePriceDTO);
               break;
           }
       }



        //TODO check if loop find productDTO
            productService.createProduct(productDTO.getName(),
                    Boolean.TRUE,
                   productInvoicePriceDTOList,
                    Double.parseDouble(price),
                    productDTO.getQuantity());
    }
}
