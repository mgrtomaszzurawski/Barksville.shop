package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class AdminProductsController {

    private final ProductService productService;

    public AdminProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String addProductToInvoice(Model model){

        model.addAttribute("products",productService.allProducts());

        return "products/showAllProducts";
    }
}
