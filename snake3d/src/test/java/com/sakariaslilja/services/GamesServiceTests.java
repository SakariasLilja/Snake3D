package com.sakariaslilja.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.App;
import com.sakariaslilja.models.GameModel;

public class GamesServiceTests {
    
    @Test
    @DisplayName("GamesService isGameFile")
    public void isGameFileTest() {
        Path correct = Paths.get(App.DIRECTORY_PATH + File.separator + "games" + File.separator + "game_test.json");
        Path wrongDirectory = Paths.get(App.DIRECTORY_PATH + File.separator + "game_test.json");
        Path wrongEnding = Paths.get(App.DIRECTORY_PATH + File.separator + "games" + File.separator + "game_test.jon");
        Path wrongPrefix = Paths.get(App.DIRECTORY_PATH + File.separator + "games" + File.separator + "gam_test.json");

        assertTrue(GamesService.isGameFile.test(correct), "Predicates should work as expected");
        assertFalse(GamesService.isGameFile.test(wrongDirectory), "Predicate should detect incorrect file");
        assertFalse(GamesService.isGameFile.test(wrongEnding), "Predicate should detect incorrect file ending");
        assertFalse(GamesService.isGameFile.test(wrongPrefix), "Predicate should detect incorrect prefix");
    }

    @Test
    @DisplayName("GamesService getGames")
    public void getGamesTest() {
        ArrayList<GameModel> gameModels = GamesService.getGames();
        assertNotNull(gameModels);
    }

    @Test
    @DisplayName("GamesService save and delete game")
    public void saveGameTest() {
        GameModel model = new GameModel();
        model.seed = 0;
        GamesService.saveGame(model);
        ArrayList<GameModel> savedGames = GamesService.getGames();
        boolean savedSuccessfully = savedGames.removeIf(g -> g.seed == 0l);
        assertTrue(savedSuccessfully, "The saveGame method should work as expected");

        GamesService.deleteGame(model);
        savedGames = GamesService.getGames();
        boolean deletedSuccesfully = savedGames.removeIf(g -> g.seed == 0l);
        assertFalse(deletedSuccesfully, "The deleteGame method should work as expected");
    }

}
