package com.goshopping.site.admin.product;

import com.goshopping.common.entity.Customer;
import com.goshopping.common.entity.Product;
import com.goshopping.site.admin.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products") //url para listar os products com a pag HTML5
    public String listAll(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "products"; //ficheiro que devolve
    }

    @GetMapping("/products/new") //url que devolve a pag HTML5
    public String newProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        model.addAttribute("pageTitle", "Create New Product");

        return "products_form"; //ficheiro que devolve
    }

    //guarda novos produtos
    @PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
        System.out.print(product);
        service.save(product);

        redirectAttributes.addFlashAttribute("message", "The product has been added successfully!");
        return "redirect:/products";
    }

    //page antes da adição com o save
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = service.get(id);
            model.addAttribute("product", product);

            model.addAttribute("pageTitle", "Adding Product (ID: " + id + ")");
            return "save_shopping_cart";
        } catch (UserNotFoundException exception) {
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            return "redirect:/products";
        }
    }

    @GetMapping("/products/cart")
    public String showShoppingCart(Model model)/*, @AuthenticationPrincipal Authentication authentication*/ {

        //Customer customer = new Customer();
        //List<CartItem> cartItems = cartService.listCartItems(customer);

        //model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");

        return "shopping_cart";
    }
}
