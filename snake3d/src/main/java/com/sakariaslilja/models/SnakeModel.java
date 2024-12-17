package com.sakariaslilja.models;

import com.google.gson.annotations.SerializedName;
import com.sakariaslilja.datastructures.Heading;
import com.sakariaslilja.datastructures.Vector3D;
import com.sakariaslilja.entities.Snake;
import com.sakariaslilja.entities.Turn;

/**
 * Model used for constructing snakes
 */
public class SnakeModel {

    public int x = 500;
    public int y = 500;
    public int z = 500;

    @SerializedName("heading")
    public Heading heading = Heading.FORWARD;

    @SerializedName("normal")
    public Heading normal = Heading.UP;

    @SerializedName("nextTurn")
    public Turn nextTurn = Turn.N;

    /**
     * @return Creates a snake with the values given to this model
     */
    public Snake createSnake() {
        Vector3D snakePos = new Vector3D(x, y, z);
        return new Snake(snakePos, heading, normal, nextTurn);
    }
    
}
