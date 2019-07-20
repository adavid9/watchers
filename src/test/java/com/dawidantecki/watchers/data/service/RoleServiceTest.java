package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest;
import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.entity.RoleName;
import com.dawidantecki.watchers.data.repository.RoleRepository;
import com.dawidantecki.watchers.exceptions.RoleAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RoleServiceTest extends DatabaseConnectionTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;

    private ArrayList<Role> roles;

    @Before
    public void setUp() {
        roleRepository.deleteAll(); // clean up roles added by @PostConstruct
        roles = roleList();
    }

    @After
    public void cleanUp() {
        roleRepository.deleteAll();
    }

    @Test
    public void should_save_role() {
        Role role = roles.get(0);
        roleService.addRole(role);
        assertEquals(role, roleService.findById(role.getId()));
    }

    @Test
    public void should_save_roles() {
        roleService.addRole(roles);
        roles.forEach(role -> assertEquals(role, roleService.findById(role.getId())));
    }

    @Test
    public void should_not_thrown_exception_while_null_role() {
        roleService.addRole((Role) null);
        assertEquals(0, roleService.findAll().size());
    }

    @Test
    public void should_delete_role() {
        Role role = roles.get(0);
        roleService.addRole(role);
        roleService.deleteRole(role);
        assertNull(roleService.findById(role.getId()));
    }

    @Test
    public void should_delete_role_by_id() {
        Role role = roles.get(0);
        roleService.addRole(role);
        roleService.deleteRole(role.getId());
        assertNull(roleService.findById(role.getId()));
    }

    @Test
    public void should_delete_role_by_role_name() {
        Role role = roles.get(0);
        roleService.addRole(role);
        roleService.deleteRole(role.getRoleName());
        assertNull(roleService.findByName(role.getRoleName()));
    }

    @Test(expected = RoleAlreadyExistsException.class)
    public void should_thrown_exception_when_role_already_exists() {
        roles.add(new Role(RoleName.ROLE_ADMIN));
        roleService.addRole(roles);
    }

    private ArrayList<Role> roleList() {
        return new ArrayList<Role>() {
            {
                add(new Role(RoleName.ROLE_ADMIN));
                add(new Role(RoleName.ROLE_USER));
            }
        };
    }
}