package com.sakariaslilja.entities;

import com.sakariaslilja.datastructures.IHeading;
import com.sakariaslilja.datastructures.Vector3D;

/**
 * Defines the direction for turning a movable object.
 */
public enum Turn implements IHeading {
    L(LEFT),
    R(RIGHT),
    U(UP),
    D(DOWN),
    N(FORWARD);

    /**
     * The Vector3D heading of this turn
     */
    public final Vector3D direction;

    private Turn(Vector3D direction) {
        this.direction = direction;
    }
}
