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

import org.junit.Test;

import static org.abstractj.CryptoParty.MINIMUM_ITERATION;
import static org.abstractj.CryptoParty.MINIMUM_SECRET_KEY_SIZE;
import static org.abstractj.crypto.Util.checkLength;
import static org.abstractj.crypto.Util.checkSize;
import static org.junit.Assert.fail;

public class UtilTest {

    @Test
    public void testCheckLength() {
        try {
            byte[] data = new byte[32];
            checkLength(data, MINIMUM_SECRET_KEY_SIZE);
        } catch (Exception e) {
            fail("Should not raise any exception");
        }
    }

    @Test(expected = RuntimeException.class)
    public void testIncorrectLength() {
        byte[] data = new byte[14];
        checkLength(data, MINIMUM_SECRET_KEY_SIZE);
    }

    @Test
    public void testCheckSize() throws Exception {
        try {
            checkSize(10000, MINIMUM_ITERATION);
        } catch (Exception e) {
            fail("Should not raise any exception");
        }
    }

    @Test(expected = RuntimeException.class)
    public void testIncorrectSize() throws Exception {
        checkSize(5000, MINIMUM_ITERATION);
    }
}
