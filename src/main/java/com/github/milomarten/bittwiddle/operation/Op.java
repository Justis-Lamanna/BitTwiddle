package com.github.milomarten.bittwiddle.operation;

/**
 * Base class for bit twiddling operations
 * @param <IMPL>
 */
public interface Op<IMPL extends Op<IMPL>> {
    /**
     * Advance forward one byte
     * @return Operations object for further modification
     */
    default IMPL advance() {
        return advance(1);
    }

    /**
     * Advance n bytes forward
     * @param n The number of bytes to proceed
     * @return Operations object for further modification
     */
    IMPL advance(int n);

    /**
     * Retrieve the pointer at the cursor, and perform additional operations relative to that pointer
     * @return Operations object for further modification
     */
    IMPL follow();
}
