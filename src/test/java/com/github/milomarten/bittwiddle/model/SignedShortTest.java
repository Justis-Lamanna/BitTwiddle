package com.github.milomarten.bittwiddle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignedShortTest {
    @Test
    public void testAnd() {
        assertEquals(SignedShort.TWO, SignedShort.from(10).and(SignedShort.from(3)));
    }

    @Test
    public void testOr() {
        assertEquals(SignedShort.from(11), SignedShort.from(10).or(SignedShort.from(3)));
    }

    @Test
    public void testNot() {
        assertEquals(SignedShort.from(-11), SignedShort.from(10).not());
    }

    @Test
    public void testXor() {
        assertEquals(SignedShort.from(9), SignedShort.from(10).xor(SignedShort.from(3)));
    }

    @Test
    public void testNegateNoOverflow() {
        assertEquals(OverflowableResult.of(SignedShort.NEGATIVE_ONE), SignedShort.ONE.negate());
    }

    @Test
    public void testNegateOverflow() {
        assertEquals(OverflowableResult.overflow(SignedShort.MIN), SignedShort.MIN.negate());
    }

    @Test
    public void testAddNoOverflow() {
        assertEquals(OverflowableResult.of(SignedShort.TWO), SignedShort.ONE.add(SignedShort.ONE));
    }

    @Test
    public void testAddOverflow() {
        assertEquals(OverflowableResult.overflow(SignedShort.MIN), SignedShort.MAX.add(SignedShort.ONE));
    }

    @Test
    public void testSubtractNoOverflow() {
        SignedShort a = SignedShort.ONE;
        SignedShort b = SignedShort.ONE;
        assertEquals(OverflowableResult.of(SignedShort.ZERO), a.subtract(b));
    }

    @Test
    public void testSubtractOverflow() {
        SignedShort a = SignedShort.MIN;
        SignedShort b = SignedShort.ONE;
        assertEquals(OverflowableResult.overflow(SignedShort.MAX), a.subtract(b));
    }

    @Test
    public void testMultiplyNoOverflow() {
        SignedShort a = SignedShort.TWO;
        SignedShort b = SignedShort.TWO;
        assertEquals(OverflowableResult.of(SignedShort.from(4)), a.multiply(b));
    }

    @Test
    public void testMultiplyOverflow() {
        SignedShort a = SignedShort.MAX;
        SignedShort b = SignedShort.MAX;
        assertEquals(OverflowableResult.overflow(SignedShort.ONE), a.multiply(b));
    }
}