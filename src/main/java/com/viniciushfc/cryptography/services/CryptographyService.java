package com.viniciushfc.cryptography.services;

import com.viniciushfc.cryptography.dtos.UserDTO;
import com.viniciushfc.cryptography.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class CryptographyService {

    private static final String AES = "AES";

    public String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance(AES).generateKey();
    }

    public User condifyUser(UserDTO dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }

        User userCondify = new User(dto);

        SecretKey secretKey = generateSecretKey();


        String encryptedUserDocument = encrypt(dto.userDocument(), secretKey);
        String encryptedCreditCardToken = encrypt(dto.creditCardToken(), secretKey);


        userCondify.setUserDocument(encryptedUserDocument);
        userCondify.setCreditCardToken(encryptedCreditCardToken);

        return userCondify;
    }

}
