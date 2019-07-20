package com.dawidantecki.watchers.component;

import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.entity.enums.RoleName;
import com.dawidantecki.watchers.data.service.RoleService;
import com.dawidantecki.watchers.data.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class InitAdmin {

    private static final Logger logger = LoggerFactory.getLogger(InitAdmin.class);

    private UserService userService;

    private RoleService roleService;

    private InitAdmin() {
        // no-args
    }

    @Autowired
    public void setUserService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role admin = new Role(RoleName.ROLE_ADMIN);
        Role user = new Role(RoleName.ROLE_USER);
        List<Role> roles = Arrays.asList(admin, user);
        roleService.addRole(roles);

        // create admin user
        User user1 = new User("watchers_admin", "watchers@email.com", "admin", "admin");
        user1.setRoles(Collections.singleton(admin));
        userService.addUser(user1);

        logger.info("Successfully created roles -> {}, {}", admin.getRoleName(), user.getRoleName());
        logger.info("Successfully created user -> {}", user1.getUsername());
    }
}
