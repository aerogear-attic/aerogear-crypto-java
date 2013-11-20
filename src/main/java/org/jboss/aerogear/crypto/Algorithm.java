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

/**
 * Representation of the algorithms supported
 */
public enum Algorithm {

    AES("AES", 256);

    private final String name;
    private final int keySize;

    Algorithm(String name, int keySize) {
        this.name = name;
        this.keySize = keySize;
    }

    /**
     * Algorithm name
     * @return string representation of the algorithm name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Key size
     * @return integer representation of the key size
     */
    public int getKeySize() {
        return keySize;
    }
}
