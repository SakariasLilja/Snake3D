package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.sakariaslilja.App;
import com.sakariaslilja.IConstants;
import com.sakariaslilja.datastructures.ColoredCollector;
import com.sakariaslilja.datastructures.DoubleVector3D;
import com.sakariaslilja.datastructures.IHeading;
import com.sakariaslilja.datastructures.Quaternion;
import com.sakariaslilja.datastructures.Tuple;
import com.sakariaslilja.entities.Apple;
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
        /*this.drawApples(g);
        this.drawSnake(g);*/
        drawCubeEntities(g, getCubeEntities());
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
            boolean allPositive = copy.forAll(hasPositiveZ);
            if (allPositive){
                DoubleVector3D point1 = copy.value1;
                DoubleVector3D point2 = copy.value2;

                g.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            }
        }
    }

    /**
     * @return Each entity's rendered vertices
     */
    private ArrayList<ColoredCollector<ArrayList<DoubleVector3D>>> getCubeEntities() {
        ArrayList<ColoredCollector<ArrayList<DoubleVector3D>>> entities = new ArrayList<>();

        ArrayList<Snake> snake = engine.getSnake();
        ArrayList<Apple> apples = engine.getApples();

        UnaryOperator<DoubleVector3D> renderVertex = v -> applyMatrices(v);

        for (int i = 1; i < snake.size(); i++) {
            Snake segment = snake.get(i);
            ArrayList<DoubleVector3D> vertices = segment.getVertices();
            vertices.replaceAll(renderVertex);
            entities.add(new ColoredCollector<ArrayList<DoubleVector3D>>(vertices, snakeColor));
        }

        for (Apple apple : apples) {
            ArrayList<DoubleVector3D> vertices = apple.getVertices();
            vertices.replaceAll(renderVertex);
            entities.add(new ColoredCollector<ArrayList<DoubleVector3D>>(vertices, appleColor));
        }

        return entities;
    }

    /**
     * Draws each cube entity in order of their z-coordinate.
     * <p> I.e. sorts the cube entities by their z-coordinate and draws them.
     * @param g The graphics context onto which to draw
     * @param entities The list of entities to draw
     */
    private void drawCubeEntities(GraphicsContext g, ArrayList<ColoredCollector<ArrayList<DoubleVector3D>>> entities) {
        Collections.sort(entities, new EntityZComparator());
        for (ColoredCollector<ArrayList<DoubleVector3D>> entity : entities) {
            drawCubeEntity(g, entity.color(), entity.obj());
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
    private void drawCubeEntity(GraphicsContext g, Color color, ArrayList<DoubleVector3D> vertices) {
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
