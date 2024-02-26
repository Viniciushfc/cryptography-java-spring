package com.viniciushfc.cryptography.services;

import com.viniciushfc.cryptography.dtos.UserDTO;
import com.viniciushfc.cryptography.entities.User;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class CryptographyService {

    private static final String SECRET_KEY = "mySecretKey12345";

    public String encode(String password) throws Exception {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Erro na criptografia");
        }
    }

    public String decrypt(String encryptedPassword) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Erro na descriptografia");
        }
    }

    public User encryptUser(User user) throws Exception {

        user.setUserDocument(encode(user.getUserDocument()));
        user.setCreditCardToken(encode(user.getCreditCardToken()));

        return user;

    }

    public User decryptUser(User user) {

        user.setUserDocument(decrypt(user.getUserDocument()));
        user.setCreditCardToken(decrypt(user.getCreditCardToken()));

        return user;
    }
}
