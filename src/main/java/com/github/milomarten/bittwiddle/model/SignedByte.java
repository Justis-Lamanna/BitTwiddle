package com.github.milomarten.bittwiddle.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a signed byte (-128 to 127)
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class SignedByte {
    /**
     * The SignedByte representing -1
     */
    public static SignedByte NEGATIVE_ONE = new SignedByte((byte)-1);
    /**
     * The SignedByte representing zero
     */
    public static SignedByte ZERO = new SignedByte((byte)0);
    /**
     * The SignedByte representing one
     */
    public static SignedByte ONE = new SignedByte((byte)1);
    /**
     * The SignedByte representing two
     */
    public static SignedByte TWO = new SignedByte((byte)2);
    /**
     * The SignedByte representing the largest value, 127
     */
    public static SignedByte MAX = new SignedByte(Byte.MAX_VALUE);
    /**
     * The SignedByte representing the smallest value, -128
     */
    public static SignedByte MIN = new SignedByte(Byte.MIN_VALUE);

    private final byte value;

    /**
     * Create a SignedByte from a regular byte
     * @param value The byte to use
     * @return A SignedByte
     */
    public static SignedByte from(byte value) {
        return new SignedByte(value);
    }

    /**
     * Create a SignedByte from a regular byte
     * @param value The byte to use
     * @return A SignedByte
     */
    static SignedByte from(int value) {
        return new SignedByte((byte)value);
    }

    /**
     * Perform the logical and of this and another SignedByte
     * @param other The other SignedByte
     * @return A SignedByte representing this AND other
     */
    public SignedByte and(SignedByte other) {
        return new SignedByte((byte)(value & other.value));
    }

    /**
     * Perform the logical or of this and another SignedByte
     * @param other The other SignedByte
     * @return A SignedByte representing this OR other
     */
    public SignedByte or(SignedByte other) {
        return new SignedByte((byte)(value | other.value));
    }

    /**
     * Perform the logical negation of this SignedByte
     * @return A SignedByte representing NOT this
     */
    public SignedByte not() {
        return new SignedByte((byte)(~value));
    }

    /**
     * Perform the logical exclusive-or of this and another SignedByte
     * @param other The other SignedByte
     * @return A SignedByte representing this XOR other
     */
    public SignedByte xor(SignedByte other) {
        return new SignedByte((byte)(value ^ other.value));
    }
}
