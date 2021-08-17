package com.github.milomarten.bittwiddle.operation;

import com.github.milomarten.bittwiddle.model.*;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public interface Operations {
    /* Retrieve Operations */

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
     * Get the specified object at the cursor
     * @param parser The parser to use
     * @param <T> The type the parser returns
     * @return The created object
     */
    <T> T get(ByteParser<T> parser);

    /* Write Operations */

    /**
     * Set a specific bit at the cursor, and continue with operations
     * @param index The index of the bit to set
     * @return Operations object for further modification
     */
    default Operations setBit(int index) {
        return replaceBit(index, bit -> Bit.ONE);
    }

    /**
     * Clear a specific bit at the cursor, and continue with operations
     * @param index The index of the bit to clear
     * @return Operations object for further modification
     */
    default Operations clearBit(int index) {
        return replaceBit(index, bit -> Bit.ZERO);
    }

    /**
     * Set a specific bit at the cursor, and continue with operations
     * @param index The index of the bit to set
     * @param bit The bit to set it to
     * @return Operations object for further modification
     */
    default Operations setBit(int index, Bit bit) {
        return replaceBit(index, b -> bit);
    }

    /**
     * Set a specific SignedByte at the cursor, and continue with operations
     * @param bite The bite to set
     * @return Operations object for further modification
     */
    default Operations setSignedByte(SignedByte bite) {
        return replaceSignedByte(b -> bite);
    }

    /**
     * Set a specific UnsignedByte at the cursor, and continue with operations
     * @param bite The bite to set
     * @return Operations object for further modification
     */
    default Operations setUnsignedByte(UnsignedByte bite) {
        return replaceUnsignedByte(b -> bite);
    }

    /**
     * Set a specific SignedShort at the cursor, and continue with operations
     * @param shorp The short to set
     * @return Operations object for further modification
     */
    default Operations setSignedShort(SignedShort shorp) {
        return replaceSignedShort(s -> shorp);
    }

    /**
     * Set a specific UnsignedShort at the cursor, and continue with operations
     * @param shorp The short to set
     * @return Operations object for further modification
     */
    default Operations setUnsignedShort(UnsignedShort shorp) {
        return replaceUnsignedShort(s -> shorp);
    }

    /**
     * Dynamically replace a bit at the cursor with a new one
     * @param index The index of the bit to modify
     * @param func A function which takes in the old bit and provides the new bit
     * @return Operations object for further modification
     */
    Operations replaceBit(int index, UnaryOperator<Bit> func);

    /**
     * Dynamically replace the SignedByte at the cursor with a new one
     * @param func A function which takes in the old SignedByte and provides a new one
     * @return Operations object for further modification
     */
    Operations replaceSignedByte(UnaryOperator<SignedByte> func);

    /**
     * Dynamically replace the UnsignedByte at the cursor with a new one
     * @param func A function which takes in the old UnsignedByte and provides a new one
     * @return Operations object for further modification
     */
    Operations replaceUnsignedByte(UnaryOperator<UnsignedByte> func);

    /**
     * Dynamically replace the SignedShort at the cursor with a new one
     * @param func A function which takes in the old SignedShort and provides a new one
     * @return Operations object for further modification
     */
    Operations replaceSignedShort(UnaryOperator<SignedShort> func);

    /**
     * Dynamically replace the UnsignedShort at the cursor with a new one
     * @param func A function which takes in the old UnsignedShort and provides a new one
     * @return Operations object for further modification
     */
    Operations replaceUnsignedShort(UnaryOperator<UnsignedShort> func);

    /**
     * Set the object at the cursor with a new object
     * @param parser The parser to use
     * @param object The object to set
     * @param <T> The type the object is
     * @return Operations object for further modification
     */
    default <T> Operations set(ByteParser<T> parser, T object) {
        return replace(parser, t -> object);
    }

    /**
     * Dynamically replace an object at the cursor, given its previous value
     * @param parser The parser to use
     * @param func A function which takes in the old object and provides a new object
     * @param <T> The type the object is
     * @return Operations object for further modification
     */
    <T> Operations replace(ByteParser<T> parser, UnaryOperator<T> func);

    /* Pipeline Operations */

    /**
     * Retrieve the pointer at the cursor, and perform additional operations relative to that pointer
     * @return Operations object for further modification
     */
    Operations follow();

    /**
     * Retrieve the pointer at the cursor, and perform a block of operations relative to it
     * @return Operations object for further modification
     */
    Operations followAndDo(Consumer<Operations> block);

    /**
     * Complete this chain of Operations
     */
    void apply() throws IOException;
}
