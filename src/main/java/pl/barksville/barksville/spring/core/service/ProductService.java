package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.dto.data.ProductDTO;
import pl.barksville.barksville.spring.dto.data.ProductInvoicePriceDTO;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.entities.data.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> allActiveProducts(){
      return  productRepository.findAllByStateIsTrue();
    }

    public ProductDTO createProductDTOBaseOnInvoice (String name, String price, String quantity ){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setName(name);

        productDTO.setState(Boolean.FALSE);

        List<ProductInvoicePriceDTO> invoicePriceDTOList = new ArrayList<>();
        ProductInvoicePriceDTO productInvoicePriceDTO = new ProductInvoicePriceDTO();
        productInvoicePriceDTO.setInvoicePrice(Double.parseDouble(price));
        productInvoicePriceDTO.setQuantity(Double.parseDouble(quantity));
        invoicePriceDTOList.add(productInvoicePriceDTO);

        productDTO.setQuantity(Double.parseDouble(quantity));


        return  productDTO;
    }

    public Product productByName(String name){
        return productRepository.findByName(name);
    }

    public boolean isExistByName(String name) {
        return productRepository.existsByName(name);
    }
}
