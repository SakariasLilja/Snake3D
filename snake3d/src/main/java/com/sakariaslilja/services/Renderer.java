package com.sakariaslilja.services;

import com.sakariaslilja.Constants;
import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.World;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Renderer for Snake 3D.
 */
public class Renderer {

    // Local variables
    private final Color backgroundColor = Color.web("#6495ED");

    /**
     * Render function that calls all other functions of Renderer in order.
     * @param g The graphics context onto which to render
     * @param world The world used for calculations
     * @param camera The camera location from which to render
     * @param normalVector The camera's normal vector
     */
    public void render(GraphicsContext g, World world, DoubleVector3D camera, DoubleVector3D normalVector) {
        this.clearCanvas(g);
    }

    /**
     * Clears the canvas by filling it completely with background color
     * @param g
     */
    private void clearCanvas(GraphicsContext g) {
        g.setFill(backgroundColor);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
    }
    
}
