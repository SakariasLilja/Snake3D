package com.sakariaslilja.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.google.gson.JsonSyntaxException;
import com.sakariaslilja.App;
import com.sakariaslilja.models.GameModel;

/**
 * Service for reading the files in the games folder
 */
public class GamesService implements Snake3DGson {

    private static final String GAMES_DIRECTORY = App.DIRECTORY_PATH.toString() + File.separator + "games";

    protected static Predicate<Path> isGameFile = (p) -> {
        String fileName = p.getFileName().toString();
        return Files.isRegularFile(p) && fileName.startsWith("game_") && fileName.endsWith(".json");
    };

    /**
     * Reads all the game files and their contents that are located in the games folder.
     * @return An ArrayList of game models
     */
    public static ArrayList<GameModel> getGames() {
        try {
            Files.createDirectories(Paths.get(GAMES_DIRECTORY));
            Stream<Path> pathStream = Files.walk(Paths.get(GAMES_DIRECTORY));
            ArrayList<Path> gamePaths = new ArrayList<>();
            pathStream.forEach(p -> gamePaths.add(p));
            pathStream.close();
            gamePaths.removeIf(isGameFile.negate());
            ArrayList<GameModel> games = new ArrayList<>();

            for (Path p : gamePaths) {
                try {
                    String fileContents = ReadFileService.readFile(p);
                    GameModel game = gson.fromJson(fileContents, GameModel.class);
                    games.add(game);
                }

                // File wasn't found, shouldn't be possible
                catch (NoSuchFileException e) {
                    System.out.println(p.getFileName().toString() + " couldn't be found.");
                }

                // JSON-syntax is invalid in the game file
                catch (JsonSyntaxException e) {
                    System.out.println("Invalid JSON syntax in file: " + p.getFileName());
                }                
            }
            return games;
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
