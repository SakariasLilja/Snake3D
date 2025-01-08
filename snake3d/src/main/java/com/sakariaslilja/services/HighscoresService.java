package com.sakariaslilja.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.gson.JsonSyntaxException;
import com.sakariaslilja.App;
import com.sakariaslilja.models.HighscoreModel;

/**
 * Service to read and write the contents of the game's highscores file
 */
public class HighscoresService implements Snake3DGson {
    
    private static final String HIGHSCORES_PATH_STRING = App.DIRECTORY_PATH.toString() + File.separator + "highscores.json";
    private static final String HIGHSCORES_ERROR_PATH_STRING = App.DIRECTORY_PATH.toString() + File.separator + "highscores_error.json";

    private static final Path HIGHSCORES_PATH = Paths.get(HIGHSCORES_PATH_STRING);
    private static final Path HIGHSCORES_ERROR_PATH = Paths.get(HIGHSCORES_ERROR_PATH_STRING);

    /**
     * Reads the highscores file
     * @return An array of highscores
     */
    public static HighscoreModel[] getHighscores() {
        HighscoreModel[] highscores = {};
        String highscoresString = "[]";

        try {
            System.out.println("Reading highscores...");
            highscoresString = ReadFileService.readFile(HIGHSCORES_PATH);
            highscores = gson.fromJson(highscoresString, HighscoreModel[].class);
            System.out.println("Successfully read highscores!");
        }

        // File wasn't found.
        catch (NoSuchFileException e) {
            createHighscoreFiles();
        }

        // JSON syntax in file was invalid
        catch (JsonSyntaxException e) {
            saveError(highscoresString);
            clearHighscores();
            System.out.println("Invalid JSON in highscores.json");
        }

        return highscores;
    }

    /**
     * @param highscore Saves this highscore to the list of saved highscores
     */
    public static boolean addHighscore(HighscoreModel highscore) {
        try {
            HighscoreModel[] highscores = getHighscores();
            HighscoreModel[] newScores = Arrays.copyOf(highscores, highscores.length + 1);
            newScores[highscores.length] = highscore;
            String highscoresString = gson.toJson(newScores);

            System.out.println("Adding highscore...");
            saveToHighscores(highscoresString);
            System.out.println("Added highscore successfully!");

            return true;
        }

        // Unknown exception
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param contents The contents to save to the highscores file
     * @throws IOException If an I/O error occurs
     */
    protected static void saveToHighscores(String contents) throws IOException {
        createHighscoreFiles();
        BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORES_PATH.toFile()));
        writer.write(contents);
        writer.close();
    }

    /**
     * @param message Message to save in the highscores error file
     */
    protected static void saveError(String message) {
        try {
            createHighscoreFiles();
            BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORES_ERROR_PATH.toFile()));
            writer.write(message);
            writer.close();
        }

        // Unknown exception
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves an empty array to the highscores.json file
     */
    protected static void clearHighscores() {
        try {
            createHighscoreFiles();
            BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORES_PATH.toFile()));
            writer.write("[]");
            writer.close();
        }
        
        // Unknown exception
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the highscores file and the root folders if necessary
     */
    protected static void createHighscoreFiles() {
        try {
            Files.createDirectories(App.DIRECTORY_PATH);
            
            HIGHSCORES_ERROR_PATH.toFile().createNewFile();
            if (HIGHSCORES_PATH.toFile().createNewFile()) { clearHighscores(); }
        }

        // Unhandled exception
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
