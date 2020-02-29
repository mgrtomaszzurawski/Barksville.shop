package pl.barksville.barksville.spring.core.service;


import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.ItemRepository;
import pl.barksville.barksville.spring.model.dal.repositories.OrderRepository;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.dal.repositories.UserEntityRepository;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.data.Order;
import pl.barksville.barksville.spring.model.entities.data.Product;
import pl.barksville.barksville.spring.session.OrderComponent;

import java.util.List;

@Service
public class OrderService {
    private final OrderComponent orderComponent;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserEntityRepository userEntityRepository;

    public OrderService(OrderComponent orderComponent, ProductRepository productRepository, OrderRepository orderRepository, ItemRepository itemRepository, UserEntityRepository userEntityRepository) {
        this.orderComponent = orderComponent;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.getOne(id);
    }

    public void addItemToOrder(Item item) {
        orderComponent.getOrder().getSoldProducts().add(item);
    }

    public List<Item> getSoldProducts() {
        return orderComponent.getOrder().getSoldProducts();
    }

    public void deleteProduct(String name) {
        orderComponent.getOrder().getSoldProducts().removeIf(item->item.getProduct().getName().equals(name));
    }

    public void addOrder(String name) {

        for(Item item:orderComponent.getOrder().getSoldProducts()){
            itemRepository.save(item);
        }

        Order order =orderComponent.getOrder();
        order.setUserEntity(userEntityRepository.findByUsername(name));

        orderRepository.save(orderComponent.getOrder());
    }

    public List<Order> getUserOrders(String name) {
      return  orderRepository.getOrderByUserEntity_Username(name);
    }


    public List<Item> getProductListByOrderId(Long id) {
      return  orderRepository.getOne(id).getSoldProducts();
    }
}
