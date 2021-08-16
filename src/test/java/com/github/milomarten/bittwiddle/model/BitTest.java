package com.github.milomarten.bittwiddle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.milomarten.bittwiddle.model.Bit.*;

class BitTest {
    @Test
    public void testAndTable() {
        assertEquals(ZERO, ZERO.and(ZERO));
        assertEquals(ZERO, ZERO.and(ONE));
        assertEquals(ZERO, ONE.and(ZERO));
        assertEquals(ONE, ONE.and(ONE));
    }

    @Test
    public void testOrTable() {
        assertEquals(ZERO, ZERO.or(ZERO));
        assertEquals(ONE, ZERO.or(ONE));
        assertEquals(ONE, ONE.or(ZERO));
        assertEquals(ONE, ONE.or(ONE));
    }

    @Test
    public void testNotTable() {
        assertEquals(ONE, ZERO.not());
        assertEquals(ZERO, ONE.not());
    }

    @Test
    public void testXorTable() {
        assertEquals(ZERO, ZERO.xor(ZERO));
        assertEquals(ONE, ZERO.xor(ONE));
        assertEquals(ONE, ONE.xor(ZERO));
        assertEquals(ZERO, ONE.xor(ONE));
    }

    @Test
    public void testToBoolean() {
        assertFalse(ZERO.toBoolean());
        assertTrue(ONE.toBoolean());
    }

    @Test
    public void testToSignedByte() {
        assertEquals(SignedByte.ZERO, ZERO.toSignedByte());
        assertEquals(SignedByte.ONE, ONE.toSignedByte());
    }

    @Test
    public void testAdd() {
        assertEquals(OverflowableResult.of(ZERO), ZERO.add(ZERO));
        assertEquals(OverflowableResult.of(ONE), ZERO.add(ONE));
        assertEquals(OverflowableResult.of(ONE), ONE.add(ZERO));
        assertEquals(OverflowableResult.overflow(ZERO), ONE.add(ONE));
    }

    @Test
    public void testSubtract() {
        assertEquals(OverflowableResult.of(ZERO), ZERO.subtract(ZERO));
        assertEquals(OverflowableResult.overflow(ONE), ZERO.subtract(ONE));
        assertEquals(OverflowableResult.of(ONE), ONE.subtract(ZERO));
        assertEquals(OverflowableResult.of(ZERO), ONE.subtract(ONE));
    }

    @Test
    public void testMultiply() {
        assertEquals(ZERO, ZERO.multiply(ZERO));
        assertEquals(ZERO, ZERO.multiply(ONE));
        assertEquals(ZERO, ONE.multiply(ZERO));
        assertEquals(ONE, ONE.multiply(ONE));
    }

    @Test
    public void testFromBoolean() {
        assertEquals(ZERO, Bit.from(false));
        assertEquals(ONE, Bit.from(true));
    }
}