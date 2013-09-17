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

    public SecureRandom getSecureRandom() {
        byte[] buffer = new byte[16];
        secureRandom.nextBytes(buffer);
        return secureRandom;
    }
}
