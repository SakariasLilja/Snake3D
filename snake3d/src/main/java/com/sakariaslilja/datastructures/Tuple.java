package com.sakariaslilja.datastructures;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * DoubleVector3D tuple class for storing two vectors.
 * Tuples are equal if each element is equivalent 
 * to the corresponding one of the other.
 */
public class Tuple {

    public DoubleVector3D value1;
    public DoubleVector3D value2;

    /**
     * Tuple of two values.
     * @param value1 First value
     * @param value2 Second value
     */
    public Tuple(DoubleVector3D value1, DoubleVector3D value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Method that takes a predicate and checks both
     * variables of the this Tuple with it.
     * @param predicate Predicate to apply to this Tuple's values
     * @return The boolean result.
     */
    public boolean forAll(Predicate<DoubleVector3D> predicate) {
        return predicate.test(value1) && predicate.test(value2);
    }

    /**
     * Method that takes an anonymous function and changes the
     * values of this Tuple accordingly.
     * @param function Anonymous function to apply to this Tuple's values
     */
    public void forEach(Function<DoubleVector3D, DoubleVector3D> function) {
        value1 = function.apply(value1);
        value2 = function.apply(value2);
    }

    /**
     * Contains method. 
     * Checks if a DoubleVector3D is found in this Tuple.
     * @param vector3d The DoubleVector3D to check for
     * @return If either value equals the wanted DoubleVector3D
     */
    public boolean contains(DoubleVector3D vector3d) {
        return value1.equals(vector3d) || value2.equals(vector3d);
    }

    /**
     * Method for duplicating this object.
     * Both this object and its contents are duplicated.
     * @return A copy of this object
     */
    public Tuple duplicate() {
        DoubleVector3D newValue1 = this.value1.duplicate();
        DoubleVector3D newValue2 = this.value2.duplicate();
        return new Tuple(newValue1, newValue2);
    }

    /**
     * Method for checking if a predicate holds for any of the Tuple's values.
     * @param predicate The predicate to check
     * @return If the predicate held for any of the values
     */
    public boolean exists(Predicate<DoubleVector3D> predicate) {
        return predicate.test(value1) || predicate.test(value2);
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
