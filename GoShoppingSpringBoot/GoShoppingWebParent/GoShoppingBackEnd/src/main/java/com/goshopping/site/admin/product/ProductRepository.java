package com.goshopping.site.admin.product;

import com.goshopping.common.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //JpaRepository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("SELECT u FROM Product u WHERE u.name =:name")
    public Product getProductByName(@Param("name") String name);

    public Long countById(Integer id);
}
