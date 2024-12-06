package com.sakariaslilja.entities;

import com.sakariaslilja.datastructures.Vector3D;

/**
 * Apple entity.
 * The simplest form of cube entity with no special attributes.
 */
public class Apple extends CubeEntity {

    /**
     * An apple is a simple, non-moving entity.
     * If a snake eats this entity, the score increases.
     * @param position The world-position of the apple
     */
    public Apple(Vector3D position) {
        super(position);

    }
    
}
