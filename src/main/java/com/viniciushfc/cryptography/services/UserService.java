package com.viniciushfc.cryptography.services;

import com.viniciushfc.cryptography.dtos.UserDTO;
import com.viniciushfc.cryptography.entities.User;
import com.viniciushfc.cryptography.infra.exception.NotFoundData;
import com.viniciushfc.cryptography.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CryptographyService cryptographyService;


    public UserService(UserRepository repository, CryptographyService cryptographyService, SecretKey secretKey) {
        this.repository = repository;
        this.cryptographyService = cryptographyService;
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDTO dto) throws Exception {

        User newUser = new User(dto);
        this.cryptographyService.encryptUser(newUser);
        saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUser() {
        List<User> userList = repository.findAll();

        return userList.stream()
                .map(user -> {
                    cryptographyService.decryptUser(user);
                    return user;
                })
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new NotFoundData("Usuário não encontrado!")));

        User user = userOptional.get();
        cryptographyService.decryptUser(user);

        return user;
    }

    public User updateUser(Long id, UserDTO dto) throws Exception {
        Optional<User> userOptional = Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new NotFoundData("Usuário não encontrado")));


        User user = userOptional.get();

        this.cryptographyService.decryptUser(user);
        user.setUserDocument(dto.userDocument());
        user.setCreditCardToken(dto.creditCardToken());
        user.setAmount(dto.amount());
        this.cryptographyService.encryptUser(user);

        saveUser(user);

        return user;
    }

    public void deleteUserById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundData("Usuário não existe!");
        }
        this.repository.deleteById(id);
    }
}