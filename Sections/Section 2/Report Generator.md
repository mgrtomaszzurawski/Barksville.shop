# Report Generator
[Main Page](../../README.md)
![DB Diagram](https://cdn.pixabay.com/photo/2017/06/16/07/26/under-construction-2408059_1280.png)
## Controller
[Return to top](#Report-Generator)
## Service
[Return to top](#Report-Generator)

## Entities
### DayReport
```java
@Entity
@Table(name = "day_report")
@Getter
@Setter
@ToString
public class DayReport extends BaseEntity {

    @Column(unique = true, nullable = false, name = "report_date")
    private LocalDate reportDate;

    @Column(unique = true, nullable = false, name = "report_name")
    private String reportName;

    @OneToOne
    private ShopReport shopReport;

    @Column(nullable = false, name = "net_income")
    private double netIncome;

    @Column(nullable = false, name = "gross_income")
    private double grossIncome;

    @Column(nullable = false)
    private double expenses;

    @Column(name="is_correct")
    private Boolean isCorrect;

    @OneToMany(cascade=CascadeType.REMOVE)
    @Column( nullable = false, name = "sold_item_report_list")
    private List<SoldItemReport> soldItemReportList;
}
```
[Return to top](#Report-Generator)

### MonthReport
```java
@Entity
@Table(name = "month_reports")
@Getter
@Setter
@ToString
public class MonthReport extends BaseEntity {

    @Column(unique = true, nullable = false, name = "report_date")
    private LocalDate reportDate;

    @Column(unique = true, nullable = false, name = "report_name")
    private String reportName;

    @Column(nullable = false, name = "net_income")
    private double netIncome;

    @Column(nullable = false, name = "gross_income")
    private double grossIncome;

    @Column(nullable = false)
    private double expenses;

    @OneToMany
    @Column(unique = true, nullable = false, name = "day_report_list")
    private List<DayReport> dayReportList;
}
```
[Return to top](#Report-Generator)


## Data to Object Classes
[Return to top](#Report-Generator)


