package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.ProductService;
import pl.barksville.barksville.spring.model.entities.data.Product;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProducts(Model model) {
        List<Product> allProducts = productService.allActiveProducts();
        model.addAttribute("allProducts", allProducts);
        return "products/products";
    }


    @PostMapping(params = {"save"})
    public String saveActiveOrder(){

        return "redirect:/orders/active-order";
    }

}
