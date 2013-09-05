/**
 * Copyright 2013 Bruno Oliveira, and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.abstractj.password;

import org.abstractj.crypto.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import static org.abstractj.CryptoParty.DERIVED_KEY_LENGTH;
import static org.abstractj.CryptoParty.ITERATIONS;
import static org.abstractj.CryptoParty.MINIMUM_ITERATION;
import static org.abstractj.CryptoParty.MINIMUM_SALT_LENGTH;
import static org.abstractj.CryptoParty.PBKDF2_ALGORITHM;
import static org.abstractj.crypto.Util.checkLength;
import static org.abstractj.crypto.Util.checkSize;

public class Pbkdf2 {

    private byte[] salt;
    private SecretKeyFactory secretKeyFactory;

    public Pbkdf2() {
        try {
            this.secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public Pbkdf2(String algorithm) {
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
