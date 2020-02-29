package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.barksville.barksville.spring.core.service.OrderService;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.data.Order;
import pl.barksville.barksville.spring.model.entities.data.Product;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public String showOrders(Model model, Principal principal){

        List<Order> orders = orderService.getUserOrders(principal.getName());

        model.addAttribute("orders",orders);

        return "products/showOrders";
    }


    @PostMapping(value ="/lookupOrder",params = {"open"})
    public String deleteProductInOrder(Model model,Long id){
        List<Item> soldProducts = orderService.getProductListByOrderId(id);

        model.addAttribute("soldProducts",soldProducts);

        return "products/lookupOrder";

    }

    @GetMapping("/lookupOrder")
    public String lookupOrders(Model model, Principal principal){

        List<Order> orders = orderService.getUserOrders( principal.getName());

        model.addAttribute("orders",orders);

        return "products/showOrders";
    }






    @PostMapping("/add-product")
    public String addrProduct(Long productId, @RequestParam(defaultValue = "1") Double quantity) {
        Product product = orderService.getProductById(productId);
        //TODO: Przerobić aby aktualizować ilość produktu jeżeli jest już w zamówieniu
        Item item = new Item();
        item.setQuantity(quantity);
        item.setProduct(product);
        orderService.addItemToOrder(item);
        return "redirect:/products";
    }

    @PostMapping(value ="/active-order",params = {"open"})
    public String showOrder(Model model){

        List<Item> soldProducts = orderService.getSoldProducts();

        model.addAttribute("soldProducts",soldProducts);

        return "products/showOrder";
    }

    @PostMapping(value = "/active-order",params = {"delete"})
    public String deleteProductInOrder(String name){
        orderService.deleteProduct(name);
        return "redirect:orders/active-order";
    }

    @PostMapping(value = "/active-order",params = {"save"})
    public String saveOrder(Principal principal){
        orderService.addOrder(principal.getName());
        return "redirect:/orders";
    }
}
