package com.sakariaslilja.entities;

import com.google.gson.annotations.SerializedName;
import com.sakariaslilja.datastructures.IHeading;
import com.sakariaslilja.datastructures.Vector3D;

/**
 * Defines the direction for turning a movable object.
 */
public enum Turn implements IHeading {
    @SerializedName("left")
    L(LEFT),
    @SerializedName("right")
    R(RIGHT),
    @SerializedName("up")
    U(UP),
    @SerializedName("down")
    D(DOWN),
    @SerializedName("null")
    N(FORWARD);

    /**
     * The Vector3D heading of this turn
     */
    public final Vector3D direction;

    private Turn(Vector3D direction) {
        this.direction = direction;
    }
}
