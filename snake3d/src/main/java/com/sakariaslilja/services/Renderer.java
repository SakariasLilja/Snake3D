package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.sakariaslilja.App;
import com.sakariaslilja.IConstants;
import com.sakariaslilja.datastructures.ColoredCollector;
import com.sakariaslilja.datastructures.DoubleVector3D;
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
public class Renderer implements IConstants {

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
    private Object[] getCubeEntities() {
        ArrayList<Snake> snake = engine.getSnake();
        ArrayList<Apple> apples = engine.getApples();

        Object[] entities = new Object[snake.size() - 1 + apples.size()];

        int index = 0;
        while (index < snake.size() - 1) {
            ArrayList<DoubleVector3D> vertices = snake.get(index + 1).getVertices();
            vertices.forEach(applyMatricesFunc);
            entities[index] = new ColoredCollector<ArrayList<DoubleVector3D>>(vertices, snakeColor);
            index++;
        }

        int offset = index;
        index = 0;

        while (index < apples.size()) {
            ArrayList<DoubleVector3D> vertices = apples.get(index).getVertices();
            vertices.forEach(applyMatricesFunc);
            entities[index + offset] = new ColoredCollector<ArrayList<DoubleVector3D>>(vertices, appleColor);
            index++;
        }

        return entities;
    }

    /**
     * Draws each cube entity in order of their z-coordinate.
     * <p> I.e. sorts the cube entities by their z-coordinate and draws them.
     * @param g The graphics context onto which to draw
     * @param entities The list of entities to draw
     */
    @SuppressWarnings("unchecked")
    private void drawCubeEntities(GraphicsContext g, Object[] entities) {
        Arrays.parallelSort(entities, new EntityZComparator());
        
        for (Object o : entities) {
            ColoredCollector<ArrayList<DoubleVector3D>> entity = (ColoredCollector<ArrayList<DoubleVector3D>>) o;
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
     */
    protected void translate(DoubleVector3D vertex) {
        DoubleVector3D camera = engine.camera().duplicate();
        camera.neg();
        vertex.add(camera);
    }

    /**
     * Applies the rotation matrices to the vertex in the order:
     * <p> rotZ, rotY, rotX
     * @param vertex The vertex to rotate
     */
    protected void rotate(DoubleVector3D vertex) {
        Quaternion rotation = engine.quaternion();
        rotation.applyRotation(vertex);
    }

    /**
     * Applies the camera transform matrix
     * @param vertex The vertex to transform
     */
    private void cameraTransform(DoubleVector3D vertex) {
        double newX = vertex.getX() * FOCAL_LENGTH / (0.0001 * 2);
        double newY = vertex.getY() * FOCAL_LENGTH / (0.0001 * 2);

        vertex.setX(newX);
        vertex.setY(newY);
    }

    /**
     * Applies the perpective correction matrix to the vertex.
     * @param vertex The vertex to correct
     */
    protected void perspectiveCorrection(DoubleVector3D vertex) {
        double oneOverZ = 1.0 / vertex.getZ();
        vertex.setX(vertex.getX() * oneOverZ);
        vertex.setY(vertex.getY() * oneOverZ);
    }

    /**
     * Centers the vertex on the screen.
     * @param vertex Vertex to translate
     */
    private void centerOnScreen(DoubleVector3D vertex) {
        vertex.setX(vertex.getX() + 0.5 * App.getWidth());
        vertex.setY(vertex.getY() + 0.5 * App.getHeight());
    }

    /**
     * Applies every rendering matrix in order.
     * @param vertex The vertex to render
     * @return The rendered vertex
     */
    private void applyMatrices(DoubleVector3D vertex) {
        translate(vertex);
        rotate(vertex);
        cameraTransform(vertex);
        perspectiveCorrection(vertex);
        centerOnScreen(vertex);
    }

    /**
     * Consumer function that applies the matrices to a vertex.
     */
    private Consumer<DoubleVector3D> applyMatricesFunc = v -> applyMatrices(v);

    /**
     * Predicate that checks if the z-coordinate of a DoubleVector3D is positive
     */
    private Predicate<DoubleVector3D> hasPositiveZ = (v) -> v.getZ() > Double.MIN_VALUE;
    
}
