package com.goshopping.site.admin.customer;

import com.goshopping.common.entity.Customer;
import com.goshopping.site.admin.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Customer> listAll() {
        return (List<Customer>) customerRepo.findAll();
    }

    public void save(Customer customer) {
        boolean isUpdatingCustomer = (customer.getId() != null);

        if (isUpdatingCustomer) {
            Customer existingCustomer = customerRepo.findById(customer.getId()).get();

            if (customer.getPassword().isEmpty()) {
                customer.setPassword(existingCustomer.getPassword());
            } else {
                encodePassword(customer);
            }
        } else {
            encodePassword(customer);
        }

        encodePassword(customer);
        customerRepo.save(customer);
    }

    private void encodePassword(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(Integer id, String email) {
        Customer customerByEmail = customerRepo.getCustomerByEmail(email);

        if (customerByEmail == null) return true;

        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (customerByEmail != null) return false;
        } else {
            if (customerByEmail.getId() != id) {
                return false;
            }
        }
        return true;
    }

    public Customer get(Integer id) throws UserNotFoundException {
        try {
            return customerRepo.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new UserNotFoundException("Could not find any customer with ID: " + id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = customerRepo.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any customer with ID: " + id);
        }
        customerRepo.deleteById(id);
    }
}
