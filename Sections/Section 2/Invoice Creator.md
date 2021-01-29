# Incoice creator
[Main Page](../../README.md)
![DB Diagram](https://cdn.pixabay.com/photo/2017/06/16/07/26/under-construction-2408059_1280.png)
## Controller
For invoices there are two controllers, one for invoice form and second for invoice view.

## InvoiceController 
```Java
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
    public String prepareInvoiceForm() {}

    @PostMapping(params = {"upload"})
    public String createInvoice(Principal principal, String invoiceNumber, String company, String invoiceDate, String cost) {}

    @GetMapping("/addProduct")
    public String addProductToInvoice(Model model) {}

    @PostMapping(value = "/addProduct", params = {"upload"})
    public String addProductToInvoice(String name, Double price, Double quantity, Double vat,Boolean isDivided,Integer parts) {}

    @PostMapping(value = "/addProduct", params = {"delete"})
    public String deleteProductToInvoice(String name) {}

    @PostMapping(value = "/addProduct", params = {"save"})
    public String saveProductsToInvoice() {}

    @GetMapping("/scanUpload")
    public String ScanToInvoice(Model model) {}

    @PostMapping(value = "/scanUpload", params = {"upload"})
    public String addScanToInvoice(@RequestParam MultipartFile file) throws IOException {}

    @PostMapping(value = "/scanUpload", params = {"delete"})
    public String deleteInvoiceScan() {}

    @PostMapping(value = "/scanUpload", params = {"next"})
    public String checkInvoice() {}

    @GetMapping("/checkProducts")
    public String checkProductInInvoice(Model model) {}

    @PostMapping(value = "/checkProducts", params = {"upload"})
    public String updateProducts(String name, String price) throws IOException {}

    @PostMapping(value = "/checkProducts", params = {"save"})
    public String saveInvoice() {}
}
```



[Return to top](#Incoice-creator)
## Service
[Return to top](#Incoice-creator)

## Entities
### Invoice
```java
@Entity
@Table(name = "Invoices")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Invoice extends BaseEntity {

    @Column(nullable = false)
    @OneToMany
    private List<Item> boughtProducts;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double cost;

    @Column(name = "invoice_number",nullable = false, unique = true)
    private String invoiceNumber;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "invoice_scan_file_id")
    private InvoiceScanFile invoiceScanFile;

    @Column(nullable = false)
    private String opr;
}
```
[Return to top](#Incoice-creator)
### InvoiceScanFile
```Java
@Entity
@Table(name = "invoice_scan_file")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString(exclude = {"data"})
public class InvoiceScanFile extends BaseEntity {

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Lob
    @Basic(fetch = FetchType.LAZY, optional = false)
    @Column(name = "data", nullable = false, columnDefinition = "MEDIUMBLOB")
    private byte[] data;
}
```
[Return to top](#Incoice-creator)

### Item
```Java
@Entity
@Table(name = "Items")
@Getter
@Setter
@ToString(exclude = "product")
public class Item extends BaseEntity {

    @ManyToOne
    private Product product;

    private Double quantity;

    private Double price;

    @Column(name="net_price")
    private Double netPrice;

    private Double vat;

    @Column(name="is_divided")
    private Boolean isDivided;

    private Integer parts;

    @Column(nullable = false,name = "is_sold")
    private Boolean isSold;

    @Column(name="left_items")
    private Double leftItems;
}
```
[Return to top](#Incoice-creator)

## Data to Object Classes
### InvoiceDTO
```java
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class InvoiceDTO {
    private List<ItemDTO> boughtProducts;

    private Double cost;
    private String company;
    private LocalDate date;

    private String invoiceNumber;

    private InvoiceScanFileDTO invoiceScanFile;

    private String opr;
}
```
[Return to top](#Incoice-creator)

### InvoiceScanFileDTO
```Java
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"data"})
public class InvoiceScanFileDTO {

    private String fileName;

    private String contentType;

    private byte[] data;
}
```
[Return to top](#Incoice-creator)

### ItemDTO
```Java
@Getter
@Setter
@ToString(exclude = "product")
@EqualsAndHashCode
public class ItemDTO {

    private ProductDTO product;

    private Double quantity;

    private Double price;

    private Double netPrice;
    
    private Double vat;
    
    private Boolean isDivided;
    
    private Integer parts;
    
    private Boolean isSold;

    private Double leftItems;
}
```
[Return to top](#Incoice-creator)
