package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

import com.sakariaslilja.App;
import com.sakariaslilja.Constants;
import com.sakariaslilja.entities.Apple;
import com.sakariaslilja.entities.CubeEntity;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.SettingsModel;
import com.sakariaslilja.models.Tuple;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 3D renderer for Snake 3D.
 */
public class Renderer implements Constants {

    // Local variables
    private GraphicsContext g;
    private ArrayList<Tuple> edges;
    private GameEngine engine;

    private Color backgroundColor;
    private Color strokeColor;
    private Color snakeColor;
    private Color appleColor;

    /**
     * Renderer instance.
     * Sets the world offset vector.
     * @param g The graphics context onto which to draw
     * @param edges The edges of the world
     * @param camera The camera of the world
     * @param normalVector The normal vector of the camera
     */
    @SuppressWarnings("exports")
    public Renderer(GraphicsContext g, GameEngine engine) {
        this.g = g;
        this.edges = engine.getEdges();
        this.engine = engine;

        // Initialize colors
        SettingsService settingsService = new SettingsService();
        SettingsModel settings = settingsService.getSettings();
        backgroundColor = Color.web(settings.backgroundColor);
        strokeColor = Color.web(settings.edgeColor);
        snakeColor = Color.web(settings.snakeColor);
        appleColor = Color.web(settings.appleColor);
    }

    /**
     * Render function that calls all other functions of Renderer in order.
     */
    public void render() {
        this.clearCanvas(g);
        this.drawEdges(g);
        this.drawApples(g);
    }

    /**
     * Clears the canvas by filling it completely with background color
     * @param g The graphics context onto which to render
     */
    private void clearCanvas(GraphicsContext g) {
        g.setFill(backgroundColor);
        g.fillRect(0, 0, App.getWidth(), App.getHeight());
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
     * Draws the game's apples onto the canvas.
     * @param g The canvas onto which to draw
     */
    private void drawApples(GraphicsContext g) {
        ArrayList<Apple> apples = engine.getApples();
        for (Apple apple : apples) { drawCubeEntity(g, appleColor, apple); }
    }

    /**
     * Draws the cube entity onto the canvas
     * @param g The canvas onto which to draw
     * @param color The color of the entity
     * @param entity The entity do draw
     */
    private void drawCubeEntity(GraphicsContext g, Color color, CubeEntity entity) {
        ArrayList<DoubleVector3D> vertices = entity.getVertices();
        for (int i = 0; i < vertices.size(); i++) {
            DoubleVector3D vertex = vertices.get(i);
            DoubleVector3D renderedVertex = applyMatrices(vertex);
            vertices.set(i, renderedVertex);
        }

        g.setFill(color);
        g.fillRect( vertices.get(0).getX(), vertices.get(0).getY(), 
                    vertices.get(3).getX(), vertices.get(3).getY());
        g.fillRect( vertices.get(4).getX(), vertices.get(4).getY(), 
                    vertices.get(1).getX(), vertices.get(1).getY());
        g.fillRect( vertices.get(4).getX(), vertices.get(4).getY(), 
                    vertices.get(7).getX(), vertices.get(7).getY());
        g.fillRect( vertices.get(6).getX(), vertices.get(6).getY(), 
                    vertices.get(3).getX(), vertices.get(3).getY());
        g.fillRect( vertices.get(4).getX(), vertices.get(4).getY(), 
                    vertices.get(2).getX(), vertices.get(2).getY());
        g.fillRect( vertices.get(1).getX(), vertices.get(1).getY(), 
                    vertices.get(7).getX(), vertices.get(7).getY());
    }

    /**
     * Applies the translation matrix and world offset correction to the vertex.
     * @param vertex Vertex to translate
     * @return The translated vertex
     */
    protected DoubleVector3D translate(DoubleVector3D vertex) {
        return vertex.add(engine.getCamera().neg());
    }

    /**
     * Applies the rotation matrices to the vertex in the order:
     * <p> rotZ, rotY, rotX
     * @param vertex The vertex to rotate
     * @return The rotated vertex
     */
    protected DoubleVector3D rotate(DoubleVector3D vertex) {
        return vertex.rotateZ(engine.getRotZ()).rotateY(engine.getRotY()).rotateX(engine.getRotX());
    }

    /**
     * Applies the camera transform matrix
     * @param vertex The vertex to transform
     * @return The transformed vertex
     */
    private DoubleVector3D cameraTransform(DoubleVector3D vertex) {
        double newX = vertex.getX() * FOCAL_LENGTH / (0.0001 * 2);
        double newY = vertex.getY() * FOCAL_LENGTH / (0.0001 * 2);

        return new DoubleVector3D(newX, newY, vertex.getZ());
    }

    /**
     * Applies the perpective correction matrix
     * @param vertex The vertex to correct
     * @return The corrected vertex
     */
    protected DoubleVector3D perspectiveCorrection(DoubleVector3D vertex) {
        return new DoubleVector3D(vertex.getX() / vertex.getZ(), vertex.getY() / vertex.getZ(), vertex.getZ());
    }

    /**
     * Centers the vertex on the screen
     * @param vertex Vertex to translate
     * @return The translated vertex
     */
    protected DoubleVector3D centerOnScreen(DoubleVector3D vertex) {
        return new DoubleVector3D(vertex.getX() + 0.5 * App.getWidth(), vertex.getY() + 0.5 * App.getHeight(), vertex.getZ());
    }

    /**
     * Applies every rendering matrix in order.
     * @param vertex The vertex to render
     * @return The rendered vertex
     */
    private DoubleVector3D applyMatrices(DoubleVector3D vertex) {
        return centerOnScreen(perspectiveCorrection(cameraTransform(rotate(translate(vertex)))));
    }

    /**
     * Anonymous function that applies {@code applyMatrices} to a DoubleVector3D
     */
    private Function<DoubleVector3D, DoubleVector3D> applyMatricesFunc = (v) -> applyMatrices(v);

    /**
     * Predicate that checks if the z-coordinate of a DoubleVector3D is positive
     */
    private Predicate<DoubleVector3D> hasPositiveZ = (v) -> v.getZ() > Double.MIN_VALUE;
    
}
