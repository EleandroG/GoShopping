package com.goshopping.site.admin.cart;

import com.goshopping.common.entity.CartItem;
import com.goshopping.common.entity.Customer;
import com.goshopping.site.admin.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/products/cart")
    public String showShoppingCart(Model model)/*, @AuthenticationPrincipal Authentication authentication*/ {

        //Customer customer = new Customer();
        //List<CartItem> cartItems = cartService.listCartItems(customer);

        //model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");

        return "shopping_cart";
    }
}
