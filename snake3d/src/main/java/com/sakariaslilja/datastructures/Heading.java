package com.sakariaslilja.datastructures;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for the headings used for this game
 */
public enum Heading {

    @SerializedName("0")
    FORWARD(new Vector3D(0, 0, 1)),
    @SerializedName("1")
    BACKWARD(new Vector3D(0, 0, -1)),
    @SerializedName("2")
    LEFT(new Vector3D(-1, 0, 0)),
    @SerializedName("3")
    RIGHT(new Vector3D(1, 0, 0)),
    @SerializedName("4")
    UP(new Vector3D(0, -1, 0)),
    @SerializedName("5")
    DOWN(new Vector3D(0, 1, 0));

    /**
     * The Vector3D representation of this direction
     */
    public final Vector3D direction;

    private Heading(Vector3D direction) {
        this.direction = direction;
    }
    
}
