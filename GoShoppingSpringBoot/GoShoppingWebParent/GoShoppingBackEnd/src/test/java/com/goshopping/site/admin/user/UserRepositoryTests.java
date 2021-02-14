package com.goshopping.site.admin.user;

import com.goshopping.common.entity.Role;
import com.goshopping.common.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateUserWithOneRole() {
        Role roleAdmin = testEntityManager.find(Role.class, 1);
        User user = new User("eleandrog@ua.pt", "123456", "Eleandro", "Laureano");
        user.addRole(roleAdmin);
        User savedUser = repo.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithTwoRoles() {
        User user2 = new User("julio@ua.pt", "7890", "Julio", "Costa");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);
        user2.addRole(roleEditor);
        user2.addRole(roleAssistant);
        User savedUser = repo.save(user2);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }


}
