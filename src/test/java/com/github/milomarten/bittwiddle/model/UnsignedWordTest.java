package com.github.milomarten.bittwiddle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsignedWordTest {
    @Test
    public void testAnd() {
        assertEquals(UnsignedWord.TWO, UnsignedWord.from(10).and(UnsignedWord.from(3)));
    }

    @Test
    public void testAndBigNumbers() {
        assertEquals(UnsignedWord.MAX, UnsignedWord.MAX.and(UnsignedWord.MAX));
    }

    @Test
    public void testOr() {
        assertEquals(UnsignedWord.from(11), UnsignedWord.from(10).or(UnsignedWord.from(3)));
    }

    @Test
    public void testNot() {
        assertEquals(UnsignedWord.from(4294967285L), UnsignedWord.from(10).not());
    }

    @Test
    public void testXor() {
        assertEquals(UnsignedWord.from(9), UnsignedWord.from(10).xor(UnsignedWord.from(3)));
    }

    @Test
    public void testAddNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedWord.TWO), UnsignedWord.ONE.add(UnsignedWord.ONE));
    }

    @Test
    public void testAddWithOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedWord.ZERO), UnsignedWord.MAX.add(UnsignedWord.ONE));
    }

    @Test
    public void testSubtractNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedWord.ONE), UnsignedWord.TWO.subtract(UnsignedWord.ONE));
    }

    @Test
    public void testSubtractOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedWord.MAX), UnsignedWord.ZERO.subtract(UnsignedWord.ONE));
    }

    @Test
    public void testMultiplyNoOverflow() {
        assertEquals(OverflowableResult.of(UnsignedWord.from(4)), UnsignedWord.TWO.multiply(UnsignedWord.TWO));
    }

    @Test
    public void testMultiplyOverflow() {
        assertEquals(OverflowableResult.overflow(UnsignedWord.ONE), UnsignedWord.MAX.multiply(UnsignedWord.MAX));
    }
}