package com.sakariaslilja.entities;

import com.google.gson.annotations.SerializedName;
import com.sakariaslilja.datastructures.IHeading;
import com.sakariaslilja.datastructures.Vector3D;

/**
 * Defines the direction for turning a movable object.
 */
public enum Turn implements IHeading {
    @SerializedName("1")
    L(LEFT),
    @SerializedName("2")
    R(RIGHT),
    @SerializedName("3")
    U(UP),
    @SerializedName("4")
    D(DOWN),
    @SerializedName("0")
    N(FORWARD);

    /**
     * The Vector3D heading of this turn
     */
    public final Vector3D direction;

    private Turn(Vector3D direction) {
        this.direction = direction;
    }
}
