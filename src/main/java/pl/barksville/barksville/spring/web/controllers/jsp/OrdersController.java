package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.barksville.barksville.spring.model.dal.repositories.ProductRepository;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.data.Product;
import pl.barksville.barksville.spring.session.OrderComponent;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrderComponent orderComponent;
    private final ProductRepository productRepository;

    public OrdersController(OrderComponent orderComponent, ProductRepository productRepository) {
        this.orderComponent = orderComponent;
        this.productRepository = productRepository;
    }

    @PostMapping("/add-product")
    public String addrProduct(Long productId, @RequestParam(defaultValue = "1") Integer quantity) {
        Product product = productRepository.getOne(productId);
        //TODO: Przerobić aby aktualizować ilość produktu jeżeli jest już w zamówieniu
        Item item = new Item();
        item.setQuantity(quantity);
        item.setProduct(product);
        orderComponent.getOrder().getSoldProducts().add(item);
        return "redirect:/products";
    }
}
