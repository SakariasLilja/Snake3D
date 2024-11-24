package com.sakariaslilja.models;

/**
 * Abstract class for Vectors.
 * All vectors have only a scalar multiplication method
 */
public abstract class AbstractVector {

    /**
     * Scalar multiplication of a vector
     * @param scalar The scalar with which to multiply this vector with
     * @return A new AbstractVector instance with the new values
     */
    public abstract AbstractVector mul(double scalar);
    
}
