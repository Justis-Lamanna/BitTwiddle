package com.github.milomarten.bittwiddle.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class SignedShort implements Comparable<SignedShort> {
    /**
     * Constant of -1
     */
    public static final SignedShort NEGATIVE_ONE = from(-1);

    /**
     * Constant of 0
     */
    public static final SignedShort ZERO = from(0);

    /**
     * Constant of 1
     */
    public static final SignedShort ONE = from(1);

    /**
     * Constant of 2
     */
    public static final SignedShort TWO = from(2);

    /**
     * Constant of the smallest possible SignedShort, -32,768
     */
    public static final SignedShort MIN = from(Short.MIN_VALUE);

    /**
     * Constant of the largest possible SignedShort, 32,767
     */
    public static final SignedShort MAX = from(Short.MAX_VALUE);

    private final short value;

    /**
     * Create a SignedShort from a regular short
     * @param value The value to convert
     * @return The created SignedShort
     */
    public static SignedShort from(short value) {
        return new SignedShort(value);
    }

    /**
     * Create a SignedShort from a regular short
     * @param value The value to convert
     * @return The created SignedShort
     */
    public static SignedShort from(int value) {
        return new SignedShort((short) value);
    }

    /**
     * Perform the bitwise and of this and another SignedShort
     * @param other The other SignedShort
     * @return A SignedShort representing this AND other
     */
    public SignedShort and(SignedShort other) {
        return from(value & other.value);
    }

    /**
     * Perform the bitwise or of this and another SignedShort
     * @param other The other SignedShort
     * @return A SignedShort representing this OR other
     */
    public SignedShort or(SignedShort other) {
        return from(value | other.value);
    }

    /**
     * Perform the bitwise exclusive-or of this and another SignedShort
     * @param other The other SignedShort
     * @return A SignedShort representing this XOR other
     */
    public SignedShort xor(SignedShort other) {
        return from(value ^ other.value);
    }

    /**
     * Perform the bitwise negation of this and another SignedShort
     * @return A SignedShort representing NOT this
     */
    public SignedShort not() {
        return from(~value);
    }

    /**
     * Negate this SignedShort.
     * @return A SignedShort representing the negation, overflowing in the case of -32768
     */
    public OverflowableResult<SignedShort> negate() {
        if(value == 0) {
            return OverflowableResult.of(this);
        } else if(value == Short.MIN_VALUE) {
            return OverflowableResult.overflow(this);
        }
        return OverflowableResult.of(from(-value));
    }

    /**
     * Add two SignedShort together
     * @param other The other SignedShort
     * @return The result, potentially overflowing
     */
    public OverflowableResult<SignedShort> add(SignedShort other) {
        short r = (short)(value + other.value);
        if (((value ^ r) & (other.value ^ r)) < 0) {
            return OverflowableResult.overflow(from(r));
        } else {
            return OverflowableResult.of(from(r));
        }
    }

    /**
     * Subtract two SignedShorts together
     * @param other The other SignedShort
     * @return The result of this operation, potentially overflowing
     */
    public OverflowableResult<SignedShort> subtract(SignedShort other) {
        short r = (short)(value - other.value);
        if((value > 0 && other.value < 0 && r < 0) || (value < 0 && other.value > 0 && r > 0)) {
            return OverflowableResult.overflow(from(r));
        } else {
            return OverflowableResult.of(from(r));
        }
    }

    /**
     * Multiply two SignedShorts together
     * @param other The other SignedShort
     * @return The result of this operation, potentially overflowing
     */
    public OverflowableResult<SignedShort> multiply(SignedShort other) {
        long r = (long)value * (long)other.value;
        if((short)r != r) {
            return OverflowableResult.overflow(from((short)r));
        } else {
            return OverflowableResult.of(from((short)r));
        }
    }

    @Override
    public int compareTo(SignedShort o) {
        return Short.compare(value, o.value);
    }
}
