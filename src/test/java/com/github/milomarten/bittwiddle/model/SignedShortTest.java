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
}