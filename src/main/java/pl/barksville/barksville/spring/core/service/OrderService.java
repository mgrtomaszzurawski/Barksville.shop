package pl.barksville.barksville.spring.core.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.barksville.barksville.spring.model.dal.repositories.OrderRepository;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.data.Order;
import pl.barksville.barksville.spring.model.entities.data.Product;
import pl.barksville.barksville.spring.session.OrderComponent;

import java.util.List;

@Service
public class OrderService {
    private final OrderComponent orderComponent;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final UserEntityService userEntityService;

    public OrderService(OrderComponent orderComponent,
                        OrderRepository orderRepository,
                        ProductService productService,
                        ItemService itemService,
                        UserEntityService userEntityService) {

        this.orderComponent = orderComponent;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.itemService = itemService;
        this.userEntityService = userEntityService;
    }

    public Product getProductById(Long id) {
        return productService.getOne(id);
    }

    public void addItemToOrder(Item item) {
        orderComponent.getOrder().getSoldProducts().add(item);
    }

    public List<Item> getSoldProducts() {
        return orderComponent.getOrder().getSoldProducts();
    }

    public void deleteProduct(String name) {
        orderComponent.getOrder().getSoldProducts().removeIf(item -> item.getProduct().getName().equals(name));
    }
@Transactional
    public void addOrder(String name) {

        for (Item item : orderComponent.getOrder().getSoldProducts()) {
            itemService.save(item);
        }



        orderRepository.save(orderComponent.getOrder());
        userEntityService.findByUsername(name).getOrderSet().add(orderComponent.getOrder());
    }

    public List<Order> getUserOrders(String name) {
        return orderRepository.getOrderByUserEntity_Username(name);
    }


    public List<Item> getProductListByOrderId(Long id) {
        return orderRepository.getOne(id).getSoldProducts();
    }
}
