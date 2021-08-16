package com.github.milomarten.bittwiddle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsignedByteTest {
    @Test
    public void testAnd() {
        assertEquals(UnsignedByte.TWO, UnsignedByte.from(10).and(UnsignedByte.from(3)));
    }

    @Test
    public void testAndBigNumbers() {
        assertEquals(UnsignedByte.MAX, UnsignedByte.MAX.and(UnsignedByte.MAX));
    }

    @Test
    public void testOr() {
        assertEquals(UnsignedByte.from(11), UnsignedByte.from(10).or(UnsignedByte.from(3)));
    }

    @Test
    public void testNot() {
        assertEquals(UnsignedByte.from(245), UnsignedByte.from(10).not());
    }

    @Test
    public void testXor() {
        assertEquals(UnsignedByte.from(9), UnsignedByte.from(10).xor(UnsignedByte.from(3)));
    }

    @Test
    public void testAddNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedByte.TWO), UnsignedByte.ONE.add(UnsignedByte.ONE));
    }

    @Test
    public void testAddWithOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedByte.ZERO), UnsignedByte.MAX.add(UnsignedByte.ONE));
    }

    @Test
    public void testSubtractNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedByte.ONE), UnsignedByte.TWO.subtract(UnsignedByte.ONE));
    }

    @Test
    public void testSubtractOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedByte.MAX), UnsignedByte.ZERO.subtract(UnsignedByte.ONE));
    }

    @Test
    public void testMultiplyNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedByte.from(4)), UnsignedByte.TWO.multiply(UnsignedByte.TWO));
    }

    @Test
    public void testMultiplyOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedByte.ONE), UnsignedByte.MAX.multiply(UnsignedByte.MAX));
    }
}