package com.github.milomarten.bittwiddle.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class SignedWord implements Comparable<SignedWord> {
    /**
     * Constant of -1
     */
    public static final SignedWord NEGATIVE_ONE = from(-1);

    /**
     * Constant of 0
     */
    public static final SignedWord ZERO = from(0);

    /**
     * Constant of 1
     */
    public static final SignedWord ONE = from(1);

    /**
     * Constant of 2
     */
    public static final SignedWord TWO = from(2);

    /**
     * Constant of the smallest possible SignedShort, -2,147,483,648
     */
    public static final SignedWord MIN = from(Integer.MIN_VALUE);

    /**
     * Constant of the largest possible SignedShort, 2,147,483,647
     */
    public static final SignedWord MAX = from(Integer.MAX_VALUE);

    private final int value;

    /**
     * Create a SignedWord from a regular int
     * @param value The value to convert
     * @return The created SignedWord
     */
    public static SignedWord from(int value) {
        return new SignedWord(value);
    }

    /**
     * Perform the bitwise and of this and another SignedWord
     * @param other The other SignedWord
     * @return A SignedWord representing this AND other
     */
    public SignedWord and(SignedWord other) {
        return from(value & other.value);
    }

    /**
     * Perform the bitwise or of this and another SignedWord
     * @param other The other SignedWord
     * @return A SignedWord representing this OR other
     */
    public SignedWord or(SignedWord other) {
        return from(value | other.value);
    }

    /**
     * Perform the bitwise exclusive-or of this and another SignedWord
     * @param other The other SignedWord
     * @return A SignedWord representing this XOR other
     */
    public SignedWord xor(SignedWord other) {
        return from(value ^ other.value);
    }

    /**
     * Perform the bitwise negation of this and another SignedWord
     * @return A SignedWord representing NOT this
     */
    public SignedWord not() {
        return from(~value);
    }

    /**
     * Negate this SignedShort.
     * @return A SignedShort representing the negation, overflowing in the case of -32768
     */
    public OverflowableResult<SignedWord> negate() {
        if(value == 0) {
            return OverflowableResult.of(this);
        } else if(value == Integer.MIN_VALUE) {
            return OverflowableResult.overflow(this);
        }
        return OverflowableResult.of(from(-value));
    }

    /**
     * Add two SignedShort together
     * @param other The other SignedShort
     * @return The result, potentially overflowing
     */
    public OverflowableResult<SignedWord> add(SignedWord other) {
        int r = value + other.value;
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
    public OverflowableResult<SignedWord> subtract(SignedWord other) {
        int r = value - other.value;
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
    public OverflowableResult<SignedWord> multiply(SignedWord other) {
        long r = (long)value * (long)other.value;
        if((int)r != r) {
            return OverflowableResult.overflow(from((int)r));
        } else {
            return OverflowableResult.of(from((int)r));
        }
    }

    @Override
    public int compareTo(SignedWord o) {
        return Integer.compare(value, o.value);
    }
}
