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
}