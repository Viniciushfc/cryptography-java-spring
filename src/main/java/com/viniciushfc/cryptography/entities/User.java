package com.viniciushfc.cryptography.entities;

import com.viniciushfc.cryptography.dtos.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userDocument;

    private String creditCardToken;

    private Long value;

    public User(UserDTO dto) {
        this.userDocument = dto.userDocument();
        this.creditCardToken = dto.creditCardToken();
        this.value = dto.value();
    }
}
