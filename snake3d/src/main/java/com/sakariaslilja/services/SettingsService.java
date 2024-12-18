package com.sakariaslilja.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.JsonSyntaxException;
import com.sakariaslilja.models.SettingsModel;

/**
 * Service for getting and setting data from game_settings.json
 */
public class SettingsService implements Snake3DGson {

    private final Path DIRECTORY_PATH = Paths.get("resources").toAbsolutePath();
    private final String SETTINGS_PATH = DIRECTORY_PATH.toString() + File.separator + "game_settings.json";
    private final String BACKUP_PATH = DIRECTORY_PATH.toString() + File.separator + "game_settings_error.json";

    File settingsFile = new File(SETTINGS_PATH);
    File backupFile = new File(BACKUP_PATH);

    SettingsModel defaultSettings = new SettingsModel();
    String defaultSettingsString = gson.toJson(defaultSettings);

    /**
     * Gets the settings saved at game_settings.json
     * @return The settings at game_settings.json/the default settings
     */
    public SettingsModel getSettings() {
        SettingsModel settingsModel = new SettingsModel();
        String contents = "";
        try 
        {
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("Reading settings file...");
            Files.readAllLines(settingsFile.toPath()).forEach( (l) -> stringBuilder.append(l) );
            System.out.println("Settings file succesfully read!");
            contents = stringBuilder.toString();
            settingsModel = gson.fromJson(contents, SettingsModel.class);
        } 

        // File did not exist
        catch (FileNotFoundException | NoSuchFileException e) {
            System.out.println("Settings file not found.");
            createAndPopulateFile(settingsFile, defaultSettingsString);
        }

        // The JSON in the file was wrong
        catch (JsonSyntaxException e) {
            System.out.println("Settings file doesn't contain valid json.");
            createAndPopulateFile(backupFile, contents);
            resetSettings();
        }

        // I/O Exception
        catch (IOException e) {
            e.printStackTrace();
        }

        return settingsModel;
    }

    /**
     * Saves new settings to game_settings.json
     * @param settings The settings to save
     */
    public void saveSettings(SettingsModel settings) {
        String jsonString = gson.toJson(settings);
        createAndPopulateFile(settingsFile, jsonString);
    }

    /**
     * Sets the settings at game_settings.json to default values
     */
    public void resetSettings() {
        createAndPopulateFile(settingsFile, defaultSettingsString);
    }

    /**
     * Creates and writes to a file
     * @param file The file to write
     * @param contents The wanted contents of the file
     */
    private void createAndPopulateFile(File file, String contents) {
        BufferedWriter writer;
        System.out.println("Creating and populating file...");
        try {
            System.out.println("creating directories...");
            Files.createDirectories(DIRECTORY_PATH);
            System.out.println("directories created!");
            if (file.createNewFile()) { System.out.println("created missing file"); }
            writer = new BufferedWriter(new FileWriter(file));
            System.out.println("populating...");
            writer.write(contents);
            System.out.println("populated!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
