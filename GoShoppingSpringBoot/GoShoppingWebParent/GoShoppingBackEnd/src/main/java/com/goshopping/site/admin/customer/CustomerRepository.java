package com.goshopping.site.admin.customer;

import com.goshopping.common.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT u FROM Customer u WHERE u.email =:email")
    public Customer getCustomerByEmail(@Param("email") String email);

    public Long countById(Integer id);
}
