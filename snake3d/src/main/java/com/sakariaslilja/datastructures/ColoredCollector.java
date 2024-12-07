package com.sakariaslilja.datastructures;

import javafx.scene.paint.Color;

/**
 * Container class for containing an object and a color
 */
public class ColoredCollector<T> {

    private Color color_;
    private T object_;

    /**
     * Initializes a contianer for an object and its associated color
     * @param color
     * @param object
     */
    public ColoredCollector(T object, Color color) {
        this.color_ = color;
        this.object_ = object;
    }

    /**
     * @return The stored object
     */
    public T obj() {
        return object_;
    }

    /**
     * @return The color of this object
     */
    public Color color() {
        return color_;
    }
    
}
