# Incoice creator
[Main Page](../../README.md)
![DB Diagram](https://cdn.pixabay.com/photo/2017/06/16/07/26/under-construction-2408059_1280.png)
## Controller
To create and view invoices there are two separate controller classes: InvoiceController and InvoiceViewController.

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

### prepareInvoiceForm
This page will allow user to fill basic information about date, company, invoice ID and pay sum for verification.

```java
@GetMapping
    public String prepareInvoiceForm() {
        return "adminPanel/invoiceCreate";
    }
```
![Basic data invoice form](.png)

[Return to top](#Incoice-creator)

### createInvoice
This is the save button on a screenshot above.

```java
 @PostMapping(params = {"upload"})
    public String createInvoice(Principal principal, String invoiceNumber, String company, String invoiceDate, String cost) {

        invoiceService.createInvoiceDTOWithoutScanAndItems(invoiceNumber, principal.getName(), company, LocalDate.parse(invoiceDate), cost);

        return "redirect:/admin/invoice/addProduct";
    }
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)
### addProductToInvoice


```java
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
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)
### addProductToInvoice


```java
@PostMapping(value = "/addProduct", params = {"upload"})
    public String addProductToInvoice(String name, Double price, Double quantity, Double vat,Boolean isDivided,Integer parts) {
        invoiceService.addProduct(name, price, quantity, vat, isDivided, parts);
        return "redirect:/admin/invoice/addProduct";
    }
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)
### deleteProductToInvoice


```java
@PostMapping(value = "/addProduct", params = {"delete"})
    public String deleteProductToInvoice(String name) {
        invoiceService.deleteProduct(name);
        return "redirect:/admin/invoice/addProduct";
    }
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)
### 


```java
    @PostMapping(value = "/addProduct", params = {"save"})
    public String saveProductsToInvoice() {
        double sum = invoiceService.getInvoiceComponent().getInvoiceDTO().getBoughtProducts().stream().map(p -> p.getPrice() * p.getQuantity()* p.getParts()).mapToDouble(p -> p).sum();
      invoiceService.getInvoiceComponent().getInvoiceDTO().setCost(sum);
            return "redirect:/admin/invoice/scanUpload";
    }
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)
### 


```java
    @GetMapping("/scanUpload")
    public String ScanToInvoice(Model model) {
        model.addAttribute("scan", invoiceService.getInvoiceComponent().getInvoiceDTO().getInvoiceScanFile());
        return "adminPanel/invoiceScanUpload";
    
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)
### 


```java
@PostMapping(value = "/scanUpload", params = {"upload"})
    public String addScanToInvoice(@RequestParam MultipartFile file) throws IOException {
        invoiceService.addScan(file);
        return "redirect:/admin/invoice/scanUpload";
    }
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)
### 


```java
@PostMapping(value = "/scanUpload", params = {"delete"})
    public String deleteInvoiceScan() {
        invoiceService.deleteScan();
        return "redirect:/admin/invoice/scanUpload";
    }
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)
### 


```java
 @PostMapping(value = "/scanUpload", params = {"next"})
    public String checkInvoice() {
        invoiceService.save();

        return "adminPanel/invoiceSaved";
    }
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)

### 


```java
 @GetMapping("/checkProducts")
    public String checkProductInInvoice(Model model) {
        List<ProductDTO> existing = invoiceService.getListOfExistingProducts();
        List<ProductDTO> nonExisting = invoiceService.getListOfNonExistingProducts(existing);
        existing.removeIf(product -> product.getSellPrice() != null);


        model.addAttribute("existing", existing);
        model.addAttribute("nonExisting", nonExisting);

        return "adminPanel/invoiceCheckProduct";
    }
```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)


### 


```java
    @PostMapping(value = "/checkProducts", params = {"upload"})
    public String updateProducts(String name, String price) throws IOException {
        //  invoiceService.createProductsBaseOnInvoice(nonExisting);
        invoiceService.addPriceToProductDTO(name, price);
        return "redirect:/admin/invoice/checkProducts";
    }

```

![Basic data invoice form](.png)

[Return to top](#Incoice-creator)


### 


```java

    @PostMapping(value = "/checkProducts", params = {"save"})
    public String saveInvoice() {
        invoiceService.save();

        return "adminPanel/invoiceSaved";
    }
```

![Basic data invoice form](.png)

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
