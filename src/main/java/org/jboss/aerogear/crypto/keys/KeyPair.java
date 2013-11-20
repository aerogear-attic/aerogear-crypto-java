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
package org.jboss.aerogear.crypto.keys;

import org.jboss.aerogear.AeroGearCrypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

/**
 * Represents a pair of cryptographic keys (a public and a private key) used for asymmetric encryption
 */
public class KeyPair {

    private final java.security.KeyPair keyPair;

    /**
     * Initialize the key pair with the standard curve name
     */
    public KeyPair() {

        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance("ECDH", AeroGearCrypto.PROVIDER);
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(ecSpec, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        this.keyPair = keyGen.generateKeyPair();

    }

    /**
     * Access to the public key
     * @return the reference to the public key
     */
    public java.security.PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    /**
     * Access to the private key
     * @return the reference to the private key
     */
    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }
}
