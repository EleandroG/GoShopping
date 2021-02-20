package com.goshopping.site.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/brands")
    public String viewPageBrands() {
        return "brands";
    }

    @GetMapping("/orders")
    public String viewPageOrders() {
        return "orders";
    }

}
