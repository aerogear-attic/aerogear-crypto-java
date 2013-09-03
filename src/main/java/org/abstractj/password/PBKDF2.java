package org.abstractj.password;

import org.abstractj.crypto.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import static org.abstractj.crypto.Util.*;

public class PBKDF2 {

    private static final int DERIVED_KEY_LENGTH = 160;
    private static final int ITERATIONS = 20000;
    private static final int MINIMUM_SALT_LENGTH = 5;
    private static final int MINIMUM_ITERATION = 10000;

    private byte[] salt;
    private SecretKeyFactory secretKeyFactory;

    public PBKDF2() {
        final String algorithm = "PBKDF2WithHmacSHA1";
        try {
            this.secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public PBKDF2(String algorithm) {
        try {
            this.secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(String password, byte[] salt, int iterations) throws InvalidKeySpecException {
        this.salt = checkLength(salt, MINIMUM_SALT_LENGTH);
        iterations = checkSize(iterations, MINIMUM_ITERATION);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, DERIVED_KEY_LENGTH);
        return secretKeyFactory.generateSecret(spec).getEncoded();
    }

    public byte[] encrypt(String password, byte[] salt) throws InvalidKeySpecException {
        return encrypt(password, salt, ITERATIONS);
    }

    public byte[] encrypt(String password) throws InvalidKeySpecException {
        byte[] salt = new Random().randomBytes();
        return encrypt(password, salt);
    }

    public boolean validate(String password, byte[] encryptedPassword, byte[] salt) throws InvalidKeySpecException {
        byte[] attempt = encrypt(password, salt);
        return Arrays.equals(encryptedPassword, attempt);
    }

    public byte[] getSalt() {
        return salt;
    }
}
