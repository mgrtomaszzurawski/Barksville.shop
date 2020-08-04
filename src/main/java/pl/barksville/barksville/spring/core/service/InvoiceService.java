package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.barksville.barksville.spring.dto.data.*;
import pl.barksville.barksville.spring.model.dal.repositories.InvoiceRepository;
import pl.barksville.barksville.spring.model.dal.repositories.InvoiceScanFileRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ItemRepository;
import pl.barksville.barksville.spring.model.entities.data.*;
import pl.barksville.barksville.spring.session.InvoiceComponent;

import java.io.IOException;
import java.time.LocalDate;
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

    public void createInvoiceDTOWithoutScanAndItems(String invoiceNumber, String opr, String company, LocalDate invoiceDate, String cost) {


        //InvoiceDTO invoiceDTO = new InvoiceDTO();
        //invoiceComponent.setInvoiceDTO(invoiceDTO);

        invoiceComponent.getInvoiceDTO().setInvoiceNumber(invoiceNumber);
        invoiceComponent.getInvoiceDTO().setOpr(opr);
        invoiceComponent.getInvoiceDTO().setCompany(company);
        invoiceComponent.getInvoiceDTO().setDate(invoiceDate);
        invoiceComponent.getInvoiceDTO().setCost(Double.parseDouble(cost));


        invoiceComponent.getInvoiceDTO().setInvoiceScanFile(new InvoiceScanFileDTO());
        invoiceComponent.getInvoiceDTO().getInvoiceScanFile().setFileName("");

        List<ItemDTO> itemDTOList = new ArrayList<>();
        invoiceComponent.getInvoiceDTO().setBoughtProducts(itemDTOList);


    }

    public void addProduct(String name, Double netPrice, Double quantity, Double vat, Boolean isDivided, Integer parts) {
        ItemDTO itemDTO = new ItemDTO();


        Double productQuantity = quantity;
        Double price = Math.round((netPrice + netPrice * vat) * 100) / 100.;


        if (isDivided) {
            price = price / parts;
            productQuantity = quantity * parts;
        }

        itemDTO.setProduct(productService.createProductDTOBaseOnInvoice(name, netPrice, productQuantity));

        itemDTO.setQuantity(quantity);

        itemDTO.setNettoPrice(netPrice);

        itemDTO.setPrice(price);

        itemDTO.setVat(vat);

        itemDTO.setIsDivided(isDivided);

        itemDTO.setParts(parts);

        itemDTO.setIsSold(false);

        itemDTO.setLeftItems(quantity);

        invoiceComponent.getInvoiceDTO().getBoughtProducts().add(itemDTO);
    }

    private boolean isValidProfileFile(ShopReportScanFile shopReportScanFile) {

        //TODO

        if (shopReportScanFile.getContentType() == null) return false;
        if (shopReportScanFile.getFileName() == null || shopReportScanFile.getFileName().isEmpty()) return false;
        return shopReportScanFile.getData() != null;
    }


    public void deleteProduct(String name) {
        invoiceComponent.getInvoiceDTO().getBoughtProducts().removeIf(itemDTO -> itemDTO.getProduct().getName().equals(name));
    }

    public InvoiceComponent getInvoiceComponent() {
        return invoiceComponent;
    }

    public void addScan(MultipartFile file) throws IOException {
        String fileName = invoiceComponent.getInvoiceDTO().getCompany() + "-" + invoiceComponent.getInvoiceDTO().getInvoiceNumber();

        InvoiceScanFileDTO invoiceScanFileDTO = new InvoiceScanFileDTO();
        invoiceScanFileDTO.setFileName(fileName + ".pdf");
        invoiceScanFileDTO.setContentType(file.getContentType());
        invoiceScanFileDTO.setData(file.getBytes());

        invoiceComponent.getInvoiceDTO().setInvoiceScanFile(invoiceScanFileDTO);
    }

    public void deleteScan() {
        invoiceComponent.getInvoiceDTO().setInvoiceScanFile(null);
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
            if (!existing.contains(item.getProduct())) {
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

        invoice.setCompany(invoiceComponent.getInvoiceDTO().getCompany());

        invoice.setDate(invoiceComponent.getInvoiceDTO().getDate());

        invoice.setCost(invoiceComponent.getInvoiceDTO().getCost());

        invoice.setInvoiceNumber(invoiceComponent.getInvoiceDTO().getInvoiceNumber());

        if (!invoiceComponent.getInvoiceDTO().getInvoiceScanFile().getFileName().isEmpty())
            invoice.setInvoiceScanFile(toScanFile());

        invoice.setOpr(invoiceComponent.getInvoiceDTO().getOpr());


        invoiceRepository.save(invoice);


    }

    private InvoiceScanFile toScanFile() {


        InvoiceScanFileDTO scanFileDTO = invoiceComponent.getInvoiceDTO().getInvoiceScanFile();
        InvoiceScanFile invoiceScanFile = new InvoiceScanFile();


        invoiceScanFile.setFileName(scanFileDTO.getFileName());
        invoiceScanFile.setContentType(scanFileDTO.getContentType());
        invoiceScanFile.setData(scanFileDTO.getData());
        invoiceScanFileRepository.save(invoiceScanFile);

        return invoiceScanFile;
    }

    private List<Item> toItemList() {
        List<Item> boughtProducts = new ArrayList<>();
        for (ItemDTO itemDTO : invoiceComponent.getInvoiceDTO().getBoughtProducts()) {
            Item item = createItem(itemDTO);
            boughtProducts.add(item);
        }
        return boughtProducts;
    }


    public void addPriceToProductDTO(String name, String price) {
        for (ItemDTO item : invoiceComponent.getInvoiceDTO().getBoughtProducts()) {
            if (item.getProduct().getName().equals(name)) {
                item.getProduct().setSellPrice(Double.parseDouble(price));
            }
        }
    }

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceByInvoiceNumber(String invoiceNumber) {
        return invoiceRepository.getInvoiceByInvoiceNumber(invoiceNumber);
    }

    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Transactional
    public void deleteInvoiceByInvoiceNumber(String invoiceNumber) {
        invoiceRepository.deleteByInvoiceNumber(invoiceNumber);
    }

    @Transactional
    public void deleteInvoiceRowByInvoiceNumberAndRowID(String invoiceNumber, Long id) {
        invoiceRepository.getInvoiceByInvoiceNumber(invoiceNumber).getBoughtProducts().removeIf(row -> id.equals(row.getId()));
        itemRepository.deleteById(id);
    }

    public void updateInvoiceRowByInvoiceNumberAndRowID(String invoiceNumber, Long id, Double netPrice, Double quantity, Double vat, Boolean isDivided, Integer parts, Double price) {
        Item item = invoiceRepository.getInvoiceByInvoiceNumber(invoiceNumber).getBoughtProducts().stream().filter(row -> id.equals(row.getId())).findFirst().get();
        item.setNetPrice(netPrice);
        item.setQuantity(quantity);
        item.setVat(vat);
        item.setIsDivided(isDivided);
        item.setParts(parts);
        item.setPrice(price);
    }


    public void addRowToInvoice(String invoiceNumber, String name, Double netPrice, Double quantity, Double vat, Boolean isDivided, Integer parts, Double price) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setProduct(productService.createProductDTOBaseOnInvoice(name, netPrice, quantity));

        itemDTO.setQuantity(quantity);

        itemDTO.setNettoPrice(netPrice);

        itemDTO.setPrice(price);

        itemDTO.setVat(vat);

        itemDTO.setIsDivided(isDivided);

        itemDTO.setParts(parts);

        Item item = createItem(itemDTO);

        invoiceRepository.getInvoiceByInvoiceNumber(invoiceNumber).getBoughtProducts().add(item);


    }

    public Item createItem(ItemDTO itemDTO) {

        Item item = new Item();
        item.setPrice(itemDTO.getPrice());
        if (productService.isExistByName(itemDTO.getProduct().getName())) {
            item.setProduct(productService.productByName(itemDTO.getProduct().getName()));
            item.setQuantity(itemDTO.getQuantity());
            item.setNetPrice(itemDTO.getNettoPrice());
            item.setVat(itemDTO.getVat());
            item.setIsDivided(itemDTO.getIsDivided());
            item.setIsSold(itemDTO.getIsSold());
            item.setLeftItems(item.getQuantity());
            if (item.getIsDivided()) {
                item.setParts(itemDTO.getParts());
            } else {
                item.setParts(1);
            }


            productService.addQuantityToProduct(itemDTO.getProduct().getName(), itemDTO.getQuantity());
        } else {
            List<ProductInvoicePriceDTO> productInvoicePriceDTOList = new ArrayList<>();
            ProductInvoicePriceDTO productInvoicePriceDTO = new ProductInvoicePriceDTO();
            productInvoicePriceDTO.setInvoicePrice(itemDTO.getPrice());
            productInvoicePriceDTO.setQuantity(itemDTO.getQuantity());
            productInvoicePriceDTO.setInvoiceNumber(invoiceComponent.getInvoiceDTO().getInvoiceNumber());

            productInvoicePriceDTOList.add(productInvoicePriceDTO);

            productService.createProduct(itemDTO.getProduct().getName(), Boolean.TRUE, productInvoicePriceDTOList, itemDTO.getProduct().getSellPrice(), itemDTO.getQuantity());


            item.setProduct(productService.productByName(itemDTO.getProduct().getName()));
            item.setQuantity(itemDTO.getQuantity());
            item.setNetPrice(itemDTO.getNettoPrice());
            item.setVat(itemDTO.getVat());
            item.setIsDivided(itemDTO.getIsDivided());
            item.setLeftItems(item.getQuantity());
            if (item.getIsDivided()) {
                item.setParts(itemDTO.getParts());
            } else {
                item.setParts(1);
            }
        }
        itemRepository.save(item);
        return item;

    }

    @Transactional
    public void changeInvoiceData(String oldInvoiceNumber, String invoiceNumber, String company, String invoiceDate, Double cost, String opr) {

        invoiceRepository.getInvoiceByInvoiceNumber(oldInvoiceNumber).setCompany(company);
        invoiceRepository.getInvoiceByInvoiceNumber(oldInvoiceNumber).setDate(LocalDate.parse(invoiceDate));
        invoiceRepository.getInvoiceByInvoiceNumber(oldInvoiceNumber).setCost(cost);
        invoiceRepository.getInvoiceByInvoiceNumber(oldInvoiceNumber).setOpr(opr);
        invoiceRepository.getInvoiceByInvoiceNumber(oldInvoiceNumber).setInvoiceNumber(invoiceNumber);


    }
}
