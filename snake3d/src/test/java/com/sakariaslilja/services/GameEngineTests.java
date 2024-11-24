package com.sakariaslilja.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.models.GameModel;

public class GameEngineTests {

    @Test
    @DisplayName("GameEngine incrementScore")
    void incrementScore() {
        GameEngine engine = new GameEngine(new GameModel(), null);
        int initialScore = engine.getScore();
        engine.incrementScore();
        int newScore = initialScore + 1;
        assertEquals(newScore, engine.getScore(), "The incrementScore method should work as expected");
    }

    @Test
    @DisplayName("GameEngine toGameModel")
    void toGameModel() {
        GameModel model = new GameModel();
        GameEngine engine = new GameEngine(model, null);

        engine.incrementScore();
        GameModel createdModel = engine.toGameModel();

        assertEquals(createdModel.seed, model.seed, "The toGameModel should work as expected");
        assertNotEquals(model.score, createdModel.score, "The score should be different after incrementation");
    }
    
}
