package com.goshopping.site.admin.cart;

import com.goshopping.common.entity.CartItem;
import com.goshopping.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CartItemController {

    @Autowired
    private CartItemService service;

    @GetMapping("/cart") //url para listar os items com a pag HTML5
    public String listAll(Model model) {
        List<CartItem> listCartItems = service.listAll();
        model.addAttribute("listCartItems", listCartItems);
        return "cart_items"; //ficheiro que devolve
    }

    @GetMapping("/cart/new") //url que devolve a pag HTML5
    public String newCartItem(Model model) {
        CartItem cartItem = new CartItem();
        model.addAttribute("cartItem", cartItem);

        model.addAttribute("pageTitle", "Adding to the Cart");

        return "cart_form"; //ficheiro que devolve
    }

    //guarda novos produtos
    @PostMapping("/cart/save")
    public String saveCartItem(CartItem cartItem, RedirectAttributes redirectAttributes) {
        System.out.print(cartItem);
        service.save(cartItem);

        redirectAttributes.addFlashAttribute("message", "The item has been added successfully!");
        return "redirect:/products";
    }

}
