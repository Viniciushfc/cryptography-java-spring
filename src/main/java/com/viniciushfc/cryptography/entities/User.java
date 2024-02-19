package com.viniciushfc.cryptography.entities;

import com.viniciushfc.cryptography.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userDocument;

    private String creditCardToken;

    private Long amount;

    public User(UserDTO dto) {
        this.userDocument = dto.userDocument();
        this.creditCardToken = dto.creditCardToken();
        this.amount = dto.amount();
    }
}
