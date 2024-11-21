package com.sakariaslilja.models;

/**
 * Generic tuple class for storing two values.
 * Tuples are equal if each element is equivalent 
 * to the corresponding one of the other.
 */
public class Tuple {

    public int value1;
    public int value2;

    /**
     * Tuple of two values.
     * @param value1 First value
     * @param value2 Second value
     */
    public Tuple(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Tuple other = (Tuple) obj;

        if (other.value1 == this.value1 && other.value2 == this.value2) {
            return true;
        }

        return false;
    }
    
}
