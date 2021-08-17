package com.github.milomarten.bittwiddle.operation;

/**
 * Describe how to turn bytes into data
 * @param <T> The type of object returned
 */
public interface DynamicByteParser<T> {
    /**
     * Read this object dynamically using an Op stream
     * @param ops The op stream to use
     * @return The parsed object
     */
    T read(ReadOp ops);

    /**
     * Write this object dynamically using an Op stream
     * @param ops The op stream to use
     */
    void write(WriteOp ops);
}
