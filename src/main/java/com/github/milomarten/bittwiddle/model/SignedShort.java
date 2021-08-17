package com.github.milomarten.bittwiddle.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class SignedShort {
    /**
     * Constant of -1
     */
    public static final SignedShort NEGATIVE_ONE = new SignedShort((short) -1);

    /**
     * Constant of 0
     */
    public static final SignedShort ZERO = new SignedShort((short) 0);

    /**
     * Constant of 1
     */
    public static final SignedShort ONE = new SignedShort((short) 1);

    /**
     * Constant of 2
     */
    public static final SignedShort TWO = new SignedShort((short) 2);

    /**
     * Constant of the smallest possible SignedShort, -32,768
     */
    public static final SignedShort MIN = new SignedShort(Short.MIN_VALUE);

    /**
     * Constant of the largest possible SignedShort, 32,767
     */
    public static final SignedShort MAX = new SignedShort(Short.MAX_VALUE);

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

    public SignedShort and(SignedShort other) {
        return from(value & other.value);
    }

    public SignedShort or(SignedShort other) {
        return from(value | other.value);
    }

    public SignedShort xor(SignedShort other) {
        return from(value ^ other.value);
    }

    public SignedShort not() {
        return from(~value);
    }
}
