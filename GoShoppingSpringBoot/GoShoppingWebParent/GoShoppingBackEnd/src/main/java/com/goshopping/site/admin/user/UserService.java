package com.goshopping.site.admin.user;

import com.goshopping.common.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }
}
