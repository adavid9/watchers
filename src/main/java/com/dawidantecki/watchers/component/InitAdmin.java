package com.dawidantecki.watchers.component;

import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitAdmin {

    private static final Logger logger = LoggerFactory.getLogger(InitAdmin.class);

    private UserService userService;

    private InitAdmin() {
        // no-args
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        User user = new User("admin", "admin", "admin");
        Role role = new Role("ROLE_ADMIN");
        user.getRoles().add(role);

        userService.addUser(user);
        logger.info("Admin user has been initialized successfully.");
    }
}
