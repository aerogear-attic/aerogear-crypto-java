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
package org.abstractj.crypto;

import org.abstractj.encoders.Encoder;
import org.abstractj.keys.PrivateKey;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.KeyAgreement;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;

import static org.abstractj.CryptoParty.MINIMUM_SECRET_KEY_SIZE;
import static org.abstractj.CryptoParty.TAG_LENGTH;
import static org.abstractj.crypto.Util.checkLength;
import static org.abstractj.crypto.Util.newBuffer;
import static org.abstractj.crypto.Util.newByteArray;

public class CryptoBox {

    private byte[] key;
    private AEADBlockCipher cipher;
    private byte[] authData;

    public CryptoBox(byte[] key) {
        checkLength(key, MINIMUM_SECRET_KEY_SIZE);
        this.cipher = BlockCipher.getInstance();
        this.key = key;

    }

    public CryptoBox(PrivateKey key) {
        this(key.toBytes());
    }

    public CryptoBox(String key, Encoder encoder) {
        this(encoder.decode(key));
    }

    public CryptoBox(java.security.PrivateKey privateKey, PublicKey publicKey) {
        this.cipher = BlockCipher.getInstance();
        this.key = generateSecret(privateKey, publicKey);
        checkLength(key, MINIMUM_SECRET_KEY_SIZE);
    }

    private byte[] generateSecret(java.security.PrivateKey privateKey, PublicKey publicKey) {
        MessageDigest hash = null;
        KeyAgreement keyAgree = null;
        try {
            hash = MessageDigest.getInstance("SHA-256", "BC");
            keyAgree = KeyAgreement.getInstance("ECDH", "BC");
            keyAgree.init(privateKey);
            keyAgree.doPhase(publicKey, true);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Fail: ", e);
        }

        return hash.digest(keyAgree.generateSecret());
    }

    public byte[] encrypt(final byte[] IV, final byte[] message) throws RuntimeException {

        AEADParameters aeadParams = new AEADParameters(
                new KeyParameter(key), TAG_LENGTH,
                IV,
                authData);

        cipher.init(true, aeadParams);
        byte[] cipherText = newBuffer(cipher.getOutputSize(message.length));
        int outputOffset = cipher.processBytes(message, 0, message.length, cipherText, 0);

        try {
            cipher.doFinal(cipherText, outputOffset);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException("Error: ", e);
        }

        return cipherText;
    }

    public byte[] encrypt(String IV, String message, Encoder encoder) {
        return encrypt(encoder.decode(IV), encoder.decode(message));
    }

    public byte[] decrypt(byte[] IV, byte[] cipherText) throws RuntimeException {

        AEADParameters aeadParams = new AEADParameters(
                new KeyParameter(key), TAG_LENGTH,
                IV,
                authData);

        cipher.init(false, aeadParams);
        byte[] buffer = newByteArray(cipherText);
        byte[] plainText = newBuffer(cipher.getOutputSize(cipherText.length));
        int outputOffset = cipher.processBytes(buffer, 0, buffer.length, plainText, 0);

        try {
            cipher.doFinal(plainText, outputOffset);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException("Error: ", e);
        }

        return plainText;
    }

    public byte[] decrypt(String IV, String cipherText, Encoder encoder) {
        return decrypt(encoder.decode(IV), encoder.decode(cipherText));
    }

    //TODO
    public void updateAAD(byte[] authData) {
        this.authData = authData;
    }
}
