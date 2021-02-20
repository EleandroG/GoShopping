package com.goshopping.site.admin.user;

import com.goshopping.common.entity.Product;
import com.goshopping.site.admin.product.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductsRepositoryTests {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private TestEntityManager testEntityManager;

    //talvez o price deva ser um double
    @Test
    public void testCreateProduct() {
        Product product = new Product("1.53", "Robalo", "Peixe fresco.");
        Product savedProduct = repo.save(product);
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllProducts() {
        Iterable<Product> listProducts = repo.findAll();
        listProducts.forEach(product -> System.out.println(product));

    }

    @Test
    public void testGetProductById() {
        Product product = repo.findById(1).get();
        System.out.println(product);
        assertThat(product).isNotNull();
    }

    @Test
    public void testUpdateProductDetails() {
        Product product = repo.findById(1).get();
        product.setDescription("None");
        repo.save(product);
    }

    @Test
    public void testDeleteProduct() {
        Integer productId = 5;
        repo.deleteById(productId);
    }

    /*@Test
    public void testGetProductByEmail() {
        String email = "eleandrog@ua.pt";
        Customer customer = repo.getCustomerByEmail(email);
        assertThat(customer).isNotNull();
    }*/

    @Test
    public void testCountById() {
        Integer id = 2;
        Long countById = repo.countById(id);
        assertThat(countById).isNotNull().isGreaterThan(0);
    }
}
