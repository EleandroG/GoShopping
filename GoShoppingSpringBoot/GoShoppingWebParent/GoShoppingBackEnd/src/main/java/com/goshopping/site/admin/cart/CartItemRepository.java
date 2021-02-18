package com.goshopping.site.admin.cart;

import com.goshopping.common.entity.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Integer> {

    @Query("SELECT u FROM CartItem u WHERE u.name =:name")
    CartItem getCartItemByName(@Param("name") String name);

    Long countById(Integer id);
}
