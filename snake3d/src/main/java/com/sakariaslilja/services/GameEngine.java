package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.sakariaslilja.IConstants;
import com.sakariaslilja.entities.Apple;
import com.sakariaslilja.entities.Snake;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.GameModel;
import com.sakariaslilja.models.IHeading;
import com.sakariaslilja.models.Quaternion;
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

    private boolean paused = false;
    private boolean turningLeft = false;
    private boolean turningRight = false;
    private boolean turningUp = false;
    private boolean turningDown = false;

    private Quaternion q = new Quaternion(1, 0, 0, 0);
    private int rCountHelper = 0;

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

        // Adding the grid positions of the world
        for (int layer = 0; layer < game.worldDepth; layer++) {
            for (int row = 0; row < game.worldHeight; row++) {
                for (int column = 0; column < game.worldWidth; column++) {
                    gridPositions.add(new Vector3D(column, row, layer));
                }
            }
        }

        snake.add(new Snake(new Vector3D(500, 500, 500), FORWARD, UP));
    }

    // Engine getters and setters

    public ArrayList<Tuple> getEdges() { return edges; }
    protected int gridPositionCount() { return gridPositions.size(); }

    public ArrayList<Apple> getApples() { return apples; }
    protected int countApples() { return apples.size(); }
    public void setApples(ArrayList<Apple> apples) { this.apples = apples; }

    public int getScore() { return score; }

    private boolean isTurning() { return turningLeft || turningRight || turningDown || turningUp; }  

    /**
     * Performs the action associated with each key.
     * @param keyCode The key code of the pressed key
     */
    public void doButtonAction(@SuppressWarnings("exports") KeyCode keyCode) {
        if (isTurning()) { return; } // Stops multiple inputs

        if (keyCode.equals(KeyCode.LEFT)) { turningLeft = true; }
        if (keyCode.equals(KeyCode.RIGHT)) { turningRight = true; }
        if (keyCode.equals(KeyCode.UP)) { turningDown = true; }
        if (keyCode.equals(KeyCode.DOWN)) { turningUp = true; }

    }

    /**
     * @return The camera's location in the world.
     */
    public DoubleVector3D camera() { return head().getPosition().toDoubleVector3D().mul(1.0 / UNIT); }
    public Quaternion quaternion() { return q; }

    /**
     * @return The head of the game's snake entity.
     */
    private Snake head() { return snake.get(0); }
    private DoubleVector3D normal() { return head().getNormal().toDoubleVector3D(); }
    private DoubleVector3D snakeXAxis() { return head().getHeading().crossProd(head().getNormal()).toDoubleVector3D(); }

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

        return model;
    }

    /**
     * Update method of the world.
     */
    public void update() {
        if (paused) {
            return;
        }

        if (this.isTurning()) {
            Quaternion rotation;

            if (turningLeft) { rotation = new Quaternion(normal(), -ONE_DEG); }
            else if (turningRight) { rotation = new Quaternion(normal(), ONE_DEG); }
            else if (turningDown) { rotation = new Quaternion(snakeXAxis(), ONE_DEG); }
            else { rotation = new Quaternion(snakeXAxis(), -ONE_DEG); }

            q = q.mul(rotation);
            rCountHelper++;
            if (rCountHelper == 90) { 
                rCountHelper = 0;
                if (turningLeft) { turningLeft = false; head().turnLeft(); }
                else if (turningRight) { turningRight = false; head().turnRight(); }
                else if (turningDown) { turningDown = false; head().turnDown(); }
                else { turningUp = false; head().turnUp(); }
            }
        }
        else {
            spawnApple(appleLimit);
        }
    }

    /**
     * Gets the non-occupied grid positions.
     * Non-occupied entails that no entity is present at the location.
     * @param positions A collection of the occupied positions
     * @return An ArrayList of free spaces
     */
    protected ArrayList<Vector3D> getAvailableGridPositions(ArrayList<Vector3D> positions) {
        ArrayList<Vector3D> available = new ArrayList<>();
        available.addAll(this.gridPositions);

        for (Vector3D position : positions) {
            available.removeIf( (pos) -> pos.equals(position) );
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
    protected void spawnApple(int limit) {
        if (this.countApples() < limit) {
            ArrayList<Vector3D> occupied = new ArrayList<>();
            for (Apple apple : apples) { occupied.add(apple.getGridPos()); }
            for (Snake segment : snake) { occupied.add(segment.getGridPos()); }
            ArrayList<Vector3D> availableGridPositions = getAvailableGridPositions(occupied);

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
