package com.github.milomarten.bittwiddle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignedWordTest {
    @Test
    public void testAnd() {
        assertEquals(SignedWord.TWO, SignedWord.from(10).and(SignedWord.from(3)));
    }

    @Test
    public void testOr() {
        assertEquals(SignedWord.from(11), SignedWord.from(10).or(SignedWord.from(3)));
    }

    @Test
    public void testNot() {
        assertEquals(SignedWord.from(-11), SignedWord.from(10).not());
    }

    @Test
    public void testXor() {
        assertEquals(SignedWord.from(9), SignedWord.from(10).xor(SignedWord.from(3)));
    }

    @Test
    public void testNegateNoOverflow() {
        assertEquals(OverflowableResult.of(SignedWord.NEGATIVE_ONE), SignedWord.ONE.negate());
    }

    @Test
    public void testNegateOverflow() {
        assertEquals(OverflowableResult.overflow(SignedWord.MIN), SignedWord.MIN.negate());
    }

    @Test
    public void testAddNoOverflow() {
        assertEquals(OverflowableResult.of(SignedWord.TWO), SignedWord.ONE.add(SignedWord.ONE));
    }

    @Test
    public void testAddOverflow() {
        assertEquals(OverflowableResult.overflow(SignedWord.MIN), SignedWord.MAX.add(SignedWord.ONE));
    }

    @Test
    public void testSubtractNoOverflow() {
        SignedWord a = SignedWord.ONE;
        SignedWord b = SignedWord.ONE;
        assertEquals(OverflowableResult.of(SignedWord.ZERO), a.subtract(b));
    }

    @Test
    public void testSubtractOverflow() {
        SignedWord a = SignedWord.MIN;
        SignedWord b = SignedWord.ONE;
        assertEquals(OverflowableResult.overflow(SignedWord.MAX), a.subtract(b));
    }

    @Test
    public void testMultiplyNoOverflow() {
        SignedWord a = SignedWord.TWO;
        SignedWord b = SignedWord.TWO;
        assertEquals(OverflowableResult.of(SignedWord.from(4)), a.multiply(b));
    }

    @Test
    public void testMultiplyOverflow() {
        SignedWord a = SignedWord.MAX;
        SignedWord b = SignedWord.MAX;
        assertEquals(OverflowableResult.overflow(SignedWord.ONE), a.multiply(b));
    }
}