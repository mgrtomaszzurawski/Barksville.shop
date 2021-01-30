# Shop Reports
## Quick access

[Main Page](../../README.md)

[Service](#Service)

[Entities](#Entities)

[Data to Object Classes](#Data-to-Object-Classes)


## Controller

### Class body with list of methods.
First getter [chooseShopReport](#chooseShopReport) redirect user to page with file upload button and [save](#uploadReportFile) to save the report to database.

Second get mapping [viewShopReportList](#shopReportList
) shows list of existed shop reports with buttons to [download](#downloadFile) orginal pdf file and [view](#viewShopReport) button to show data on site.

```java
@Slf4j
@Controller
@RequestMapping("/admin/shop-report")
public class ShopReportController {

    private final ShopReportService shopReportService;

    public ShopReportController(ShopReportService shopReportService) {
        this.shopReportService = shopReportService;
    }

    @GetMapping
    public String chooseShopReport(Model model, Principal principal) {}

    @PostMapping(params = {"upload"})
    public String uploadReportFile(@RequestParam MultipartFile file, Principal principal) throws IOException {}

    @GetMapping(value="/list")
    public String shopReportList(Model model, String reportDate) {}

    @PostMapping(value = "/shop-report-view")
    public String viewShopReport(Model model, String reportDate) {}

    @PostMapping(value = "download", params = {"download"})
    public ResponseEntity<Resource> downloadFile(String reportDate) {}

}

```
[Return to top](#Shop-Reports)

### chooseShopReport

```java
 @GetMapping
    public String chooseShopReport(Model model, Principal principal) {


        return "adminPanel/shopReport";
    }

```

![Shop report](shop%20report.png)

[Return to top](#Shop-Reports)

### uploadReportFile

```java
 @PostMapping(params = {"upload"})
    public String uploadReportFile(@RequestParam MultipartFile file, Principal principal) throws IOException {

        shopReportService.createShopReport(file, principal.getName());

        return "redirect:/admin/panel";
    }
```

[Return to top](#Shop-Reports)

[Go to Service](#Service)

### shopReportList

```java
 @GetMapping(value="/list")
    public String shopReportList(Model model, String reportDate) {

        model.addAttribute("shopReportList", shopReportService.findAll());
        return "adminPanel/shopReportList";
    }
```
![Shop report list view](shop%20report%20list%20view.png)

[Return to top](#Shop-Reports)

[Go to Service](#Service)

### downloadFile
```java
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
```
![Shop report download](shop%20report%20download.png)


[Return to top](#Shop-reports)



### viewShopReport

```java
 @PostMapping(value = "/shop-report-view")
    public String viewShopReport(Model model, String reportDate) {

        model.addAttribute("shopReport", shopReportService.getShopReportByDate(LocalDate.parse(reportDate)));
        return "adminPanel/shopReportView";
    }
```

![Shop report view](shop%20report%20view.png)

[Return to top](#Shop-Reports)

[Go to Service](#Service)

## Service
### Class body with list of methods.
Method createShopReport will be execiuted on [saving](#uploadReportFile) new report. This function calls parsePDF and  createShopReportByShopReportDTO. First one will create ShopReportDTO with data from the file and second one will create ShopReport to save it in database. isValidProfileFile checks if provided pdf file is valid.

Method findAll will return input for [viewShopReportList](#shopReportList
) and getShopReportByDate for [viewShopReport](#viewShopReport).

```java

@Service
public class ShopReportService {
    private final ShopReportRepository shopReportRepository;
    private final ProductService productService;
    private final ItemService itemService;
    

    public ShopReportService(ShopReportRepository shopReportRepository, ProductService productService, ItemService itemService, ReportsService reportsService) {
        this.shopReportRepository = shopReportRepository;
        this.productService = productService;
        this.itemService = itemService;
    }

    @Transactional
    public void createShopReport(MultipartFile file, String oprName) throws IOException {}

    @Transactional
    public void createShopReportByShopReportDTO(ShopReportDTO shopReportDTO) {}

    private boolean isValidProfileFile(ShopReportScanFileDTO shopReportScanFile) {}


    private void parsePDF(ShopReportDTO shopReportDTO, MultipartFile file) throws IOException {}

    public ShopReport getShopReportByDate(LocalDate reportDate) {}

    public List<ShopReport> findAll() {}
}
```
[Return to top](#Shop-reports)

## Entities

### ShopReport
```java
@Entity
@Table(name = "Shop")
@Getter
@Setter
@ToString
public class ShopReport extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany
    private List<Item> soldProducts;

    @Column(name = "transactions_number")
    private Integer transactionsNumber;

    private Double earnings;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "shop_report_scan_file_id")
    private ShopReportScanFile shopReportScanFile;

    private String opr;


}

```
[Return to top](#Shop-reports)

### ShopReportScanFile
```java
@Entity
@Table(name = "shop_report_scan_file")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString(exclude = {"data"})
public class ShopReportScanFile extends BaseEntity {

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
[Return to top](#Shop-reports)
### Item
```java
@Entity
@Table(name = "Items")
@Getter
@Setter
@ToString(exclude = "product")
public class Item extends BaseEntity {

    @ManyToOne
    private Product product;

    private Double quantity;

    //cena moze sie roznic od ceny w Product np. przez promocje
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
[Return to top](#Shop-reports)

## Data to Object Classes

### ShopReportDTO
```java
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShopReportDTO {

    private List<ItemDTO> soldProducts;

    private String name;

    private LocalDate date;

    private Integer transactionsNumber;

    private Double earnings;

    private ShopReportScanFileDTO shopReportScanFile;

    private String opr;
}


```
[Return to top](#Shop-reports)
### ItemDTO
```java
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

[Return to top](#Shop-reports)

### ShopReportScanFileDTO
```java
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"data"})
public class ShopReportScanFileDTO {

    private String fileName;

    private String contentType;

    private byte[] data;
}
```
[Return to top](#Shop-reports)
### ItemDTO
```java
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
[Return to top](#Shop-reports)
