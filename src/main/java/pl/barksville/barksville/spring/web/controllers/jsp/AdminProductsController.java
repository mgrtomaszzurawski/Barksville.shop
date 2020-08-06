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
@RequestMapping("/admin/products")
public class AdminProductsController {

    private final ProductService productService;

    public AdminProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String addProductToInvoice(Model model){
        model.addAttribute("products",productService.allProducts());
        return "adminPanel/products/showAllProducts";
    }

    @GetMapping(value = "/product-alias-list")
    public String productListToAddAlias(Model model) {
        model.addAttribute("products", productService.allProducts());
        return "adminPanel/products/productAliasList";
    }
    @PostMapping(value = "/product-alias-list",params="add")
    public String addProductAlias(Long id,String alias){
        Product product =  productService.getProductById(id);
        product.getAliasNames().add(alias);
        productService.saveProduct(product);
        return "redirect:/admin/products/product-alias-list";
    }
    /*@PostMapping(value = "/change-minimal-quantity",params="delete")
    public String deleteProductAlias(Long product,String alias){
        productService.getProductById(product).getAliasNames().removeIf(name->name.equals(alias));
        return "redirect:/admin/products/product-alias-list";
    }*/


    @GetMapping(value = "/low-quantity-products")
    public String lowQuantityProductList(Model model) {
        model.addAttribute("products", productService.allActiveProducts().stream().filter(product -> product.getQuantity()<=product.getMinimalQuantity()).collect(Collectors.toList()));
        return "adminPanel/products/lowQuantityProducts";
    }

    @GetMapping(value = "/product-minimal-quantity-list")
    public String productListToEditMinimalQuantity(Model model) {
        model.addAttribute("products", productService.allProducts());
        return "adminPanel/products/productMinimalQuantity";
    }

    @PostMapping(value = "/change-minimal-quantity")
    public String changeProductMinimalQuantity(Long id,Double minQuantity){
       Product product= productService.getProductById(id);
       product.setMinimalQuantity(minQuantity);
       productService.saveProduct(product);
        return "redirect:/admin/products/product-minimal-quantity-list";
    }
}
