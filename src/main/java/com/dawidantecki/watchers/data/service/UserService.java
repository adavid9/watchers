package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.repository.RoleRepository;
import com.dawidantecki.watchers.data.repository.UserRepository;
import com.dawidantecki.watchers.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        if (actualUser != null)
            throw new UserAlreadyExistsException("User with " + user.getUsername() + " already exists.");

        user.setRoles(new HashSet<>(roleRepository.findAll()));
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
}
