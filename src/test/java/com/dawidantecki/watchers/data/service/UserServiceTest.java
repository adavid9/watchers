package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.configuration.DatabaseConnectionTest;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest extends DatabaseConnectionTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private ArrayList<User> users;

    @Before
    public void setUp() {
        users = generateUsers();
    }

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void should_save_user() {
        User user = users.get(0);

        userService.addUser(user);

        assertEquals(user, userService.findById(user.getId()));
    }

    @Test
    public void should_save_users() {
        userService.addUser(users);
        users.forEach(x -> assertEquals(x, userService.findById(x.getId())));
    }

    @Test
    public void should_not_thrown_exception_while_null() {
        userService.addUser((User) null);

        assertEquals(0, userService.findAll().size());
    }

    @Test
    public void should_delete_user() {
        User user = users.get(0);

        userService.addUser(user);
        userService.deleteUser(user);

        assertNull(userService.findById(user.getId()));
    }

    @Test
    public void should_delete_collection_of_users() {
        userService.addUser(users);

        userService.deleteUser(users);

        int found = 0;
        for (User u: users) {
            if (userService.findById(u.getId()) != null)
                found++;
        }

        assertEquals(0, found);
    }

    @Test
    public void should_delete_user_by_id() {
        User user = users.get(0);

        userService.addUser(user);

        userService.deleteUser(user.getId());

        assertNull(userService.findById(user.getId()));
    }

    @Test
    public void should_delete_user_by_username() {
        User user = users.get(0);

        userService.addUser(user);

        userService.deleteUser(user.getUsername());

        assertNull(userService.findByUsername(user.getUsername()));
    }

    @Test
    public void should_find_user_by_id() {
        User user = users.get(0);

        userService.addUser(user);

        assertEquals(user, userService.findById(user.getId()));
    }

    @Test
    public void should_find_user_by_username() {
        User user = users.get(0);

        userService.addUser(user);

        assertEquals(user, userService.findByUsername(user.getUsername()));
    }

    @Test
    public void should_find_user_by_email() {
        User user = users.get(0);

        userService.addUser(user);

        assertEquals(user, userService.findByEmail(user.getEmail()));
    }

    @Test
    public void should_be_only_one() {
        users.add(new User("user", "", "", ""));
        users.add(new User("user", "", "", ""));

        userService.addUser(users);

        List<User> allUsers = userService.findAll();
        for (User u1: allUsers) {
            int sameObjects = 0;
            for (User u2: allUsers) {
                if (u1.getUsername().equals(u2.getUsername())) sameObjects++;

                if (sameObjects > 1)
                    fail("User with already existing username cannot be saved.");
            }
        }
    }

    private ArrayList<User> generateUsers() {
        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            users.add(new User("username" + i, "email" + i + "@email.com", "password", "password"));
        }

        return users;
    }
}