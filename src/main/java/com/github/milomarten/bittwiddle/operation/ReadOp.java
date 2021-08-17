package com.github.milomarten.bittwiddle.operation;

import com.github.milomarten.bittwiddle.model.*;

/**
 * Encapsulates read-based operations
 */
public interface ReadOp extends Op<ReadOp> {
    /**
     * Get the specified bit at the cursor
     * @param index The index of the bit to retrieve
     * @return The bit at the specified index
     */
    Bit bit(int index);

    /**
     * Get the SignedByte at the cursor
     * @return The signed byte at the cursor
     */
    SignedByte signedByte();

    /**
     * Get the UnsignedByte at the cursor
     * @return The unsigned byte at the cursor
     */
    UnsignedByte unsignedByte();

    /**
     * Get the SignedShort at the cursor
     * @return The signed short at the cursor
     */
    SignedShort signedShort();

    /**
     * Get the UnsignedShort at the cursor
     * @return The unsigned short at the cursor
     */
    UnsignedShort unsignedShort();

    /**
     * Get the SignedWord at the cursor
     * @return The signed word at the cursor
     */
    SignedWord signedWord();

    /**
     * Get the UnsignedWord at the cursor
     * @return The unsigned word at the cursor
     */
    UnsignedWord unsignedWord();

    /**
     * Get the specified object at the cursor
     * @param parser The parser to use
     * @param <T> The type the parser returns
     * @return The created object
     */
    <T> T get(StaticByteParser<T> parser);

    /**
     * Get the specified object at the cursor
     * @param parser The parser to use
     * @param <T> The type the parser returns
     * @return The created object
     */
    <T> T get(DynamicByteParser<T> parser);
}
