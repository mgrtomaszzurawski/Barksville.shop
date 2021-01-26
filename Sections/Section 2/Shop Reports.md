# Shop Reports

[Main Page](../../README.md)
![DB Diagram](https://cdn.pixabay.com/photo/2017/06/16/07/26/under-construction-2408059_1280.png)
## Controller

### Class body and list of methods.
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

    @PostMapping(value = "/shop-report-view")
    public String viewShopReport(Model model, String reportDate) {}


    @PostMapping(value = "download", params = {"download"})
    public ResponseEntity<Resource> downloadFile(String reportDate) {}

    @GetMapping(value="/list")
    public String shopReportList(Model model, String reportDate) {}

}

```

### Get method
```java
 @GetMapping
    public String chooseShopReport(Model model, Principal principal) {


        return "adminPanel/shopReport";
    }

```

![DB Diagram](shop%20report.png)


### Post method
redirects to admin panel
```java
 @PostMapping(params = {"upload"})
    public String uploadReportFile(@RequestParam MultipartFile file, Principal principal) throws IOException {

        shopReportService.createShopReport(file, principal.getName());

        return "redirect:/admin/panel";
    }
```

### Shop report list view

```java
 @PostMapping(value = "/shop-report-view")
    public String viewShopReport(Model model, String reportDate) {

        model.addAttribute("shopReport", shopReportService.getShopReportByDate(LocalDate.parse(reportDate)));
        return "adminPanel/shopReportView";
    }
```

![DB Diagram](shop%20report%20list%20view.png)


### Download shop report pdf file
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
![DB Diagram](shop%20report%20download.png)

### Shop report view

```java
 @GetMapping(value="/list")
    public String shopReportList(Model model, String reportDate) {

        model.addAttribute("shopReportList", shopReportService.findAll());
        return "adminPanel/shopReportList";
    }
```
![DB Diagram](shop%20report%20view.png)


[Return to top](#Shop-reports)



## Service
[Return to top](#Shop-reports)

## DTO
[Return to top](#Shop-reports)
## Entites
[Return to top](#Shop-reports)


