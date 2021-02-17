package com.goshopping.site.admin.user;

import com.goshopping.common.entity.CartItem;
import com.goshopping.common.entity.Customer;
import com.goshopping.common.entity.Product;
import com.goshopping.site.admin.cart.CartItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingCartTests {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem() {
        Product product = entityManager.find(Product.class, 3);
        //Customer customer = entityManager.find(Customer.class, 5);

        CartItem newItem = new CartItem();
        //newItem.setCustomer(customer);
        newItem.setProduct(product);
        newItem.setQuantity(1);

        CartItem saveCartItem = cartRepo.save(newItem);
        assertTrue(saveCartItem.getId() > 0);

    }

    @Test
    public void testGetCartItemsByCustomer() {
        Customer customer = new Customer();
        customer.setId(5);

        List<CartItem> cartItems = cartRepo.findByCustomer(customer);
        assertEquals(1, cartItems.size());
    }
}
