package pl.barksville.barksville.spring.core.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.data.Product;
import pl.barksville.barksville.spring.session.OrderComponent;

@Service
public class OrderService {
    private final OrderComponent orderComponent;
    private final ProductRepository productRepository;

    public OrderService(OrderComponent orderComponent, ProductRepository productRepository) {
        this.orderComponent = orderComponent;
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id){
        return productRepository.getOne(id);
    }

    public void addItemToOrder(Item item){
        orderComponent.getOrder().getSoldProducts().add(item);
    }
}
