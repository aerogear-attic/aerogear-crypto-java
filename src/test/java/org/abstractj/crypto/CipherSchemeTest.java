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

import org.junit.Before;
import org.junit.Test;

import javax.crypto.Cipher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CipherSchemeTest {

    private CipherScheme cipherScheme;

    @Before
    public void setUp() {
        this.cipherScheme = new CipherScheme();
    }

    @Test
    public void testGetNewCipher() throws Exception {
        String defaultTransformation = "AES/GCM/NoPadding";
        Cipher cipher = cipherScheme.getNewCipher();
        assertEquals("Should return the default transformation", defaultTransformation, cipher.getAlgorithm());
    }

    @Test
    public void testGetNewAlternativeCipher() throws Exception {
        String defaultTransformation = "AES/CBC/PKCS7Padding";
        cipherScheme = new CipherScheme(BlockMode.CBC);
        Cipher cipher = cipherScheme.getNewCipher();
        assertEquals("Should return the default transformation", defaultTransformation, cipher.getAlgorithm());
    }

    @Test
    public void testGetIV() throws Exception {
        assertNotNull("IV should not be null", cipherScheme.getIV());
        assertEquals("IV size should respect the mininum length", 16, cipherScheme.getIV().length);
    }

}
