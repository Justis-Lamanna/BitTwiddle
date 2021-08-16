package com.github.milomarten.bittwiddle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OverflowableResultTest {
    @Test
    public void testIgnoreNoOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.of(1);
        assertEquals(1, result.ignore());
    }

    @Test
    public void testIgnoreOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.overflow(1);
        assertEquals(1, result.ignore());
    }

    @Test
    public void testExactNoOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.of(1);
        assertEquals(1, result.exact(RuntimeException::new));
    }

    @Test
    public void testExactOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.overflow(1);
        assertThrows(RuntimeException.class, () -> {
            result.exact(RuntimeException::new);
        });
    }

    @Test
    public void testExactNoOverflowNoExceptionSpecified() {
        OverflowableResult<Integer> result = OverflowableResult.of(1);
        assertEquals(1, result.exact());
    }

    @Test
    public void testExactOverflowNoExceptionSpecified() {
        OverflowableResult<Integer> result = OverflowableResult.overflow(1);
        assertThrows(ArithmeticException.class, result::exact);
    }

    @Test
    public void testClampNoOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.of(1);
        assertEquals(1, result.clamp(100));
    }

    @Test
    public void testClampOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.overflow(1);
        assertEquals(100, result.clamp(100));
    }

    @Test
    public void testLazyClampNoOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.of(1);
        assertEquals(1, result.lazyClamp(() -> 100));
    }

    @Test
    public void testLazyClampOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.overflow(1);
        assertEquals(100, result.lazyClamp(() -> 100));
    }

    @Test
    public void testUpcastNoOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.of(1);
        assertEquals(1L, result.<Long>upcast(Long::valueOf, l -> Long.MAX_VALUE));
    }

    @Test
    public void testUpcastOverflow() {
        OverflowableResult<Integer> result = OverflowableResult.overflow(1);
        assertEquals(Long.MAX_VALUE, result.<Long>upcast(Long::valueOf, l -> Long.MAX_VALUE));
    }
}