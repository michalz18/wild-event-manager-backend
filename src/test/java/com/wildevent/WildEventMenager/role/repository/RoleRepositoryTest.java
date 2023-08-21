package com.wildevent.WildEventMenager.role.repository;

import com.wildevent.WildEventMenager.role.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindAllReturnsAllRoles() {
        Role role1 = new Role();
        Role role2 = new Role();

        role1.setName("Admin");
        role2.setName("User");

        roleRepository.save(role1);
        roleRepository.save(role2);

        List<Role> roles = roleRepository.findAll();

        assertTrue(roles.contains(role1));
        assertTrue(roles.contains(role2));
    }
}