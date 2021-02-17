package com.goshopping.site.admin.cart;

import com.goshopping.common.entity.CartItem;
import com.goshopping.common.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByCustomer(Customer customer);

}
