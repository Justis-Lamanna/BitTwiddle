package com.github.milomarten.bittwiddle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignedByteTest {
    @Test
    public void testAnd() {
        assertEquals(SignedByte.TWO, SignedByte.from(10).and(SignedByte.from(3)));
    }

    @Test
    public void testOr() {
        assertEquals(SignedByte.from(11), SignedByte.from(10).or(SignedByte.from(3)));
    }

    @Test
    public void testNot() {
        assertEquals(SignedByte.from(-11), SignedByte.from(10).not());
    }

    @Test
    public void testXor() {
        assertEquals(SignedByte.from(9), SignedByte.from(10).xor(SignedByte.from(3)));
    }

    @Test
    public void testNegateNoOverflow() {
        assertEquals(OverflowableResult.of(SignedByte.NEGATIVE_ONE), SignedByte.ONE.negate());
    }

    @Test
    public void testNegateOverflow() {
        assertEquals(OverflowableResult.overflow(SignedByte.MIN), SignedByte.MIN.negate());
    }

    @Test
    public void testAddNoOverflow() {
        SignedByte a = SignedByte.ONE;
        SignedByte b = SignedByte.ONE;
        assertEquals(OverflowableResult.of(SignedByte.TWO), a.add(b));
    }

    @Test
    public void testAddOverflow() {
        SignedByte a = SignedByte.MAX;
        SignedByte b = SignedByte.ONE;
        assertEquals(OverflowableResult.overflow(SignedByte.MIN), a.add(b));
    }

    @Test
    public void testSubtractNoOverflow() {
        SignedByte a = SignedByte.ONE;
        SignedByte b = SignedByte.ONE;
        assertEquals(OverflowableResult.of(SignedByte.ZERO), a.subtract(b));
    }

    @Test
    public void testSubtractOverflow() {
        SignedByte a = SignedByte.MIN;
        SignedByte b = SignedByte.ONE;
        assertEquals(OverflowableResult.overflow(SignedByte.MAX), a.subtract(b));
    }

    @Test
    public void testMultiplyNoOverflow() {
        SignedByte a = SignedByte.TWO;
        SignedByte b = SignedByte.TWO;
        assertEquals(OverflowableResult.of(SignedByte.from(4)), a.multiply(b));
    }

    @Test
    public void testMultiplyOverflow() {
        SignedByte a = SignedByte.MAX;
        SignedByte b = SignedByte.MAX;
        assertEquals(OverflowableResult.overflow(SignedByte.ONE), a.multiply(b));
    }
}