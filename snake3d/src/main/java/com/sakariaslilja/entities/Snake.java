package com.sakariaslilja.entities;

import com.sakariaslilja.datastructures.DoubleVector3D;
import com.sakariaslilja.datastructures.Heading;
import com.sakariaslilja.datastructures.Vector3D;
import com.sakariaslilja.models.SnakeModel;

/**
 * Snake class.
 */
public class Snake extends CubeEntity implements IMovable {

    private Vector3D heading, normal;
    private Turn nextTurn;

    /**
     * Creates a snake with all parameters.
     * @param position The world position of the snake
     * @param heading The heading direction of the snake
     * @param normal The normal heading of the snake
     * @param nextTurn The next turn the snake will take
     */
    public Snake(Vector3D position, Heading heading, Heading normal, Turn nextTurn) {
        super(position);
        this.heading = heading.vec;
        this.normal = normal.vec;
        this.nextTurn = nextTurn;
    }

    /**
     * Default constructor for a snake.
     * The next direction for turning will be the same as the heading.
     * @param position The world position of the snake
     * @param heading The heading direction of the snake
     * @param normal The next turn the snake will take
     */
    public Snake(Vector3D position, Heading heading, Heading normal) {
        this(position, heading, normal, Turn.N);
    }

    /**
     * Creates a snake with vector parameters.
     * @param position The world position of the snake
     * @param heading The vector heading of the snake
     * @param normal The vector normal of the snake
     * @param nextTurn The next turn the snake will take
     */
    public Snake(Vector3D position, Vector3D heading, Vector3D normal, Turn nextTurn) {
        super(position);
        this.heading = heading;
        this.normal = normal;
        this.nextTurn = nextTurn;
    }

    @Override
    public Vector3D getHeading() { return heading; }
    public Vector3D getNormal() { return normal; }

    /**
     * Moving the snake will update its position by adding its heading to it.
     */
    @Override
    public void move() {
        this.setPosition(this.getPosition().add(heading.mul(STEP_SIZE)));
    }

    /**
     * Turns the snake's head to the left
     */
    public void turnLeft() { heading = normal.crossProd(heading); }

    /**
     * Turns the snake's head to the right
     */
    public void turnRight() { heading = heading.crossProd(normal); }

    /**
     * Turns the snake's head downward
     */
    public void turnDown() {
        Vector3D newHeading = normal.neg();
        normal = heading;
        heading = newHeading;
    }

    /**
     * Turns the snake's head upward
     */
    public void turnUp() {
        Vector3D newNormal = heading.neg();
        heading = normal;
        normal = newNormal;
    }

    public Turn getTurn() { return nextTurn; }

    /**
     * @param turn Sets this turn as the segment's next turn
     */
    public void setTurn(Turn turn) { this.nextTurn = turn; }

    /**
     * Applies the next turn to this snake object
     */
    public void applyTurn() {
        switch (nextTurn) {
            case U:
                turnUp();
                break;
            case D:
                turnDown();
                break;
            case L:
                turnLeft();
                break;
            case R:
                turnRight();
                break;        
            default:
                break;
        }
    }

    /**
     * @return This snake converted to a model representation of its current state
     */
    public SnakeModel toSnakeModel() {
        SnakeModel model = new SnakeModel();
        model.x = this.getPosition().getX();
        model.y = this.getPosition().getY();
        model.z = this.getPosition().getZ();

        Heading heading_;
        if (heading.equals(Heading.BACKWARD.vec)) { heading_ = Heading.BACKWARD; }
        else if (heading.equals(Heading.LEFT.vec)) { heading_ = Heading.LEFT; }
        else if (heading.equals(Heading.RIGHT.vec)) { heading_ = Heading.RIGHT; }
        else if (heading.equals(Heading.UP.vec)) { heading_ = Heading.UP; }
        else if (heading.equals(Heading.DOWN.vec)) { heading_ = Heading.DOWN; }
        else { heading_ = Heading.FORWARD; }
        model.heading = heading_;

        Heading normal_;
        if (normal.equals(Heading.FORWARD.vec)) { normal_ = Heading.FORWARD; }
        else if (normal.equals(Heading.BACKWARD.vec)) { normal_ = Heading.BACKWARD; }
        else if (normal.equals(Heading.LEFT.vec)) { normal_ = Heading.LEFT; }
        else if (normal.equals(Heading.RIGHT.vec)) { normal_ = Heading.RIGHT; }
        else if (normal.equals(Heading.DOWN.vec)) { normal_ = Heading.DOWN; }
        else { normal_ = Heading.UP; }
        model.normal = normal_;

        model.nextTurn = this.nextTurn;

        return model;
    }

    @Override
    public Vector3D getGridPos() {
        Vector3D offset = new Vector3D(-1, -1, -1);
        if (heading.exists(i -> i < 0)) {
            offset = offset.add(heading.mul(UNIT - 1));
        }
        DoubleVector3D p1 = getPosition().add(offset).toDoubleVector3D();
        p1.mul(1.0 / UNIT);
        return p1.toVector3D();
    }

    @Override
    public String toString() {
        String p = "Position: " + this.getPosition().toString() + "\n";
        String h = "Heading: " + this.getHeading().toString() + "\n";
        String n = "Normal: " + this.getNormal().toString() + "\n";
        String gp = "GridPos: " + this.getGridPos().toString() + "\n";
        String t = "Next turn: " + this.nextTurn.name() + "\n";
        StringBuilder b = new StringBuilder();
        b.append(p);
        b.append(h);
        b.append(n);
        b.append(gp);
        b.append(t);
        return b.toString();
    }
    
}
