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
            System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
            System.out.println("Hashed Password: " + Base64.getEncoder().encodeToString(hash));
            DataBaseConnectivity.insertData(email, Base64.getEncoder().encodeToString(salt),
                    Base64.getEncoder().encodeToString(hash));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException e) {
            handleHashingException("Error during password hashing for registration.", e);
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
            handleHashingException("Error during password verification.", e);
        }
        return false;
    }

    private static void handleHashingException(String errorMessage, Exception e) {

        System.err.println(errorMessage);
        e.printStackTrace();


        System.err.println("An unexpected error occurred. Please contact support.");
    }
}
