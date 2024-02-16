package com.viniciushfc.cryptography.services;

import com.viniciushfc.cryptography.dtos.UserDTO;
import com.viniciushfc.cryptography.entities.User;
import com.viniciushfc.cryptography.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDTO dto) {
        User newUser = new User(dto);
        saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUser(){
        return this.repository.findAll();
    }


}