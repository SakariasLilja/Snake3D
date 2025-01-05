package com.sakariaslilja.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.App;
import com.sakariaslilja.models.HighscoreModel;

public class HighscoresServiceTests {

    private final String highscoreString = App.DIRECTORY_PATH.toString() + File.separator + "highscores.json";
    private final String errorString = App.DIRECTORY_PATH.toString() + File.separator + "highscores_error.json";

    private final Path highscorePath = Paths.get(highscoreString);
    private final Path errorPath = Paths.get(errorString);

    @Test
    @DisplayName("createHighscoresFiles test")
    public void createHighscoreFilesTest() {
        HighscoresService.createHighscoreFiles();

        assertDoesNotThrow(() -> { ReadFileService.readFile(highscorePath); }, "highscores.json should exist after creation");
        assertDoesNotThrow(() -> { ReadFileService.readFile(errorPath); }, "highscores_error.json should exist after creation");
    }
    
    @Test
    @DisplayName("getHighscores test")
    public void getHighscoresTest() {
        HighscoresService.createHighscoreFiles();
        HighscoreModel[] highscores = HighscoresService.getHighscores();

        assertNotNull(highscores, "getHighscores should return an array");
    }

    @Test
    @DisplayName("saveToHighscores test")
    public void saveToHighscoresTest() {
        try {
            HighscoresService.createHighscoreFiles();
            String oldContents = ReadFileService.readFile(highscorePath);
            String testMsg = "test";
            HighscoresService.saveToHighscores(testMsg);
            String readMsg = ReadFileService.readFile(highscorePath);
            HighscoresService.saveToHighscores(oldContents);

            assertEquals(testMsg, readMsg, "saveToHighscores should work as expected");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("saveError test")
    public void saveErrorTest() {
        try {
            HighscoresService.createHighscoreFiles();
            String oldError = ReadFileService.readFile(errorPath);
            String testMsg = "test";
            HighscoresService.saveError(testMsg);
            String readMsg = ReadFileService.readFile(errorPath);
            HighscoresService.saveError(oldError);

            assertEquals(testMsg, readMsg, "saveError should work as expected");
        }
        catch (NoSuchFileException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("clearHighscores test")
    public void clearHighscoresTest() {
        try {
            HighscoresService.createHighscoreFiles();
            String oldContents = ReadFileService.readFile(highscorePath);
            HighscoresService.clearHighscores();
            String expected = "[]";
            String clearedString = ReadFileService.readFile(highscorePath);
            HighscoresService.saveToHighscores(oldContents);

            assertEquals(expected, clearedString, "The clear highscores should work as expected");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("addHighscore test")
    public void addHighscoreTest() {
        try {
            HighscoresService.createHighscoreFiles();
            String oldContents = ReadFileService.readFile(highscorePath);
            HighscoresService.clearHighscores();
            HighscoreModel model = new HighscoreModel();
            model.score = 123;
            HighscoresService.addHighscore(model);
            HighscoreModel[] models = HighscoresService.getHighscores();
            assertEquals(model.score, models[0].score, "addHighscore should work as expected");
            HighscoresService.saveToHighscores(oldContents);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
