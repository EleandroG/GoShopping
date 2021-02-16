package com.goshopping.site.admin.user;

import com.goshopping.common.entity.Customer;
import com.goshopping.site.admin.customer.CustomerRepository;
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
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer("Sergio@ua.pt", "123456678", "Sergio", "Mendes", "934962819", "Rua Ciudad Rodrigo 39", "3810-042");
        Customer savedCustomer = repo.save(customer);
        assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllCustomers() {
        Iterable<Customer> listCustomers = repo.findAll();
        listCustomers.forEach(customer -> System.out.println(customer));

    }

    @Test
    public void testGetCustomerById() {
        Customer customer = repo.findById(1).get();
        System.out.println(customer);
        assertThat(customer).isNotNull();
    }

    @Test
    public void testUpdateCustomerDetails() {
        Customer customer = repo.findById(1).get();
        customer.setEmail("eleandro15@gmail.com");
        repo.save(customer);
    }

    @Test
    public void testDeleteCustomer() {
        Integer userId = 7;
        repo.deleteById(userId);

    }

    @Test
    public void testGetCustomerByEmail() {
        String email = "eleandrog@ua.pt";
        Customer customer = repo.getCustomerByEmail(email);
        assertThat(customer).isNotNull();
    }

    @Test
    public void testCountById() {
        Integer id = 6;
        Long countById = repo.countById(id);
        assertThat(countById).isNotNull().isGreaterThan(0);
    }
}
