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

package org.abstractj;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class CryptoParty {

    public static void loadProvider() {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    //PBKDF2
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int DERIVED_KEY_LENGTH = 160;
    public static final int ITERATIONS = 20000;
    public static final int MINIMUM_SALT_LENGTH = 16;
    public static final int MINIMUM_ITERATION = 10000;


    //AES
    public static final int MINIMUM_SECRET_KEY_SIZE = 32;

    //GCM
    public static final int IV_LENGTH = 96;
    public static final int TAG_LENGTH = 128;
}
