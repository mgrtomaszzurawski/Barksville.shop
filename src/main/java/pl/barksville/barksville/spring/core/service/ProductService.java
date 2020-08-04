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

        productDTO.setQuantity(quantity);


        return productDTO;
    }

    public Product productByName(String name) {
        return productRepository.findByName(name);
    }

    public boolean isExistByName(String name) {
        return productRepository.existsByName(name);
    }

    public void createProduct(String name, Boolean state, List<ProductInvoicePriceDTO> invoicePriceListDTO, Double sellPrice, Double quantity) {
        Product product = new Product();
        product.setName(name);
        product.setState(state);

        List<ProductInvoicePrice> invoicePriceList = new ArrayList<>();

        for (ProductInvoicePriceDTO priceDTO : invoicePriceListDTO) {
            ProductInvoicePrice productInvoicePrice = new ProductInvoicePrice();
            productInvoicePrice.setInvoicePrice(priceDTO.getInvoicePrice());
            productInvoicePrice.setQuantity(priceDTO.getQuantity());
            productInvoicePrice.setInvoiceNumber(priceDTO.getInvoiceNumber());
            invoicePriceList.add(productInvoicePrice);
            productIvoicePriceRepository.save(productInvoicePrice);
        }

        product.setInvoicePriceList(invoicePriceList);

        product.setSellPrice(sellPrice);

        product.setQuantity(quantity);

        productRepository.save(product);
    }

    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public List<String> allProductsNames() {
        return productRepository.findAll().stream().map(Product::getName).collect(Collectors.toList());
    }

    public void addQuantityToProduct(String name, Double quantity) {
        Double sum = productRepository.findByName(name).getQuantity();
        sum += quantity;
        Product product = productRepository.findByName(name);
        product.setQuantity(sum);
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

    public void changeQuantityOfProduct(String name, Double quantity) {
        Double productQuantity=productRepository.findByName(name.toString()).getQuantity()-quantity;
        productRepository.findByName(name.toString()).setQuantity(productQuantity);
    }
}
