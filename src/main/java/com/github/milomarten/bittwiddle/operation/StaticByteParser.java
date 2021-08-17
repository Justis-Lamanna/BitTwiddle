package com.github.milomarten.bittwiddle.operation;

/**
 * Describes how to turn bytes into data
 * @param <T> The type of object returned
 */
public interface StaticByteParser<T> {
    /**
     * The number of bytes this object requires
     * @return The number of bytes
     */
    int numberOfBytes();

    /**
     * Parse the bytes into a concrete object
     * @param bites The bytes. Size == numberOfBytes()
     * @return The created object
     */
    T read(byte[] bites);

    /**
     * Writes a concrete object
     * @param object The object.
     * @return The created bytes. Size == numberOfBytes()
     */
    byte[] write(T object);
}
