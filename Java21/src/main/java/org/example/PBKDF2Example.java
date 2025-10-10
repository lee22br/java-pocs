package org.example;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class PBKDF2Example {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "mySecretPassword";
        byte[] salt = "someRandomSalt".getBytes(StandardCharsets.UTF_8); // Use a unique, random salt for each key
        int iterations = 65536; // Number of iterations
        int keyLength = 256; // Desired key length in bits

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey derivedKey = skf.generateSecret(spec);

        // Use derivedKey for further cryptographic operations
        System.out.println("Derived Key: " + bytesToHex(derivedKey.getEncoded()));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
