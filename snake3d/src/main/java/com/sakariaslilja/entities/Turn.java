package com.sakariaslilja.entities;

import com.google.gson.annotations.SerializedName;
import com.sakariaslilja.datastructures.Heading;

/**
 * Defines the direction for turning a movable object.
 */
public enum Turn {
    @SerializedName("left")
    L(Heading.LEFT),
    @SerializedName("right")
    R(Heading.RIGHT),
    @SerializedName("up")
    U(Heading.UP),
    @SerializedName("down")
    D(Heading.DOWN),
    @SerializedName("null")
    N(Heading.FORWARD);

    /**
     * The Vector3D heading of this turn
     */
    public final Heading direction;

    private Turn(Heading direction) {
        this.direction = direction;
    }
}
