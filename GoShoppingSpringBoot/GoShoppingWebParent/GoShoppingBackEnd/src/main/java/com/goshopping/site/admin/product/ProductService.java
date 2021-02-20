package com.goshopping.site.admin.product;

import com.goshopping.common.entity.Product;
import com.goshopping.site.admin.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductService service;


    public List<Product> listAll() {
        return (List<Product>) productRepo.findAll();
    }


    //Add um novo produto ao site
    public void save(Product products) {
        boolean isUpdatingProduct = (products.getId() != null);

        if (isUpdatingProduct) {
            Product existingProduct = productRepo.findById(products.getId()).get();
            products.setId(existingProduct.getId());
        }
        productRepo.save(products);
    }

    public boolean isNameUnique(Integer id, String name) {
        Product productsByName = productRepo.getProductByName(name);

        if (productsByName == null) return true;

        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (productsByName != null) return false;
        } else {
            if (productsByName.getId() != id) {
                return false;
            }
        }
        return true;
    }

    public Product get(Integer id) throws ProductNotFoundException {
        try {
            return productRepo.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new ProductNotFoundException("Could not find any product with ID: " + id);
        }
    }

    public void delete(Integer id) throws ProductNotFoundException {
        Long countById = productRepo.countById(id);
        if (countById == null || countById == 0) {
            throw new ProductNotFoundException("Could not find any product with ID: " + id);
        }
        productRepo.deleteById(id);
    }
}
