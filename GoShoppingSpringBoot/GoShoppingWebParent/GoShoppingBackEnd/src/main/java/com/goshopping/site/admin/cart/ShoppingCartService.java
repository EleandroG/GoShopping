package com.goshopping.site.admin.cart;

import com.goshopping.common.entity.CartItem;
import com.goshopping.common.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartRepo;

    public List<CartItem> listCartItems(Customer customer) {
        return cartRepo.findByCustomer(customer);
    }
}
