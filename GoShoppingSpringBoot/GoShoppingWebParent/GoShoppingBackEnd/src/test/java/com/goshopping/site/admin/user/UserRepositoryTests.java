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
    public void testCreateUser() {
        Role roleAdmin = testEntityManager.find(Role.class, 1);
        User user = new User("eleandrog@ua.pt", "123456", "Eleandro", "Laureano");
        user.addRole(roleAdmin);
        User savedUser = repo.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

}
