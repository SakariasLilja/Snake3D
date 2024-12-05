package com.sakariaslilja.models;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Rotatable tuple for storing numbers.
 * Works in place.
 */
public class RotatableTuple implements Iterable<Int> {

    private ArrayList<Int> values = new ArrayList<>();

    /**
     * Creates a new rotatable tuple of three values.
     * <p> Rotatable meaning that 3x3 matrix transforms can be applied to them.
     * <p> Works in place.
     * @param x x-value
     * @param y y-value
     * @param z z-value
     */
    public RotatableTuple(Int x, Int y, Int z) {
        values.add(x);
        values.add(y);
        values.add(z);
    }

    public Int getX() { return values.get(0); }
    public Int getY() { return values.get(1); }
    public Int getZ() { return values.get(2); }

    public void setX(int value) { values.get(0).set(value); }
    public void setY(int value) { values.get(1).set(value); }
    public void setZ(int value) { values.get(2).set(value); }

    /**
     * Applies a 90-degree x-rotation matrix to this tuple.
     * Works in place.
     * @param isPositive If the rotation is positive
     * @return The updated object
     */
    public RotatableTuple rotateX(boolean isPositive) {
        int amount = isPositive ? 1 : -1;

        values.get(1).mul(amount);
        values.get(2).mul(-amount);
        Int temp = values.get(1);
        values.set(1, values.get(2));
        values.set(2, temp);

        return this;
    }

    /**
     * Applies a 90-degree y-rotation matrix to this tuple.
     * Works in place.
     * @param isPositive If the rotation is positive
     * @return The updated object
     */
    public RotatableTuple rotateY(boolean isPositive) {
        int amount = isPositive ? 1 : -1;

        values.get(2).mul(amount);
        values.get(0).mul(-amount);
        Int temp = values.get(0);
        values.set(0, values.get(2));
        values.set(2, temp);

        return this;
    }

    /**
     * Applies a 90-degree z-rotation matrix to this tuple.
     * Works in place.
     * @param isPositive If the rotation is positive
     * @return The updated object
     */
    public RotatableTuple rotateZ(boolean isPositive) {
        int amount = isPositive ? 1 : -1;

        values.get(1).mul(-amount);
        values.get(0).mul(amount);
        Int temp = values.get(0);
        values.set(0, values.get(1));
        values.set(1, temp);

        return this;
    }

    // Inherited class for iterating over the elements of this tuple
    @Override
    public Iterator<Int> iterator() {
        return values.iterator();
    }

    @Override
    public String toString() {
        return "RT(" + values.get(0).value() + ", " + values.get(1).value() + ", " + values.get(2).value() + ")";
    }
    
}
