package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.sakariaslilja.IConstants;
import com.sakariaslilja.entities.Apple;
import com.sakariaslilja.entities.Entity;
import com.sakariaslilja.entities.Snake;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.GameModel;
import com.sakariaslilja.models.IHeading;
import com.sakariaslilja.models.Int;
import com.sakariaslilja.models.Quaternion;
import com.sakariaslilja.models.RotatableTuple;
import com.sakariaslilja.models.Tuple;
import com.sakariaslilja.models.Vector3D;
import com.sakariaslilja.models.World;

import javafx.scene.input.KeyCode;

/**
 * The game engine.
 */
public class GameEngine implements IConstants, IHeading {

    private String gameTitle;
    private long seed;
    private Random random;
    private Date creationDate;
    private int score;

    private int worldWidth, worldHeight, worldDepth;
    private int appleLimit;

    private ArrayList<Tuple> edges;
    private ArrayList<Vector3D> gridPositions = new ArrayList<>();
    private ArrayList<Apple> apples = new ArrayList<>();
    private ArrayList<Snake> snake = new ArrayList<>();

    private DoubleVector3D heading;
    private DoubleVector3D normal;

    // In degrees
    private int rotationX;
    private int rotationY;
    private Int rotX;
    private Int rotY;
    private Int rotZ;
    private RotatableTuple rotations;

    private boolean paused = false;
    private boolean isTurning = false;
    private boolean isTilting = false;
    private boolean typicalRotation = true;

    /**
     * To create an instance of a game engine, the world dimensions are needed
     * @param seed The random seed of the game
     * @param worldWidth Width of the world
     * @param worldHeight Height of the world
     * @param worldDepth Depth of the world
     */
    public GameEngine(GameModel game) {
        this.gameTitle = game.gameTitle;
        this.seed = game.seed;
        this.random = new Random(seed);
        this.creationDate = game.creationDate;
        this.score = game.score;
        this.worldWidth = game.worldWidth;
        this.worldHeight = game.worldHeight;
        this.worldDepth = game.worldDepth;
        this.appleLimit = Integer.max(1, (int) Math.cbrt(worldWidth*worldHeight*worldDepth) - 2);
        World world = new World(worldWidth, worldHeight, worldDepth);
        this.edges = world.getEdges();
        this.rotX = new Int(game.rotX);
        this.rotY = new Int(game.rotY);
        this.rotZ = new Int(game.rotZ);
        this.rotations = new RotatableTuple(this.rotX, this.rotY, this.rotZ);

        // Adding the grid positions of the world
        for (int layer = 0; layer < game.worldDepth; layer++) {
            for (int row = 0; row < game.worldHeight; row++) {
                for (int column = 0; column < game.worldWidth; column++) {
                    gridPositions.add(new Vector3D(column, row, layer));
                }
            }
        }

        snake.add(new Snake(new Vector3D(500, 500, 500), FORWARD, UP));
        this.heading = head().getHeading().toDoubleVector3D();
        this.normal = head().getNormal().toDoubleVector3D();
    }

    // Engine getters and setters

    public ArrayList<Tuple> getEdges() { return edges; }
    protected int gridPositionCount() { return gridPositions.size(); }

    public ArrayList<Apple> getApples() { return apples; }
    protected int countApples() { return apples.size(); }
    public void setApples(ArrayList<Apple> apples) { this.apples = apples; }

    public int getScore() { return score; }
    

    /**
     * Performs the action associated with each key.
     * @param keyCode The key code of the pressed key
     */
    public void doButtonAction(@SuppressWarnings("exports") KeyCode keyCode) {
        if (keyCode.equals(KeyCode.LEFT)) { isTurning = true; typicalRotation = true; }
        if (keyCode.equals(KeyCode.RIGHT)) { isTurning = true; typicalRotation = false; }
        if (keyCode.equals(KeyCode.UP)) { isTilting = true; typicalRotation = true; }
        if (keyCode.equals(KeyCode.DOWN)) { isTilting = true; typicalRotation = false; }

    }

    /**
     * @return The x-rotation in radians
     */
    public double getRotX() { return Math.PI * rotationX / 180.0; }

    /**
     * @return The y-rotation in radians
     */
    public double getRotY() { return Math.PI * rotationY / 180.0; }

