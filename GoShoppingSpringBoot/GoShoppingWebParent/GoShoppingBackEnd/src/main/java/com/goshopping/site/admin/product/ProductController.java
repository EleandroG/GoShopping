package com.goshopping.site.admin.product;

import com.goshopping.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    //Para Save, ou seja, para adicionar ao Cart
    @PostMapping("/products/save") //TODO: mudar o url?
    public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
        System.out.print(product);
        service.save(product);

        redirectAttributes.addFlashAttribute("message", "The customer has been added to the cart succesfully!");
        return "redirect:/products"; //TODO: qual URL? //TODO: Linha 67 do products.html
    }
}
