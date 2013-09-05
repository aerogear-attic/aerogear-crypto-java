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

import org.abstractj.CryptoParty;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

import static org.abstractj.crypto.Algorithm.AES;
import static org.abstractj.crypto.BlockMode.GCM;
import static org.abstractj.crypto.Util.formatter;

public class CipherScheme {

    private static String transformation;
    private byte[] ivBytes;

    static {
        CryptoParty.loadProvider();
    }

    public CipherScheme(BlockMode mode) {
        transformation = formatter(AES, mode);
        this.ivBytes = new Random().randomBytes();
    }

    public CipherScheme() {
        this(GCM);
    }

    public Cipher getNewCipher() {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return cipher;
    }

    public byte[] getIV() {
        return ivBytes;
    }
}
