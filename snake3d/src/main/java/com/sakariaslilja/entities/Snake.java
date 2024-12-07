package com.sakariaslilja.entities;

import com.sakariaslilja.datastructures.Vector3D;

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
     * @param normal The normal vector of the snake
     * @param nextTurn The next turn the snake will take
     */
    public Snake(Vector3D position, Vector3D heading, Vector3D normal, Turn nextTurn) {
        super(position);
        this.heading = heading;
        this.normal = normal;
        this.nextTurn = nextTurn;
    }

    /**
     * Default constructor for a snake.
     * The next direction for turning will be the same as the heading.
     * @param position The world position of the snake
     * @param heading The heading direction of the snake
     * @param normal The next turn the snake will take
     */
    public Snake(Vector3D position, Vector3D heading, Vector3D normal) {
        this(position, heading, normal, Turn.N);
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
