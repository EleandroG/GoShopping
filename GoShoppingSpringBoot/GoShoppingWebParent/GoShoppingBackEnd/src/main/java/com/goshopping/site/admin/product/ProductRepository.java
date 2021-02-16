package com.goshopping.site.admin.product;

import com.goshopping.common.entity.Customer;
import com.goshopping.common.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
