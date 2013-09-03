package org.abstractj.crypto;

public class Util {

    public static byte[] checkLength(byte[] data, int size) {
        if (data == null || data.length != size)
            throw new RuntimeException("Invalid size: " + data.length);
        return data;
    }

    public static int checkSize(int size, int minimumSize) {
        if (size < minimumSize)
            throw new RuntimeException("Invalid size: " + size);
        return size;
    }
}
