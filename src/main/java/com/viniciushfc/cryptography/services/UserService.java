package com.viniciushfc.cryptography.services;

import com.viniciushfc.cryptography.dtos.UserDTO;
import com.viniciushfc.cryptography.entities.User;
import com.viniciushfc.cryptography.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CryptographyService cryptographyService;

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDTO dto) throws Exception {
        User newUser = cryptographyService.condifyUser(dto);
        saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUser(){
        return this.repository.findAll();
    }


}