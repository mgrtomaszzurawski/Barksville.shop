# Product information managment
[Main Page](../../README.md)
![DB Diagram](https://cdn.pixabay.com/photo/2017/06/16/07/26/under-construction-2408059_1280.png)
## Controller
[Return to top](#Product-information-managment)
## Service
[Return to top](#Product-information-managment)

## Entities
[Return to top](#Product-information-managment)
### Product
```Java

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean state;
 
    @OneToMany
    private List<ProductInvoicePrice> invoicePriceList;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(nullable = false)
    private Double quantity;

    private Double rating;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name="minimal_quantity",nullable = false)
    private Double minimalQuantity;

    @Column(name="total_bought_quantity",nullable = false)
    private Double totalBoughtQuantity;
    
    @Column(name="total_sold_quantity",nullable = false)
    private Double totalSoldQuantity;

    @Column(name="total_gross_income",nullable = false)
    private Double totalGrossIncome;

    @Column(name="total_expenses",nullable = false)
    private Double totalExpenses;

    @ElementCollection
    @Column(name="alias_names",nullable = false)
    private List<String> aliasNames;
}
```
[Return to top](#Product-information-managment)

### ProductInvoicePrice
```java
@Entity
@Table(name = "product_invoice_price")
@Getter
@Setter
@ToString
public class ProductInvoicePrice extends BaseEntity {

    @Column(nullable = false)
    private Double invoicePrice;

    @Column(nullable = false)
    private Double quantity;

    @Column(name="invoice_number", nullable = false)
    private String invoiceNumber;
}
```
[Return to top](#Product-information-managment)



## Data to Object Classes

### ProductDTO
```java
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductDTO {

    private String name;

    private Boolean state;

    private List<ProductInvoicePriceDTO> invoicePriceList;

    private Double sellPrice;

    private Double quantity;

    private Double rating;

    private String description;


    private Double minimalQuantity;

    private Double totalBoughtQuantity;

    private Double totalSoldQuantity;

    private Double totalGrossIncome;

    private Double totalExpenses;

    private List<String> aliasNames;
}
```

[Return to top](#Product-information-managment)

### ProductInvoicePriceDTO
```Java
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductDTO {

    private String name;

    private Boolean state;

    private List<ProductInvoicePriceDTO> invoicePriceList;

    private Double sellPrice;

    private Double quantity;

    private Double rating;

    private String description;

    private Double minimalQuantity;

    private Double totalBoughtQuantity;

    private Double totalSoldQuantity;

    private Double totalGrossIncome;

    private Double totalExpenses;

    private List<String> aliasNames;
}
```
[Return to top](#Product-information-managment)