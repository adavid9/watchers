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
        Role admin = Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build();
        Role user = Role.builder()
                .roleName(RoleName.ROLE_USER)
                .build();
        List<Role> roles = Arrays.asList(admin, user);
        roleService.addRole(roles);

        // create admin user
        User adminUser = User.builder()
                .username("watchers_admin")
                .email("watchers@email.com")
                .password("admin")
                .confirmPassword("admin")
                .roles(Collections.singleton(admin))
                .build();
        userService.addUser(adminUser);

        logger.info("Successfully created roles -> {}, {}", admin.getRoleName(), user.getRoleName());
        logger.info("Successfully created user -> {}", adminUser.getUsername());
    }
}
