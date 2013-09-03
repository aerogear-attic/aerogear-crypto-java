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

public class Util {

    public static byte[] checkLength(byte[] data, int size) {
        if (data == null || data.length < size)
            throw new RuntimeException("Invalid length: " + data.length);
        return data;
    }

    public static int checkSize(int size, int minimumSize) {
        if (size < minimumSize)
            throw new RuntimeException("Invalid size: " + size);
        return size;
    }

    public static String formatter(Algorithm algorithm, BlockMode mode) {
        return String.format("%s/%s", algorithm, mode);
    }

    public static String formatter(BlockMode mode, Padding padding) {
        return String.format("%s/%s", mode, padding);
    }
}
