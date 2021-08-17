package com.github.milomarten.bittwiddle.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
public class UnsignedShort implements Comparable<UnsignedShort> {
    /**
     * The UnsignedShort representing zero
     */
    public static UnsignedShort ZERO = from(0);
    /**
     * The UnsignedShort representing one
     */
    public static UnsignedShort ONE = from(1);
    /**
     * The UnsignedShort representing two
     */
    public static UnsignedShort TWO = from(2);
    /**
     * The UnsignedShort representing the smallest value, 0
     */
    public static UnsignedShort MIN = ZERO;
    /**
     * The UnsignedShort representing the largest value, 65535
     */
    public static UnsignedShort MAX = from(65_535);

    private final short value;

    //For Lombok - Display value as the unsigned version, rather than the signed version we use here
    @ToString.Include(name = "value")
    int valueAsInt() {
        return Short.toUnsignedInt(value);
    }

    /**
     * Create an UnsignedShort
     * @param value The value, 0 to 65535
     * @return The created UnsignedShort
     */
    public static UnsignedShort from(int value) {
        return new UnsignedShort((short)value);
    }

    /**
     * Upcast this to an UnsignedWord
     * @return The upcast value
     */
    public UnsignedWord toUnsignedWord() {
        return UnsignedWord.from(Short.toUnsignedLong(value));
    }

    /**
     * Perform the logical and of this and another UnsignedShort
     * @param other The other UnsignedShort
     * @return The result of this AND other
     */
    public UnsignedShort and(UnsignedShort other) {
        return from(value & other.value);
    }

    /**
     * Perform the logical or of this and another UnsignedShort
     * @param other The other UnsignedShort
     * @return The result of this OR other
     */
    public UnsignedShort or(UnsignedShort other) {
        return from(value | other.value);
    }

    /**
     * Perform the logical exclusive-or of this and another UnsignedShort
     * @param other The other UnsignedShort
     * @return The result of this XOR other
     */
    public UnsignedShort xor(UnsignedShort other) {
        return from(value ^ other.value);
    }

    /**
     * Perform the logical negation of this UnsignedShort
     * @return The result of NOT this
     */
    public UnsignedShort not() {
        return from(~value);
    }

    /**
     * Perform addition between this and another UnsignedShort
     * @param other The other UnsignedShort
     * @return The result, possibly overflown
     */
    public OverflowableResult<UnsignedShort> add(UnsignedShort other) {
        int r = Short.toUnsignedInt(value) + Short.toUnsignedInt(other.value);
        if(r >= 0 && r <= 65_535) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r & 0xFFFF));
        }
    }

    /**
     * Perform subtraction between this and another UnsignedShort
     * @param other The other UnsignedShort
     * @return The result, possibly overflown
     */
    public OverflowableResult<UnsignedShort> subtract(UnsignedShort other) {
        int r = Short.toUnsignedInt(value) - Short.toUnsignedInt(other.value);
        if(r >= 0 && r <= 65_535) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r & 0xFFFF));
        }
    }

    /**
     * Perform multiplication between this and another UnsignedShort
     * @param other The other UnsignedShort
     * @return The result, possibly overflown
     */
    public OverflowableResult<UnsignedShort> multiply(UnsignedShort other) {
        int r = Short.toUnsignedInt(value) * Short.toUnsignedInt(other.value);
        if(r >= 0 && r <= 65_535) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r & 0xFFFF));
        }
    }

    @Override
    public int compareTo(UnsignedShort o) {
        return Integer.compareUnsigned(value, o.value);
    }
}
