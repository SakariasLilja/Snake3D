package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

import com.sakariaslilja.App;
import com.sakariaslilja.IConstants;
import com.sakariaslilja.datastructures.DoubleVector3D;
import com.sakariaslilja.datastructures.IHeading;
import com.sakariaslilja.datastructures.Quaternion;
import com.sakariaslilja.datastructures.Tuple;
import com.sakariaslilja.entities.Apple;
import com.sakariaslilja.entities.CubeEntity;
import com.sakariaslilja.entities.Snake;
import com.sakariaslilja.models.SettingsModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 3D renderer for Snake 3D.
 */
public class Renderer implements IConstants, IHeading {

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
        this.drawSnake(g);
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
     * Draws the snake onto the canvas.
     * This excludes the snake's head, as the player needs to see.
     * @param g The canvas onto which to draw
     */
    private void drawSnake(GraphicsContext g) {
        ArrayList<Snake> snake = engine.getSnake();
        int i = 1;
        while (i < snake.size()) {
            drawCubeEntity(g, snakeColor, snake.get(i));
        }
    }

    /**
     * Draws the cube entity onto the canvas.
     * A cube consists of 6 faces, each which can be represented as a
     * 4-sided polygon. 
     * @param g The canvas onto which to draw
     * @param color The color of the entity
     * @param entity The entity do draw
     */
    private void drawCubeEntity(GraphicsContext g, Color color, CubeEntity entity) {
        ArrayList<DoubleVector3D> vertices = entity.getVertices();
        // Render each vertex
        for (int i = 0; i < vertices.size(); i++) {
            DoubleVector3D vertex = vertices.get(i);
            DoubleVector3D renderedVertex = applyMatrices(vertex);
            vertices.set(i, renderedVertex);
        }
        
        // The vertices used to make up each respective face, ordered so that a square can be drawn
        DoubleVector3D[] frontFace  = {vertices.get(0), vertices.get(1), vertices.get(3), vertices.get(2)};
        DoubleVector3D[] rearFace   = {vertices.get(4), vertices.get(5), vertices.get(7), vertices.get(6)};
        DoubleVector3D[] topFace    = {vertices.get(0), vertices.get(1), vertices.get(5), vertices.get(4)};
        DoubleVector3D[] bottomFace = {vertices.get(2), vertices.get(3), vertices.get(7), vertices.get(6)};
        DoubleVector3D[] leftFace   = {vertices.get(0), vertices.get(2), vertices.get(6), vertices.get(4)};
        DoubleVector3D[] rightFace  = {vertices.get(1), vertices.get(3), vertices.get(7), vertices.get(5)};

        DoubleVector3D[][] faces = {frontFace, rearFace, topFace, bottomFace, leftFace, rightFace};

        g.setFill(color);
        
        for (DoubleVector3D[] face : faces) {
            // Don't draw if any of the points are behind the camera
            if (face[0].getZ() < 0 || face[1].getZ() < 0 || face[2].getZ() < 0 || face[3].getZ() < 0) { continue; }

            double[] xPoints = new double[face.length];
            double[] yPoints = new double[face.length];

            for (int i = 0; i < face.length; i++) {
                xPoints[i] = face[i].getX();
                yPoints[i] = face[i].getY();
            }

            g.fillPolygon(xPoints, yPoints, xPoints.length);
        }
    }

    /**
     * Applies the translation matrix and world offset correction to the vertex.
     * @param vertex Vertex to translate
     * @return The translated vertex
     */
    protected DoubleVector3D translate(DoubleVector3D vertex) {
        return vertex.add(engine.camera().neg());
    }

    /**
     * Applies the rotation matrices to the vertex in the order:
     * <p> rotZ, rotY, rotX
     * @param vertex The vertex to rotate
     * @return The rotated vertex
     */
    protected DoubleVector3D rotate(DoubleVector3D vertex) {
        //Quaternion q = new Quaternion(engine.getQVector(), engine.getRotation());
        Quaternion q = engine.quaternion();
        return q.applyRotation(vertex);
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
    private DoubleVector3D centerOnScreen(DoubleVector3D vertex) {
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
