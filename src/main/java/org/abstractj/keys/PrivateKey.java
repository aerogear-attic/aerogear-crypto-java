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
package org.abstractj.keys;

import org.abstractj.encoders.Encoder;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;

import static org.abstractj.CryptoParty.MINIMUM_SECRET_KEY_SIZE;
import static org.abstractj.crypto.Algorithm.AES;
import static org.abstractj.crypto.Util.checkLength;
import static org.abstractj.encoders.Encoder.HEX;

public class PrivateKey {

    private byte[] secretKey;

    public PrivateKey() {
        KeyGenerator kGen;
        try {
            kGen = KeyGenerator.getInstance(AES.toString());
            kGen.init(AES.getKeySize());
            this.secretKey = kGen.generateKey().getEncoded();
            checkLength(secretKey, MINIMUM_SECRET_KEY_SIZE);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: ", e);
        }
    }

    public PrivateKey(byte[] keyBytes) {
        checkLength(keyBytes, MINIMUM_SECRET_KEY_SIZE);
        this.secretKey = keyBytes;
    }

    public PrivateKey(String secretKey) {
        this.secretKey = HEX.decode(secretKey);
        checkLength(this.secretKey, MINIMUM_SECRET_KEY_SIZE);
    }

    public PrivateKey(String secretKey, Encoder encoder) {
        checkLength(encoder.decode(secretKey), MINIMUM_SECRET_KEY_SIZE);
        this.secretKey = encoder.decode(secretKey);
    }

    public byte[] toBytes() {
        return secretKey;
    }

    @Override
    public String toString() {
        return HEX.encode(secretKey);
    }
}
