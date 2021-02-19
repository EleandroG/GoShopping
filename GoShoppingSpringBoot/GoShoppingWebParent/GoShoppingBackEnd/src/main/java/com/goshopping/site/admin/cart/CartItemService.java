package com.goshopping.site.admin.cart;

import com.goshopping.common.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import com.goshopping.site.admin.user.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private CartItemService service;

    public List<CartItem> listAll() {
        return (List<CartItem>) cartItemRepo.findAll();
    }

    //Add um novo item ao cart
    public void save(CartItem cartItem) {
        boolean isUpdatingCartItem = (cartItem.getId() != null);

        if (isUpdatingCartItem) {
            CartItem existingProduct = cartItemRepo.findById(cartItem.getId()).get();
            cartItem.setId(existingProduct.getId());
        }
        cartItemRepo.save(cartItem);
    }

    public boolean isNameUnique(Integer id, String name) {
        CartItem cartItemByName= cartItemRepo.getCartItemByName(name);

        if (cartItemByName == null) return true;

        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (cartItemByName != null) return false;
        } else {
            if (cartItemByName.getId() != id) {
                return false;
            }
        }
        return true;
    }

    public CartItem get(Integer id) throws UserNotFoundException {
        try {
            return cartItemRepo.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new UserNotFoundException("Could not find any item with ID: " + id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = cartItemRepo.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any item with ID: " + id);
        }
        cartItemRepo.deleteById(id);
    }
}
