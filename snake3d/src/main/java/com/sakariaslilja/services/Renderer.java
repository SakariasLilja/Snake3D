package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

import com.sakariaslilja.Constants;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.Tuple;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Renderer for Snake 3D.
 */
public class Renderer {

    // Local variables
    private GraphicsContext g;
    private ArrayList<Tuple> edges;
    private GameEngine engine;

    private final Color backgroundColor = Color.web("#4a4e69");
    private final Color strokeColor = Color.web("#6495ED");
    //private final Color snakeColor = Color.web("#F5F5F5");

    /**
     * Renderer instance.
     * Sets the world offset vector.
     * @param g The graphics context onto which to draw
     * @param edges The edges of the world
     * @param camera The camera of the world
     * @param normalVector The normal vector of the camera
     */
    @SuppressWarnings("exports")
    public Renderer(GraphicsContext g, ArrayList<Tuple> edges, GameEngine engine) {
        this.g = g;
        this.edges = edges;
        this.engine = engine;
    }

    /**
     * Render function that calls all other functions of Renderer in order.
     */
    public void render() {
        this.clearCanvas(g);
        this.drawEdges(g);
    }

    /**
     * Clears the canvas by filling it completely with background color
     * @param g The graphics context onto which to render
     */
    private void clearCanvas(GraphicsContext g) {
        g.setFill(backgroundColor);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
    }

    /**
     * Draws the edges onto the canvas
     * @param g The canvas onto which to draw
     */
    private void drawEdges(GraphicsContext g) {
        g.setStroke(strokeColor);
        for (Tuple edge : edges) {
            Tuple copy = edge.duplicate();
            copy.forEach(applyMatricesFunc);
            if (copy.forAll(hasPositiveZ)){
                DoubleVector3D point1 = copy.value1;
                DoubleVector3D point2 = copy.value2;

                g.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            }            
        }
    }

    /**
     * Applies the translation matrix and world offset correction to the vertex.
     * @param vertex Vertex to translate
     * @return The translated vertex
     */
    private DoubleVector3D translationMatrix(DoubleVector3D vertex) {
        return vertex.add(engine.getCamera().neg());
    }

    /**
     * Applies the rotation matrices to the vertex in the order:
     * <p> rotZ, rotY, rotX
     * @param vertex The vertex to rotate
     * @return The rotated vertex
     */
    private DoubleVector3D applyRotations(DoubleVector3D vertex) {
        return vertex.rotateZ(engine.getRotZ()).rotateY(engine.getRotY()).rotateX(engine.getRotX());
    }

    /**
     * Applies the camera transform matrix
     * @param vertex The vertex to transform
     * @return The transformed vertex
     */
    private DoubleVector3D cameraTransform(DoubleVector3D vertex) {
        double newX = vertex.getX() * Constants.FOCAL_LENGTH * Constants.WIDTH / (2 * Constants.SENSOR_SIZE_X);
        double newY = vertex.getY() * Constants.FOCAL_LENGTH * Constants.HEIGHT / (2 * Constants.SENSOR_SIZE_Y);

        return new DoubleVector3D(newX, newY, vertex.getZ());
    }

    /**
     * Applies the perpective correction matrix
     * @param vertex The vertex to correct
     * @return The corrected vertex
     */
    private DoubleVector3D perpectiveCorrection(DoubleVector3D vertex) {
        return new DoubleVector3D(vertex.getX() / vertex.getZ(), vertex.getY() / vertex.getZ(), vertex.getZ());
    }

    /**
     * Moves the vertex to the center of the screen
     * @param vertex Vertex to translate
     * @return The translated vertex
     */
    private DoubleVector3D centralizeVertex(DoubleVector3D vertex) {
        return new DoubleVector3D(vertex.getX() + 0.5 * Constants.WIDTH, vertex.getY() + 0.5 * Constants.HEIGHT, vertex.getZ());
    }

    /**
     * Applies every rendering matrix in order.
     * @param vertex The vertex to render
     * @return The rendered vertex
     */
    private DoubleVector3D applyMatrices(DoubleVector3D vertex) {
        return centralizeVertex(perpectiveCorrection(cameraTransform(applyRotations(translationMatrix(vertex)))));
    }

    /**
     * Anonymous function that applies {@code applyMatrices} to a DoubleVector3D
     */
    private Function<DoubleVector3D, DoubleVector3D> applyMatricesFunc = (v) -> applyMatrices(v);

    /**
     * Predicate that checks if the z-coordinate of a DoubleVector3D is positive
     */
    private Predicate<DoubleVector3D> hasPositiveZ = (v) -> v.getZ() > 0.000001;
    
}
