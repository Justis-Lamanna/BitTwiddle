package com.github.milomarten.bittwiddle.operation;

import com.github.milomarten.bittwiddle.model.*;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * Encapsulates write-based operations
 */
public interface WriteOp extends Op<WriteOp> {
    /* Write Operations */
    /**
     * Set a specific bit at the cursor, and continue with operations
     * @param index The index of the bit to set
     * @return Operations object for further modification
     */
    default WriteOp setBit(int index) {
        return replaceBit(index, bit -> Bit.ONE);
    }

    /**
     * Clear a specific bit at the cursor, and continue with operations
     * @param index The index of the bit to clear
     * @return Operations object for further modification
     */
    default WriteOp clearBit(int index) {
        return replaceBit(index, bit -> Bit.ZERO);
    }

    /**
     * Set a specific bit at the cursor, and continue with operations
     * @param index The index of the bit to set
     * @param bit The bit to set it to
     * @return Operations object for further modification
     */
    default WriteOp setBit(int index, Bit bit) {
        return replaceBit(index, b -> bit);
    }

    /**
     * Set a specific SignedByte at the cursor, and continue with operations
     * @param bite The bite to set
     * @return Operations object for further modification
     */
    default WriteOp setSignedByte(SignedByte bite) {
        return replaceSignedByte(b -> bite);
    }

    /**
     * Set a specific UnsignedByte at the cursor, and continue with operations
     * @param bite The bite to set
     * @return Operations object for further modification
     */
    default WriteOp setUnsignedByte(UnsignedByte bite) {
        return replaceUnsignedByte(b -> bite);
    }

    /**
     * Set a specific SignedShort at the cursor, and continue with operations
     * @param shorp The short to set
     * @return Operations object for further modification
     */
    default WriteOp setSignedShort(SignedShort shorp) {
        return replaceSignedShort(s -> shorp);
    }

    /**
     * Set a specific UnsignedShort at the cursor, and continue with operations
     * @param shorp The short to set
     * @return Operations object for further modification
     */
    default WriteOp setUnsignedShort(UnsignedShort shorp) {
        return replaceUnsignedShort(s -> shorp);
    }

    /**
     * Set a specific SignedWord at the cursor, and continue with operations
     * @param word The word to set
     * @return Operations object for further modification
     */
    default WriteOp setSignedWord(SignedWord word) {
        return replaceSignedWord(w -> word);
    }

    /**
     * Set a specific SignedWord at the cursor, and continue with operations
     * @param word The word to set
     * @return Operations object for further modification
     */
    default WriteOp setSignedWord(UnsignedWord word) {
        return replaceUnsignedWord(w -> word);
    }

    /**
     * Dynamically replace a bit at the cursor with a new one
     * @param index The index of the bit to modify
     * @param func A function which takes in the old bit and provides the new bit
     * @return Operations object for further modification
     */
    WriteOp replaceBit(int index, UnaryOperator<Bit> func);

    /**
     * Dynamically replace the SignedByte at the cursor with a new one
     * @param func A function which takes in the old SignedByte and provides a new one
     * @return Operations object for further modification
     */
    WriteOp replaceSignedByte(UnaryOperator<SignedByte> func);

    /**
     * Dynamically replace the UnsignedByte at the cursor with a new one
     * @param func A function which takes in the old UnsignedByte and provides a new one
     * @return Operations object for further modification
     */
    WriteOp replaceUnsignedByte(UnaryOperator<UnsignedByte> func);

    /**
     * Dynamically replace the SignedShort at the cursor with a new one
     * @param func A function which takes in the old SignedShort and provides a new one
     * @return Operations object for further modification
     */
    WriteOp replaceSignedShort(UnaryOperator<SignedShort> func);

    /**
     * Dynamically replace the UnsignedShort at the cursor with a new one
     * @param func A function which takes in the old UnsignedShort and provides a new one
     * @return Operations object for further modification
     */
    WriteOp replaceUnsignedShort(UnaryOperator<UnsignedShort> func);

    /**
     * Dynamically replace the SignedWord at the cursor with a new one
     * @param func A function which takes in the old SignedWord and provides a new one
     * @return Operations object for further modification
     */
    WriteOp replaceSignedWord(UnaryOperator<SignedWord> func);

    /**
     * Dynamically replace the UnsignedWord at the cursor with a new one
     * @param func A function which takes in the old UnsignedWord and provides a new one
     * @return Operations object for further modification
     */
    WriteOp replaceUnsignedWord(UnaryOperator<UnsignedWord> func);

    /**
     * Set the object at the cursor with a new object
     * @param parser The parser to use
     * @param object The object to set
     * @param <T> The type the object is
     * @return Operations object for further modification
     */
    default <T> WriteOp set(StaticByteParser<T> parser, T object) {
        return replace(parser, t -> object);
    }

    /**
     * Set the object at the cursor with a new object
     * @param parser The parser to use
     * @param object The object to set
     * @param <T> The type the object is
     * @return Operations object for further modification
     */
    default <T> WriteOp set(DynamicByteParser<T> parser, T object) {
        return replace(parser, t -> object);
    }

    /**
     * Dynamically replace an object at the cursor, given its previous value
     * @param parser The parser to use
     * @param func A function which takes in the old object and provides a new object
     * @param <T> The type the object is
     * @return Operations object for further modification
     */
    <T> WriteOp replace(StaticByteParser<T> parser, UnaryOperator<T> func);

    /**
     * Dynamically replace an object at the cursor, given its previous value
     * @param parser The parser to use
     * @param func A function which takes in the old object and provides a new object
     * @param <T> The type the object is
     * @return Operations object for further modification
     */
    <T> WriteOp replace(DynamicByteParser<T> parser, UnaryOperator<T> func);

    /* Pipeline Operations */
    /**
     * Retrieve the pointer at the cursor, and perform a block of operations relative to it
     * @return Operations object for further modification
     */
    WriteOp followAndDo(Consumer<WriteOp> block);

    /**
     * Complete this chain of Operations
     */
    void apply() throws IOException;
}
