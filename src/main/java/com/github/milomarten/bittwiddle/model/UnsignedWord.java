package com.github.milomarten.bittwiddle.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
public class UnsignedWord implements Comparable<UnsignedWord> {
    /**
     * Constant of -1
     */
    public static final UnsignedWord NEGATIVE_ONE = from(-1);

    /**
     * Constant of 0
     */
    public static final UnsignedWord ZERO = from(0);

    /**
     * Constant of 1
     */
    public static final UnsignedWord ONE = from(1);

    /**
     * Constant of 2
     */
    public static final UnsignedWord TWO = from(2);

    /**
     * Constant of the smallest possible SignedShort, 0
     */
    public static final UnsignedWord MIN = ZERO;

    /**
     * Constant of the largest possible SignedShort, 4,294,967,295
     */
    public static final UnsignedWord MAX = from(4_294_967_295L);

    private final int value;

    /**
     * Create a UnsignedWord from a regular long
     * @param value The value to convert
     * @return The created UnsignedWord
     */
    public static UnsignedWord from(long value) {
        return new UnsignedWord((int)value);
    }

    //For Lombok - Display value as the unsigned version, rather than the signed version we use here
    @ToString.Include(name = "value")
    long valueAsInt() {
        return Integer.toUnsignedLong(value);
    }

    /**
     * Perform the bitwise and of this and another UnsignedWord
     * @param other The other UnsignedWord
     * @return A UnsignedWord representing this AND other
     */
    public UnsignedWord and(UnsignedWord other) {
        return from(value & other.value);
    }

    /**
     * Perform the bitwise or of this and another UnsignedWord
     * @param other The other UnsignedWord
     * @return A UnsignedWord representing this OR other
     */
    public UnsignedWord or(UnsignedWord other) {
        return from(value | other.value);
    }

    /**
     * Perform the bitwise exclusive-or of this and another UnsignedWord
     * @param other The other UnsignedWord
     * @return A UnsignedWord representing this XOR other
     */
    public UnsignedWord xor(UnsignedWord other) {
        return from(value ^ other.value);
    }

    /**
     * Perform the bitwise negation of this and another UnsignedWord
     * @return A UnsignedWord representing NOT this
     */
    public UnsignedWord not() {
        return from(~value);
    }

    /**
     * Add two UnsignedWords together
     * @param other The other UnsignedWord
     * @return The result, potentially overflowing
     */
    public OverflowableResult<UnsignedWord> add(UnsignedWord other) {
        long r = Integer.toUnsignedLong(value) + Integer.toUnsignedLong(other.value);
        if(r >= 0 && r <= 4_294_967_295L) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r));
        }
    }

    /**
     * Subtract two UnsignedWords together
     * @param other The other UnsignedWord
     * @return The result of this operation, potentially overflowing
     */
    public OverflowableResult<UnsignedWord> subtract(UnsignedWord other) {
        long r = Integer.toUnsignedLong(value) - Integer.toUnsignedLong(other.value);
        if(r >= 0 && r <= 4_294_967_295L) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r));
        }
    }

    /**
     * Multiply two UnsignedWords together
     * @param other The other UnsignedWord
     * @return The result of this operation, potentially overflowing
     */
    public OverflowableResult<UnsignedWord> multiply(UnsignedWord other) {
        long r = Integer.toUnsignedLong(value) * Integer.toUnsignedLong(other.value);
        if(r >= 0 && r <= 4_294_967_295L) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r));
        }
    }

    @Override
    public int compareTo(UnsignedWord o) {
        return Integer.compareUnsigned(value, o.value);
    }
}
