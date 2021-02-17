package com.goshopping.site.admin.product;

import com.goshopping.common.entity.CartItem;
import com.goshopping.common.entity.Product;
import com.goshopping.site.admin.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    //@Autowired
    private CartItem cartItem;


    public List<Product> listAll() {
        return (List<Product>) productRepo.findAll();
    }

    public void save(Product product) {
        boolean isUpdatingProduct = (product.getId() != null);

        if (isUpdatingProduct) {
            Product existingProduct = productRepo.findById(product.getId()).get();
            product.setId(existingProduct.getId());
        }
        productRepo.save(product);
    }

    public void saveToCart(Product product) {
        boolean isUpdatingProduct = (product.getId() != null);

        if (isUpdatingProduct) {
            Product existingProduct = productRepo.findById(product.getId()).get();
            product.setId(existingProduct.getId());
        }
        //productRepo.save(product);
        cartItem.addProduct(product);
    }

    public boolean isNameUnique(Integer id, String name) {
        Product productByName = productRepo.getProductByName(name);

        if (productByName == null) return true;

        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            if (productByName != null) return false;
        } else {
            if (productByName.getId() != id) {
                return false;
            }
        }
        return true;
    }

    public Product get(Integer id) throws UserNotFoundException {
        try {
            return productRepo.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new UserNotFoundException("Could not find any product with ID: " + id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = productRepo.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any product with ID: " + id);
        }
        productRepo.deleteById(id);
    }
}
