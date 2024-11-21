package com.sakariaslilja.models;

import java.util.function.Function;

/**
 * Vector3D tuple class for storing two vectors.
 * Tuples are equal if each element is equivalent 
 * to the corresponding one of the other.
 */
public class Tuple {

    public Vector3D value1;
    public Vector3D value2;

    /**
     * Tuple of two values.
     * @param value1 First value
     * @param value2 Second value
     */
    public Tuple(Vector3D value1, Vector3D value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Method that takes an anonymous function and checks both
     * variables of the this Tuple with it.
     * @param function Anonymous function to apply to this Tuple's values
     * @return The boolean result.
     */
    public boolean forAll(Function<Vector3D, Boolean> function) {
        return function.apply(value1) && function.apply(value2);
    }

    /**
     * Method that takes an anonymous function and changes the
     * values of this Tuple accordingly.
     * @param function Anonymous function to apply to this Tuple's values
     */
    public void forEach(Function<Vector3D, Vector3D> function) {
        value1 = function.apply(value1);
        value2 = function.apply(value2);
    }

    /**
     * Contains method. 
     * Checks if a Vector3D is found in this Tuple.
     * @param vector3d The Vector3D to check for
     * @return If either value equals the wanted Vector3D
     */
    public boolean contains(Vector3D vector3d) {
        return value1.equals(vector3d) || value2.equals(vector3d);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Tuple other = (Tuple) obj;

        if (other.value1.equals(this.value1) && other.value2.equals(this.value2)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "[" + value1.toString() + " <-> " + value2.toString() + "]";
    }
    
}
