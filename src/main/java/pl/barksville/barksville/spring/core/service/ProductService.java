package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.entities.data.Product;

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
}
