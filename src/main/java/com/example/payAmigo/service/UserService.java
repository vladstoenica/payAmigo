package com.example.payAmigo.service;

import com.example.payAmigo.entity.User;
import com.example.payAmigo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) {
        User user = userRepository.findById(id);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void deleteById(int id) {
        userRepository.deleteByUserId(id);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(User user){
        return userRepository.save(user);
    }


}
