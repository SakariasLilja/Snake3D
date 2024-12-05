package com.sakariaslilja.models;

/**
 * Updatable wrapper class for integers.
 * Reference stays the same.
 */
public class Int {

    private int value_;

    /**
     * Creates an updatable integer representation.
     * @param value The integer value
     */
    public Int(int value) {
        this.value_ = value;
    }

    /**
     * @return The value of this Int
     */
    public int value() { return value_; }
    public void set(int value) { this.value_ = value; }
    /**
     * Negates the value of this Int
     */
    public void neg() { this.value_ *= -1; }

    @Override
    public String toString() {
        return Integer.toString(value_);
    }
    
}
