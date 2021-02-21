package com.goshopping.site.admin.user;

import static org.assertj.core.api.Assertions.assertThat;
import com.goshopping.common.entity.Role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateFirstRole() {
        Role roleAdmin = new Role("Admin", "Manage everything");
        Role savedRole = repo.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRestRoles() {
        //Role roleSalesPerson = new Role("Salesperson", "Manage product prices, orders, etc");
        //Role savedRole2 = repo.save(roleSalesPerson);
        Role roleEditor = new Role("Editor", "Manage categories, articles, etc");
        Role savedRole3 = repo.save(roleEditor);
        //Role roleShipper = new Role("Shipper", "Manage shipping order, order status, etc");
        //Role savedRole4 = repo.save(roleShipper);
        Role roleAssistant = new Role("Assistant", "Manage reviews, Q&A, etc.");
        Role savedRole5 = repo.save(roleAssistant);
        //repo.saveAll(List.of(roleSalesPerson, roleEditor, roleShipper, roleAssistant));
    }

}
