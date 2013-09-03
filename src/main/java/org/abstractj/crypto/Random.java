package org.abstractj.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Random {

    private SecureRandom secureRandom;
    private static final String ALGORITHM = "SHA1PRNG";

    public Random() {
        this(ALGORITHM);
    }

    public Random(String algorithm) {
        try {
            this.secureRandom = SecureRandom.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public byte[] randomBytes() {
        return randomBytes(16);
    }

    public byte[] randomBytes(int n) {
        byte[] buffer = new byte[n];
        secureRandom.nextBytes(buffer);
        return buffer;
    }
}
