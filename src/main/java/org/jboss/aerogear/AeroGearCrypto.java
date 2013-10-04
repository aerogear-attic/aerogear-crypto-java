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
package org.jboss.aerogear;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jboss.aerogear.crypto.Util;
import org.jboss.aerogear.crypto.password.DefaultPbkdf2;
import org.jboss.aerogear.crypto.password.Pbkdf2;

import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class AeroGearCrypto {

    public static final String PROVIDER = Util.isAndroid() ? "SC" : "BC";

    static {
        if (Util.isAndroid()) {
            Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
        } else {
            if (Security.getProvider("BC") == null) {
                Security.addProvider(new BouncyCastleProvider());
            }
        }
    }

    //PBKDF2
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int DERIVED_KEY_LENGTH = 256;
    public static final int ITERATIONS = 20000;
    public static final int MINIMUM_SALT_LENGTH = 16;
    public static final int MINIMUM_ITERATION = 10000;
    //AES
    public static final int MINIMUM_SECRET_KEY_SIZE = 32;
    //GCM
    public static final int IV_LENGTH = 96;
    public static final int TAG_LENGTH = 128;

    public static Pbkdf2 pbkdf2() {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
            return new DefaultPbkdf2(keyFactory);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
