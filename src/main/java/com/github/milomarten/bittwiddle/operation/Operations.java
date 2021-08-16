package com.github.milomarten.bittwiddle.operation;

import com.github.milomarten.bittwiddle.model.Bit;

public interface Operations {
    /* Terminal Operations */

    /**
     * Get the specified bit at the current byte.
     * @param index The index
     * @return The bit at the specified index
     */
    Bit bit(int index);
}
