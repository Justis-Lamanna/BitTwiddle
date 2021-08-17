package com.github.milomarten.bittwiddle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsignedShortTest {
    @Test
    public void testAnd() {
        assertEquals(UnsignedShort.TWO, UnsignedShort.from(10).and(UnsignedShort.from(3)));
    }

    @Test
    public void testAndBigNumbers() {
        assertEquals(UnsignedShort.MAX, UnsignedShort.MAX.and(UnsignedShort.MAX));
    }

    @Test
    public void testOr() {
        assertEquals(UnsignedShort.from(11), UnsignedShort.from(10).or(UnsignedShort.from(3)));
    }

    @Test
    public void testNot() {
        assertEquals(UnsignedShort.from(65525), UnsignedShort.from(10).not());
    }

    @Test
    public void testXor() {
        assertEquals(UnsignedShort.from(9), UnsignedShort.from(10).xor(UnsignedShort.from(3)));
    }

    @Test
    public void testAddNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedShort.TWO), UnsignedShort.ONE.add(UnsignedShort.ONE));
    }

    @Test
    public void testAddWithOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedShort.ZERO), UnsignedShort.MAX.add(UnsignedShort.ONE));
    }

    @Test
    public void testSubtractNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedShort.ONE), UnsignedShort.TWO.subtract(UnsignedShort.ONE));
    }

    @Test
    public void testSubtractOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedShort.MAX), UnsignedShort.ZERO.subtract(UnsignedShort.ONE));
    }

    @Test
    public void testMultiplyNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedShort.from(4)), UnsignedShort.TWO.multiply(UnsignedShort.TWO));
    }

    @Test
    public void testMultiplyOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedShort.ONE), UnsignedShort.MAX.multiply(UnsignedShort.MAX));
    }
}