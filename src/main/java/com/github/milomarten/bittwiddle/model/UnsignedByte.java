package com.github.milomarten.bittwiddle.model;

import com.github.milomarten.bittwiddle.operation.ByteParser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
public class UnsignedByte implements Comparable<UnsignedByte> {
    /**
     * The SignedByte representing zero
     */
    public static UnsignedByte ZERO = from(0);
    /**
     * The SignedByte representing one
     */
    public static UnsignedByte ONE = from(1);
    /**
     * The SignedByte representing two
     */
    public static UnsignedByte TWO = from(2);
    /**
     * The SignedByte representing the smallest value, 0
     */
    public static UnsignedByte MIN = ZERO;
    /**
     * The SignedByte representing the largest value, 255
     */
    public static UnsignedByte MAX = from(255);

    private final byte value;

    //For Lombok - Display value as the unsigned version, rather than the signed version we use here
    @ToString.Include(name = "value")
    int valueAsInt() {
        return Byte.toUnsignedInt(value);
    }

    /**
     * Create an UnsignedByte
     * @param value The value, 0 to 255
     * @return The created UnsignedByte
     */
    public static UnsignedByte from(int value) {
        return new UnsignedByte((byte)value);
    }

    /**
     * Upcast this UnsignedByte to an UnsignedShort
     * @return The upcasted value
     */
    public UnsignedShort toUnsignedShort() {
        return UnsignedShort.from(Byte.toUnsignedInt(value));
    }

    /**
     * Perform the logical and of this and another UnsignedByte
     * @param other The other UnsignedByte
     * @return An UnsignedByte representing this AND other
     */
    public UnsignedByte and(UnsignedByte other) {
        return from(value & other.value);
    }

    /**
     * Perform the logical or of this and another UnsignedByte
     * @param other The other UnsignedByte
     * @return An UnsignedByte representing this OR other
     */
    public UnsignedByte or(UnsignedByte other) {
        return from(value | other.value);
    }

    /**
     * Perform the logical not of this and another UnsignedByte
     * @return An UnsignedByte representing NOT this
     */
    public UnsignedByte not() {
        return from(~value);
    }

    /**
     * Perform the logical exclusive-or of this and another UnsignedByte
     * @param other The other UnsignedByte
     * @return An UnsignedByte representing this XOR other
     */
    public UnsignedByte xor(UnsignedByte other) {
        return from(value ^ other.value);
    }

    /**
     * Perform addition between this and another UnsignedByte
     * @param other The other UnsignedByte
     * @return The result, possibly overflown
     */
    public OverflowableResult<UnsignedByte> add(UnsignedByte other) {
        int r = Byte.toUnsignedInt(value) + Byte.toUnsignedInt(other.value);
        if(r >= 0 && r <= 255) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r & 0xFF));
        }
    }

    /**
     * Perform subtraction between this and another UnsignedByte
     * @param other The other UnsignedByte
     * @return The result, possibly overflown
     */
    public OverflowableResult<UnsignedByte> subtract(UnsignedByte other) {
        int r = Byte.toUnsignedInt(value) - Byte.toUnsignedInt(other.value);
        if(r >= 0 && r <= 255) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r & 0xFF));
        }
    }

    /**
     * Perform multiplication between this and another UnsignedByte
     * @param other The other UnsignedByte
     * @return The result, possibly overflown
     */
    public OverflowableResult<UnsignedByte> multiply(UnsignedByte other) {
        int r = Byte.toUnsignedInt(value) * Byte.toUnsignedInt(other.value);
        if(r >= 0 && r <= 255) {
            return OverflowableResult.of(from(r));
        } else {
            return OverflowableResult.overflow(from(r & 0xFF));
        }
    }

    @Override
    public int compareTo(UnsignedByte o) {
        return Integer.compareUnsigned(value, o.value);
    }
}
