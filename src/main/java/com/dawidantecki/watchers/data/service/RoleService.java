package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.repository.RoleRepository;
import com.dawidantecki.watchers.exceptions.RoleAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role) {
        if (role == null)
            return;
        Role existingRole = roleRepository.findByName(role.getName());
        if (existingRole != null)
            throw new RoleAlreadyExistsException("Role " + role.getName() + " already exists, cannot add role with the same name.");
        else
            roleRepository.save(role);
    }

    public void addRole(Collection<Role> roles) {
        if ((roles != null) && (roles.size() > 0))
            roles.forEach(x -> {
                if (x != null)
                    addRole(x);
            });
    }

    public Role findById(long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public void deleteRole(long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null)
            return;
        roleRepository.delete(role);
    }

    public void deleteRole(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null)
            return;
        deleteRole(role.getId());
    }
}
