package com.goshopping.site;

import com.goshopping.common.entity.CartItem;
import com.goshopping.common.entity.Customer;
import com.goshopping.common.entity.Product;
import com.goshopping.site.shoppingcart.CartItemRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingCartTests {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem() {
        Product product = entityManager.find(Product.class, 20);
        Customer customer = entityManager.find(Customer.class, 5);

        CartItem newItem = new CartItem();
        newItem.setCustomer(customer);
        newItem.setProduct(product);
        newItem.setQuantity(1);

        CartItem saveCartItem = cartRepo.save(newItem);
        assertTrue(saveCartItem.getId() > 0);

    }
}
