package com.dawidantecki.watchers.data.service;

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
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        if (user == null)
            return;
        User actualUser = userRepository.findByUsername(user.getUsername());
        // if the actual user id is different then user id that means the user already exists and cannot be saved 2nd time.
        if (actualUser != null && !actualUser.getId().equals(user.getId())) {
            throw new UserAlreadyExistsException("User with " + user.getUsername() + " username already exists");
        }
        // allow to edit existing user if the id is the same ( that means that this is the same user and this will be update )
        if (actualUser != null)
            user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
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
}
