package com.example;

import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hash {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128;

    public static byte[] registration(String email, String password) {
        try {
            SecureRandom rand = SecureRandom.getInstanceStrong();
            byte[] salt = new byte[16];
            rand.nextBytes(salt);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);

            byte[] hash = factory.generateSecret(spec).getEncoded();
            DataBaseConnectivity.insertData(email, Base64.getEncoder().encodeToString(salt),
                    Base64.getEncoder().encodeToString(hash));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException e) {
            handleHashingException("Error during registration.");
        }
        return null;
    }

    public static boolean verifyPassword(String enteredPassword, String storedSalt, String storedHashedPassword) {
        try {
            byte[] salt = Base64.getDecoder().decode(storedSalt);
            byte[] storedHash = Base64.getDecoder().decode(storedHashedPassword);

            KeySpec spec = new PBEKeySpec(enteredPassword.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] enteredHash = factory.generateSecret(spec).getEncoded();

            return MessageDigest.isEqual(enteredHash, storedHash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException e) {
            handleHashingException("Error during password verification.");
        }
        return false;
    }

    private static void handleHashingException(String errorMessage) {

        System.err.println(errorMessage);
        System.err.println("An unexpected error occurred. Please contact support.");
        System.exit(1);
    }
}
