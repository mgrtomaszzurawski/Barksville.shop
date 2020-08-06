package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.dto.data.ProductDTO;
import pl.barksville.barksville.spring.dto.data.ProductInvoicePriceDTO;
import pl.barksville.barksville.spring.model.dal.repositories.ProductIvoicePriceRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.entities.data.Product;
import pl.barksville.barksville.spring.model.entities.data.ProductInvoicePrice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductIvoicePriceRepository productIvoicePriceRepository;


    public ProductService(ProductRepository productRepository, ProductIvoicePriceRepository productIvoicePriceRepository) {
        this.productRepository = productRepository;
        this.productIvoicePriceRepository = productIvoicePriceRepository;
    }

    public List<Product> allActiveProducts() {
        return productRepository.findAllByStateIsTrue();
    }


    public ProductDTO createProductDTOBaseOnInvoice(String name, Double price, Double quantity) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setName(name);

        productDTO.setState(Boolean.FALSE);

        List<ProductInvoicePriceDTO> invoicePriceDTOList = new ArrayList<>();
        ProductInvoicePriceDTO productInvoicePriceDTO = new ProductInvoicePriceDTO();
        productInvoicePriceDTO.setInvoicePrice(price);
        productInvoicePriceDTO.setQuantity(quantity);
        invoicePriceDTOList.add(productInvoicePriceDTO);

        productDTO.setInvoicePriceList(invoicePriceDTOList);

        productDTO.setQuantity(quantity);

        productDTO.setMinimalQuantity(0.);

        productDTO.setTotalBoughtQuantity(quantity);

        productDTO.setTotalSoldQuantity(0.);

        productDTO.setTotalGrossIncome(0.);

        productDTO.setTotalExpenses(price * quantity);

        productDTO.setAliasNames(new ArrayList<String>());

        return productDTO;
    }

    public Product productByName(String name) {
        if (productRepository.existsByName(name)) {
            return productRepository.findByName(name);
        } else {
            List<Product> productList = productRepository.findAll();

            for (Product product : productList
            ) {
                for (String aliasName :
                        product.getAliasNames()) {
                    if (aliasName.equals(name)) {
                        return product;
                    }
                }
            }
        }
        //it should not return it!!
        return new Product();

    }

    public boolean isExistByName(String name) {
        if (productRepository.existsByName(name)) {
            return true;
        }

        List<Product> productList = productRepository.findAll();

        for (Product product : productList
        ) {
            for (String aliasName :
                    product.getAliasNames()) {
                if (aliasName.equals(name)) {
                    return true;
                }
            }
        }

        return false;

        //return productRepository.existsByName(name);
    }

    public void createProduct(String name, Boolean state, Double price, Double quantity, String invoiceNumber, Double sellPrice) {
        Product product = new Product();
        product.setName(name);
        product.setState(state);

        List<ProductInvoicePrice> invoicePriceList = new ArrayList<>();

        ProductInvoicePrice productInvoicePrice = new ProductInvoicePrice();
        productInvoicePrice.setInvoicePrice(price);
        productInvoicePrice.setQuantity(quantity);
        productInvoicePrice.setInvoiceNumber(invoiceNumber);
        invoicePriceList.add(productInvoicePrice);
        productIvoicePriceRepository.save(productInvoicePrice);

        product.setInvoicePriceList(invoicePriceList);

        product.setSellPrice(sellPrice);

        product.setQuantity(quantity);

        product.setMinimalQuantity(0.);

        product.setTotalBoughtQuantity(quantity);

        product.setTotalSoldQuantity(0.);

        product.setTotalGrossIncome(0.);

        product.setTotalExpenses(price * quantity);

        product.setAliasNames(new ArrayList<String>());

        productRepository.save(product);
    }

    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public List<String> allProductsNames() {
        return productRepository.findAll().stream().map(Product::getName).collect(Collectors.toList());
    }

    public void updateProductByInvoice(String name, Double quantity, Double price) {
        Product product = productRepository.findByName(name);
        Double sum = product.getQuantity();
        sum += quantity;
        Double totalSum = product.getTotalBoughtQuantity();
        totalSum += quantity;
        Double totalExpenses = product.getTotalExpenses();
        totalExpenses += price * quantity;

        product.setQuantity(sum);
        product.setTotalBoughtQuantity(totalSum);
        product.setTotalExpenses(totalExpenses);
        productRepository.save(product);
        //  productRepository.updateQuantity(name,sum);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public ProductDTO getProductDTOByProductName(String productName) {
        Product product = productRepository.findByName(productName);

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName(product.getName());

        productDTO.setQuantity(product.getQuantity());

        productDTO.setRating(product.getRating());

        productDTO.setSellPrice(product.getSellPrice());

        productDTO.setState(product.getState());

        productDTO.setDescription(product.getDescription());

        List<ProductInvoicePriceDTO> productInvoicePriceDTOList = new ArrayList<>();
        for (ProductInvoicePrice productInvoicePrice : product.getInvoicePriceList()
        ) {

            ProductInvoicePriceDTO productInvoicePriceDTO = new ProductInvoicePriceDTO();
            productInvoicePriceDTO.setInvoiceNumber(productInvoicePrice.getInvoiceNumber());
            productInvoicePriceDTO.setInvoicePrice(productInvoicePrice.getInvoicePrice());
            productInvoicePriceDTO.setQuantity(productInvoicePrice.getQuantity());

            productInvoicePriceDTOList.add(productInvoicePriceDTO);

        }

        productDTO.setInvoicePriceList(productInvoicePriceDTOList);

        return productDTO;
    }

    public void updateProductByShopReport(String name, Double quantity, Double price) {
        Product product = productRepository.findByName(name);
        Double productQuantity = product.getQuantity() - quantity;
        Double productTotalSoldQuantity = product.getTotalSoldQuantity() + quantity;
        Double productTotalGrossIncome = product.getTotalGrossIncome() + quantity * price;

        product.setQuantity(productQuantity);
        product.setTotalSoldQuantity(productTotalSoldQuantity);
        product.setTotalGrossIncome(productTotalGrossIncome);


        productRepository.save(product);
    }

    public Product getProductById(Long id) {
       return productRepository.findById(id).get();
    }
}