    /**
     * @return The z-rotation in radians
     */
    public double getRotZ() { return Math.PI * rotZ.value() / 180.0; }

    /**
     * @return The directional heading of the camera
     */
    public DoubleVector3D getHeading() { return this.heading; }

    /**
     * @return The directional normal of the camera
     */
    public DoubleVector3D getNormal() { return this.normal; }

    /**
     * @return The camera's location in the world.
     */
    public DoubleVector3D camera() { return head().getPosition().toDoubleVector3D().mul(1.0 / UNIT); }

    /**
     * @return The head of the game's snake entity.
     */
    private Snake head() { return snake.get(0); }

    /**
     * Triggers the paused variable of the game.
     * <p> I.e. if the game is paused, this method resumes
     * the game, and if the game is running, this method
     * pauses the game.
     */
    public void togglePause() { paused = !paused; }

    /**
     * Increases the score by 1.
     */
    public void incrementScore() { score++; }

    /**
     * Increases the score by a given amount.
     * @param amount The amount to increase the score by
     */
    public void increaseScore(int amount) { score += amount; }

    /**
     * Converts this GameEngine instance to a GameModel.
     * Used for saving the game.
     * @return A GameModel instance with the attributes of this game
     */
    public GameModel toGameModel() {
        GameModel model = new GameModel();

        model.gameTitle = gameTitle;
        model.creationDate = creationDate;
        model.score = score;
        model.seed = seed;
        model.worldWidth = worldWidth;
        model.worldHeight = worldHeight;
        model.worldDepth = worldDepth;
        model.rotX = rotX.value();
        model.rotY = rotY.value();
        model.rotZ = rotZ.value();

        return model;
    }

    /**
     * Update method of the world.
     */
    public void update() {
        if (paused) {
            return;
        }

        int v = typicalRotation ? 1 : -1;
        // TODO: fix
        if (this.isTurning) {
            this.rotationY += v;
            Quaternion qY = new Quaternion(heading, ONE_DEG * v);
            this.heading = qY.applyRotation(heading);

            if (rotationY % 90 == 0) {
                if (typicalRotation) { head().turnLeft(); }
                else { head().turnRight(); }

                this.heading = head().getHeading().toDoubleVector3D();

                rotationY = 0;
                isTurning = false;
            }
            
        }
        else if (this.isTilting) {
            this.rotationX += v;
            Quaternion qX = new Quaternion(heading.crossProd(normal), ONE_DEG * v);
            this.heading = qX.applyRotation(heading);
            this.normal = qX.applyRotation(normal);

            if (rotationX % 90 == 0) {
                if (typicalRotation) { head().turnDown(); }
                else { head().turnUp(); }

                this.heading = head().getHeading().toDoubleVector3D();
                this.normal = head().getNormal().toDoubleVector3D();

                this.rotationX = 0;
                isTilting = false;
            }

        }
        else {
            this.spawnApple(appleLimit);
        }
    }

    /**
     * Gets the non-occupied grid positions.
     * Non-occupied entails that no entity is present at the location.
     * @param obstructors Vararg of collections of entities
     * @return An ArrayList of free spaces
     */
    @SuppressWarnings("unchecked")
    protected <T extends Entity> ArrayList<Vector3D> getAvailableGridPositions(ArrayList<T>... obstructors) {
        ArrayList<Vector3D> available = new ArrayList<>();
        available.addAll(this.gridPositions);

        for (ArrayList<T> obstructor : obstructors) {
            for (T entity : obstructor) {
                available.removeIf( (pos) -> pos.equals(entity.getGridPos()) );
            }
        }

        return available;
    }

    /**
     * Spawns an apple at a random location in the world if the number of
     * apples is less than a given limit.
     * <p> 
     * An apple can only spawn if there are unoccupied locations in the world.
     * @param limit The max number of apples a world can have
     */
    @SuppressWarnings("unchecked")
    protected void spawnApple(int limit) {
        if (this.countApples() < limit) {
            ArrayList<Vector3D> availableGridPositions = getAvailableGridPositions(apples);

            if (availableGridPositions.size() == 0) {
                return;
            }

            int locationIndex = random.nextInt(availableGridPositions.size());
            Vector3D offset = new Vector3D(500, 500, 500);
            Apple apple = new Apple(availableGridPositions.get(locationIndex).mul(UNIT).add(offset));
            apples.add(apple);
        }
    }
    
}
