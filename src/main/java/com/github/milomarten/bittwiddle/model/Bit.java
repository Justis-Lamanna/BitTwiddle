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
        public SignedByte toSignedByte() {
            return SignedByte.ZERO;
        }

        @Override
        public UnsignedByte toUnsignedByte() {
            return UnsignedByte.ZERO;
        }

        @Override
        public SignedShort toSignedShort() {
            return SignedShort.ZERO;
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

        @Override
        public OverflowableResult<Bit> add(Bit other) {
            return OverflowableResult.of(other);
        }

        @Override
        public OverflowableResult<Bit> subtract(Bit other) {
            return other == ZERO ? OverflowableResult.of(ZERO) : OverflowableResult.overflow(ONE);
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
        public SignedByte toSignedByte() {
            return SignedByte.ONE;
        }

        @Override
        public UnsignedByte toUnsignedByte() {
            return UnsignedByte.ONE;
        }

        @Override
        public SignedShort toSignedShort() {
            return SignedShort.ONE;
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

        @Override
        public OverflowableResult<Bit> add(Bit other) {
            return other == ONE ? OverflowableResult.overflow(Bit.ZERO) : OverflowableResult.of(Bit.ONE);
        }

        @Override
        public OverflowableResult<Bit> subtract(Bit other) {
            return OverflowableResult.of(other == ZERO ? ONE : ZERO);
        }
    };

    /**
     * Return true or false if this is ONE or ZERO
     * @return True if this == ONE, false otherwise.
     */
    public abstract boolean toBoolean();

    /**
     * Upcast this to a SignedByte
     * @return The upcast value
     */
    public abstract SignedByte toSignedByte();

    /**
     * Upcast this to an UnsignedByte
     * @return The upcast value
     */
    public abstract UnsignedByte toUnsignedByte();

    /**
     * Upcast this to a SignedShort
     * @return The upcast value
     */
    public abstract SignedShort toSignedShort();

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
     * Add two bits together
     * @param other The other bit
     * @return The result of this operation, potentially overflowing
     */
    public abstract OverflowableResult<Bit> add(Bit other);

    /**
     * Subtract two bits together
     * @param other The other bit
     * @return The result of this operation, potentially overflowing
     */
    public abstract OverflowableResult<Bit> subtract(Bit other);

    /**
     * Multiply two bits together
     * @param other The other bit
     * @return The result of this operation
     */
    public Bit multiply(Bit other) {
        return and(other);
    }

    /**
     * Get a Bit, given a boolean
     * @param bool The boolean to convert
     * @return Bit.ZERO if false is passed, Bit.ONE if true is passed
     */
    public static Bit from(boolean bool) {
        return bool ? ONE : ZERO;
    }
}
