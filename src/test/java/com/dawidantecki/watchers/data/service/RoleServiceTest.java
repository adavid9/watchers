package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.entity.enums.RoleName;
import com.dawidantecki.watchers.data.repository.RoleRepository;
import com.dawidantecki.watchers.exceptions.RoleAlreadyExistsException;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void when_find_by_id_then_role_should_be_returned() {
        final Optional<Role> expected = Optional.of(new Role(RoleName.ROLE_ADMIN));
        when(roleRepository.findById(anyLong())).thenReturn(expected);

        final Role actual = roleService.findById(1L);

        assertEquals(expected.get(), actual);
    }

    @Test
    public void when_find_by_name_then_role_should_be_returned() {
        final Role expected = new Role(RoleName.ROLE_ADMIN);
        when(roleRepository.findByRoleName(any(RoleName.class))).thenReturn(expected);

        final Role actual = roleService.findByName(RoleName.ROLE_ADMIN);

        assertEquals(expected, actual);
    }

    @Test
    public void when_find_all_then_roles_should_be_returned() {
        final List<Role> expected = Lists.newArrayList(
                new Role(RoleName.ROLE_ADMIN),
                new Role(RoleName.ROLE_USER)
        );
        when(roleRepository.findAll()).thenReturn(expected);

        final List<Role> actual = roleService.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void when_add_role_then_role_should_be_saved() {
        final Role role = new Role(RoleName.ROLE_ADMIN);

        roleService.addRole(role);

        verify(roleRepository).save(eq(role));
    }

    @Test
    public void when_add_role_and_role_exists_then_exception_should_be_thrown() {
        final Role role = new Role(RoleName.ROLE_ADMIN);
        when(roleRepository.findByRoleName(any(RoleName.class))).thenReturn(role);

        final RoleAlreadyExistsException roleAlreadyExistsException = assertThrows(RoleAlreadyExistsException.class, () -> roleService.addRole(role));
        assertEquals("Role " + role.getRoleName().name() + " already exists, cannot add role with the same name.", roleAlreadyExistsException.getMessage());
    }
}