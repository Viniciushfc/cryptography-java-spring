package com.viniciushfc.cryptography.controllers;

import com.viniciushfc.cryptography.dtos.UserDTO;
import com.viniciushfc.cryptography.entities.User;
import com.viniciushfc.cryptography.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO dto){
        try{
            var result = this.service.createUser(dto);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return this.service.getAllUser();
    }

}
