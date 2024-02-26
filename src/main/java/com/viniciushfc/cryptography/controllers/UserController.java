package com.viniciushfc.cryptography.controllers;

import com.viniciushfc.cryptography.dtos.UserDTO;
import com.viniciushfc.cryptography.entities.User;
import com.viniciushfc.cryptography.infra.exception.NotFoundData;
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
    public ResponseEntity<Object> createUser(@RequestBody UserDTO dto) {
        try {
            this.service.createUser(dto);
            return ResponseEntity.ok().body("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Algo deu errado no cadastro do usuário");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        try {
            this.service.updateUser(id, dto);
            return ResponseEntity.ok().body("Usuário atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Algo deu errado na atualização do usuário, " + "'" + e.getMessage() + "'");
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.service.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        User user = new User();
        try {
            user = this.service.getUserById(id);
            return ResponseEntity.ok().body(user);
        } catch (NotFoundData e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id){
        try{
            this.service.deleteUserById(id);
            return ResponseEntity.ok().body("Usuário deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
