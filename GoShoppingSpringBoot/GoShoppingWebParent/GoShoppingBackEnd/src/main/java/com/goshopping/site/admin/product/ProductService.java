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

    public Optional<Product> findById(Long id) {
        return null;
    }

    public Page<Product> findAllProductsPageable(Pageable pageable) {
        return null;
    }


    public List<Product> listAll() {
        return (List<Product>) productRepo.findAll();
    }


    //Add um novo produto ao site
    public void save(Product product) {
        boolean isUpdatingProduct = (product.getId() != null);

        if (isUpdatingProduct) {
            Product existingProduct = productRepo.findById(product.getId()).get();
            product.setId(existingProduct.getId());
        }
        productRepo.save(product);
    }

    //Add para o cart
    /*public void saveToCart(Product product) {
        boolean isUpdatingProduct = (product.getId() != null);

        if (isUpdatingProduct) {
            Product existingProduct = productRepo.findById(product.getId()).get();
            product.setId(existingProduct.getId());
        }
//        productRepo.save(product);
        cartItem.addProduct(product);
        //List<CartItem> cartItems = service.listAll();
        //List<Product> listProducts = service.listAll();
    }*/

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
