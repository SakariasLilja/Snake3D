package com.sakariaslilja.datastructures;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for the headings used for this game
 */
public enum Heading {

    @SerializedName("forward")
    FORWARD(new Vector3D(0, 0, 1)),
    @SerializedName("backward")
    BACKWARD(new Vector3D(0, 0, -1)),
    @SerializedName("left")
    LEFT(new Vector3D(-1, 0, 0)),
    @SerializedName("right")
    RIGHT(new Vector3D(1, 0, 0)),
    @SerializedName("up")
    UP(new Vector3D(0, -1, 0)),
    @SerializedName("down")
    DOWN(new Vector3D(0, 1, 0));

    /**
     * The Vector3D representation of this direction
     */
    public final Vector3D vec;

    private Heading(Vector3D vec) {
        this.vec = vec;
    }
    
}
