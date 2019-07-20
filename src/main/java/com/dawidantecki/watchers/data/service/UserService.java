package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.repository.UserRepository;
import com.dawidantecki.watchers.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (NullPointerException npe) {
            return null;
        }
    }

    public User findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (NullPointerException npe) {
            return null;
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        if (user == null)
            return;
        User byLogin = userRepository.findByUsername(user.getUsername());
        User byEmail = userRepository.findByEmail(user.getEmail());
        boolean exists = false;
        if (byLogin != null || byEmail != null) {
            exists = true;
        }

        if (exists) {
            byLogin.setUsername(user.getUsername());
            byLogin.setEmail(user.getEmail());
            byLogin.setPassword(passwordEncoder.encode(user.getPassword()));
            byLogin.setRoles(user.getRoles());
            byLogin.setUser_movies(user.getUser_movies());
            userRepository.save(byLogin);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public void addUser(Collection<User> users) {
        if ((users != null) && (users.size() > 0))
            users.forEach(x -> {
                if (x != null)
                    addUser(x);
            });
    }

    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return;

        userRepository.delete(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            return;

        deleteUser(user.getId());
    }

    public void deleteUser(User user) {
        deleteUser(user.getId());
    }

    public void deleteUser(Collection<User> users) {
        if (users.size() > 0)
            users.forEach(x -> {
                if (x != null)
                    deleteUser(x.getId());
            });
    }
}
