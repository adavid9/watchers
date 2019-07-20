package com.dawidantecki.watchers.data.repository;

import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(RoleName roleName);
}
