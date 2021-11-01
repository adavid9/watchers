package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        if (user == null)
            return;
        User byLogin = userRepository.findByUsername(user.getUsername());

        if (byLogin != null) {
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

    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return;

        userRepository.delete(user);
    }

    public void deleteUser(User user) {
        deleteUser(user.getId());
    }
}
