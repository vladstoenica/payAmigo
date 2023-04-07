package com.example.payAmigo.service;

import com.example.payAmigo.entity.User;
import com.example.payAmigo.entity.Wallet;
import com.example.payAmigo.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById() {
        User user = new User(1, "jon", "jon@email.com", "pass", new Wallet());
        when(userRepository.findById(1)).thenReturn(user);
        User result = userService.getUserById(1);
        assertEquals(user, result);
    }

    @Test
    public void testSaveUser() {
        User user = new User(2, "gog", "gog@email.com", "pass", new Wallet());
        // Mock de save din userRepository to return the same user
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.save(user);
        assertEquals(user, savedUser);
    }

    @Test
    public void testDeleteUserById() {
        int userIdToDelete = 5;
        // Mock de deleteByUserId din userRepository ca sa throw an exception
        doThrow(new RuntimeException("User not found")).when(userRepository).deleteByUserId(anyInt());
        assertThrows(RuntimeException.class, () -> userService.deleteById(userIdToDelete));
    }

    @Test
    public void testGetAllUsers() {
        List<User> testUsers = new ArrayList<>();
        testUsers.add(new User(1, "jon", "jon@email.com", "pass", new Wallet()));
        testUsers.add(new User(2, "gog", "gog@email.com", "pass", new Wallet()));
        //Mock la findAll
        when(userRepository.findAll()).thenReturn(testUsers);
        List<User> allUsers = userService.getAllUsers();
        assertIterableEquals(testUsers, allUsers);
    }

    @Test
    public void testFindByEmail() {
        String testEmail = "jon@email.com";
        User testUser = new User(1, "jon", testEmail, "pass", new Wallet());
        when(userRepository.findByEmail(testEmail)).thenReturn(testUser);
        User foundUser = userService.findByEmail(testEmail);
        assertEquals(testUser, foundUser);
    }


}
