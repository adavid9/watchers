package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.Role;
import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.entity.enums.RoleName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailsServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    @Test
    public void when_load_user_by_username_and_user_not_found_an_exception_should_be_thrown() {
        final UsernameNotFoundException usernameNotFoundException = assertThrows(UsernameNotFoundException.class,
                () -> myUserDetailsService.loadUserByUsername("username")
        );
        assertEquals("Username: username not found.", usernameNotFoundException.getMessage());
    }

    @Test
    public void when_load_user_by_username_user_details_should_be_returned() {
        final User user = mock(User.class);
        when(user.getUsername()).thenReturn("username");
        when(user.getPassword()).thenReturn("password");
        when(user.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build()))
        );
        when(userService.findByUsername(user.getUsername())).thenReturn(user);

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername("username");

        assertEquals("username", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }
}