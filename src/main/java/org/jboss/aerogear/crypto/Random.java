/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Provides a cryptographically strong RNG
 */
public class Random {

    private SecureRandom secureRandom;
    private static final String ALGORITHM = "SHA1PRNG";

    /**
     * Initializes the class with the default supported algorithm
     */
    public Random() {
        this(ALGORITHM);
    }

    /**
     * Initializes the class with the provided RNG algorithm name
     *
     * @param algorithm name provided
     */
    public Random(String algorithm) {
        try {
            this.secureRandom = SecureRandom.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates random bytes which defaults to the buffer of length 16
     *
     * @return byte array representation of random bytes
     */
    public byte[] randomBytes() {
        return randomBytes(16);
    }

    /**
     * Generates an array of random bytes which length is specified by the user
     *
     * @return byte array representation of random bytes
     */
    public byte[] randomBytes(int n) {
        byte[] buffer = new byte[n];
        secureRandom.nextBytes(buffer);
        return buffer;
    }

    /**
     * Retrieves the reference to the {@link SecureRandom} object
     *
     * @return SecureRandom
     */
    public SecureRandom getSecureRandom() {
        byte[] buffer = new byte[16];
        secureRandom.nextBytes(buffer);
        return secureRandom;
    }
}
