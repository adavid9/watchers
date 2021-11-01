package com.dawidantecki.watchers.data.service;

import com.dawidantecki.watchers.data.entity.User;
import com.dawidantecki.watchers.data.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void when_find_by_id_then_user_should_be_returned() {
        final Optional<User> expected = Optional.of(
                new User("username")
        );
        when(userRepository.findById(anyLong())).thenReturn(expected);

        final User actual = userService.findById(1L);

        assertEquals(expected.get(), actual);
    }

    @Test
    public void when_find_by_username_then_user_should_be_returned() {
        final User expected = new User("username");
        when(userRepository.findByUsername(anyString())).thenReturn(expected);

        final User actual = userService.findByUsername("username");

        assertEquals(expected, actual);
    }

    @Test
    public void when_find_all_then_users_should_be_returned() {
        final List<User> expected = Lists.newArrayList(
                new User("username_one"),
                new User("username_two")
        );
        when(userRepository.findAll()).thenReturn(expected);

        final List<User> actual = userService.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void when_add_user_then_user_should_be_saved() {
        final User userMock = mock(User.class);
        when(userMock.getUsername()).thenReturn("username");
        when(userMock.getPassword()).thenReturn("password");
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("password");

        userService.addUser(userMock);

        verify(userRepository).findByUsername(eq(userMock.getUsername()));
        verify(bCryptPasswordEncoder).encode(eq(userMock.getPassword()));
        verify(userRepository).save(eq(userMock));
    }

    @Test
    public void when_delete_user_then_user_should_be_deleted() {
        final User userMock = mock(User.class);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userMock));

        userService.deleteUser(1L);

        verify(userRepository).delete(eq(userMock));
    }
}