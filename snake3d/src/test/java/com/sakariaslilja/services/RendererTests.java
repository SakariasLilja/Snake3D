package com.sakariaslilja.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.models.DoubleVector3D;
import com.sakariaslilja.models.GameModel;

public class RendererTests {

    // TODO: re-implement w/ correct camera
    /*@Test
    @DisplayName("Renderer translate")
    public void renderTranslate() {
        DoubleVector3D camera = new DoubleVector3D(1, 1, 1);
        GameEngine engine = new GameEngine(new GameModel(), camera);
        Renderer renderer = new Renderer(null, engine);

        DoubleVector3D vertex = new DoubleVector3D(0, 0, 0);
        DoubleVector3D expected = new DoubleVector3D(-1, -1, -1);

        assertEquals(expected, renderer.translate(vertex), "The translate method should work as expected");
    }*/

    /*@Test
    @DisplayName("Renderer rotations")
    public void renderRotations() {
        GameModel model = new GameModel();
        model.rotX = 90;
        model.rotY = 90;
        GameEngine engine = new GameEngine(model);
        Renderer renderer = new Renderer(null, engine);

        DoubleVector3D vertex = new DoubleVector3D(1, 2, 3);
        DoubleVector3D expected = new DoubleVector3D(3, -2, 1);

        assertEquals(expected, renderer.rotate(vertex).round(), "The rotate method should apply the matrices correctly");
    }*/

    @Test
    @DisplayName("Renderer perspectiveCorrection")
    public void rendererPerspectiveCorrection() {
        GameEngine engine = new GameEngine(new GameModel());
        Renderer renderer = new Renderer(null, engine);

        DoubleVector3D vertex = new DoubleVector3D(8, -4, 2);
        DoubleVector3D expected = new DoubleVector3D(4, -2, 2);

        assertEquals(expected, renderer.perspectiveCorrection(vertex), "The perspective correction should work as expected");
    }
    
}
