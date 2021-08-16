package com.github.milomarten.bittwiddle.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Encapsulates the result of some operation
 * @param <T> The resultant object
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class OverflowableResult<T> {
    private T object;
    private boolean overflow;

    /**
     * Create a result that had no overflow
     * @param object The result object
     * @param <T> The type of object
     * @return A created OverflowableResult, marked as not overflown
     */
    public static <T> OverflowableResult<T> of(T object) {
        return new OverflowableResult<>(object, false);
    }

    /**
     * Create a result that had an overflow
     * @param object The result object
     * @param <T> The type of object
     * @return A created OverflowableResult, marked as overflown
     */
    public static <T> OverflowableResult<T> overflow(T object) {
        return new OverflowableResult<>(object, true);
    }

    /**
     * Ignore overflow (rollover effect)
     * @return The result
     */
    public T ignore() {
        return object;
    }

    /**
     * Throw an exception if overflow occurred
     * @param exceptionSupplier A function which creates an exception
     * @param <X> The exception type
     * @return This object, if it did not overflow
     * @throws X Overflow occured
     */
    public <X extends Throwable> T getOrThrow(Supplier<X> exceptionSupplier) throws X {
        if(overflow) {
            throw exceptionSupplier.get();
        }
        return object;
    }

    /**
     * Clamp overflow at a specific value (saturation effect)
     * @param bound The bound to use
     * @return The result, or the bound if overflow happened
     */
    public T clamp(T bound) {
        return overflow ? bound : object;
    }

    /**
     * Lazily clamp overflow at a specific value (saturation effect)
     * @param bound
     * @return
     */
    public T lazyClamp(Supplier<T> bound) {
        return overflow ? bound.get() : object;
    }

    /**
     * Upcast the result (expand type)
     * @param objectCast Transformation function which turns a non-overflowed object into the upper type
     * @param overflowCast Transformation function which turns an overflowed object into the upper type
     * @param <U> The new type
     * @return The upcast
     */
    public <U> U upcast(Function<T, U> objectCast, Function<T, U> overflowCast) {
        return (overflow ? overflowCast : objectCast).apply(object);
    }
}
