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
public class SignedByte implements Comparable<SignedByte> {
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
    public static SignedByte from(int value) {
        return new SignedByte((byte)value);
    }

    /**
     * Upcast this SignedByte to a SignedShort
     * @return A SignedShort equal to this SignedByte
     */
    public SignedShort toSignedShort() {
        return SignedShort.from(value);
    }

    /**
     * Upcast this SignedByte to a SignedWord
     * @return A SignedWord equal to this SignedByte
     */
    public SignedWord toSignedWord() {
        return SignedWord.from(value);
    }

    /**
     * Perform the logical and of this and another SignedByte
     * @param other The other SignedByte
     * @return A SignedByte representing this AND other
     */
    public SignedByte and(SignedByte other) {
        return from(value & other.value);
    }

    /**
     * Perform the logical or of this and another SignedByte
     * @param other The other SignedByte
     * @return A SignedByte representing this OR other
     */
    public SignedByte or(SignedByte other) {
        return from(value | other.value);
    }

    /**
     * Perform the logical negation of this SignedByte
     * @return A SignedByte representing NOT this
     */
    public SignedByte not() {
        return from(~value);
    }

    /**
     * Perform the logical exclusive-or of this and another SignedByte
     * @param other The other SignedByte
     * @return A SignedByte representing this XOR other
     */
    public SignedByte xor(SignedByte other) {
        return from(value ^ other.value);
    }

    /**
     * Negate this byte.
     * @return A SignedByte representing the negation, overflowing in the case of negating -128.
     */
    public OverflowableResult<SignedByte> negate() {
        if(value == 0) {
            return OverflowableResult.of(this);
        } else if (value == Byte.MIN_VALUE) {
            return OverflowableResult.overflow(this);
        }
        return OverflowableResult.of(SignedByte.from(-value));
    }

    /**
     * Add two signed bytes together
     * @param other The other SignedByte
     * @return The result of this operation, potentially overflowing
     */
    public OverflowableResult<SignedByte> add(SignedByte other) {
        byte r = (byte)(value + other.value);
        if (((value ^ r) & (other.value ^ r)) < 0) {
            return OverflowableResult.overflow(from(r));
        } else {
            return OverflowableResult.of(from(r));
        }
    }

    /**
     * Subtract two signed bytes together
     * @param other The other SignedByte
     * @return The result of this operation, potentially overflowing
     */
    public OverflowableResult<SignedByte> subtract(SignedByte other) {
        byte r = (byte)(value - other.value);
        if((value > 0 && other.value < 0 && r < 0) || (value < 0 && other.value > 0 && r > 0)) {
            return OverflowableResult.overflow(from(r));
        } else {
            return OverflowableResult.of(from(r));
        }
    }

    /**
     * Multiply two signed bytes together
     * @param other The other SignedByte
     * @return The result of this operation, potentially overflowing
     */
    public OverflowableResult<SignedByte> multiply(SignedByte other) {
        long r = (long)value * (long)other.value;
        if((byte)r != r) {
            return OverflowableResult.overflow(from((byte)r));
        } else {
            return OverflowableResult.of(from((byte)r));
        }
    }

    @Override
    public int compareTo(SignedByte o) {
        return Byte.compare(value, o.value);
    }
}
