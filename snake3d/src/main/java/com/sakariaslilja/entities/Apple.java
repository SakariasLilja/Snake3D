package com.sakariaslilja.entities;

import com.sakariaslilja.datastructures.Vector3D;

/**
 * Apple entity.
 * The simplest form of cube entity with no special attributes.
 * An apple is slightly smaller than a full cube.
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

    @Override
    protected double size() {
        return 0.55;
    }
    
}
