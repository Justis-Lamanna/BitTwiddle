package com.github.milomarten.bittwiddle.model;

/**
 * The basic unit, the bit. This is, of course, independent of endianness.
 */
public enum Bit {
    /**
     * The zero bit
     */
    ZERO {
        @Override
        public boolean toBoolean() {
            return false;
        }

        @Override
        public Bit and(Bit other) {
            return ZERO;
        }

        @Override
        public Bit or(Bit other) {
            return other == ONE ? ONE : ZERO;
        }

        @Override
        public Bit not() {
            return ONE;
        }

        @Override
        public Bit xor(Bit other) {
            return or(other);
        }
    },
    /**
     * The one bit
     */
    ONE {
        @Override
        public boolean toBoolean() {
            return true;
        }

        @Override
        public Bit and(Bit other) {
            return other == ONE ? ONE : ZERO;
        }

        @Override
        public Bit or(Bit other) {
            return ONE;
        }

        @Override
        public Bit not() {
            return ZERO;
        }

        @Override
        public Bit xor(Bit other) {
            return other == ZERO ? ONE : ZERO;
        }
    };

    /**
     * Return true or false if this is ONE or ZERO
     * @return True if this == ONE, false otherwise.
     */
    public abstract boolean toBoolean();

    /**
     * Perform a logical AND on this bit and another
     * @param other The other bit
     * @return The result of this operation
     */
    public abstract Bit and(Bit other);

    /**
     * Perform a logical OR on this bit and another
     * @param other The other bit
     * @return The result of this operation
     */
    public abstract Bit or(Bit other);

    /**
     * Perform a logical inversion on this bit and another
     * @return The opposite bit
     */
    public abstract Bit not();

    /**
     * Perform a logical exclusive-or
     * @param other The other bit
     * @return The result of this operation
     */
    public abstract Bit xor(Bit other);

    /**
     * Get a Bit, given a boolean
     * @param bool The boolean to convert
     * @return Bit.ZERO if false is passed, Bit.ONE if true is passed
     */
    public static Bit fromBoolean(boolean bool) {
        return bool ? ONE : ZERO;
    }
}
