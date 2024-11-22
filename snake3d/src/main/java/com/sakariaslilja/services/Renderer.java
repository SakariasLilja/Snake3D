package com.sakariaslilja.services;

import java.util.ArrayList;

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
    private DoubleVector3D camera;
    private DoubleVector3D normalVector;

    private final Color backgroundColor = Color.RED; //Color.web("#4a4e69");
    //private final Color strokeColor = Color.web("#6495ED");
    //private final Color snakeColor = Color.web("#F5F5F5");

    /**
     * Renderer instance.
     * @param g The graphics context onto which to draw
     * @param edges The edges of the world
     * @param camera The camera of the world
     * @param normalVector The normal vector of the camera
     */
    @SuppressWarnings("exports")
    public Renderer(GraphicsContext g, ArrayList<Tuple> edges, DoubleVector3D camera, DoubleVector3D normalVector) {
        this.g = g;
        this.edges = edges;
        this.camera = camera;
        this.normalVector = normalVector;
    }

    /**
     * Render function that calls all other functions of Renderer in order.
     */
    public void render() {
        this.clearCanvas(g);
    }

    /**
     * Clears the canvas by filling it completely with background color
     * @param g The graphics context onto which to render
     */
    private void clearCanvas(GraphicsContext g) {
        g.setFill(backgroundColor);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
    }
    
}
