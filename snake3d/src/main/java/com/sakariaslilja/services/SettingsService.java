package com.sakariaslilja.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sakariaslilja.models.SettingsModel;

/**
 * Service for getting and setting data from game_settings.json
 */
public class SettingsService {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(DateFormat.MEDIUM).create();

    private final String SETTINGS_PATH = "resources" + File.separator + "game_settings.json";
    private final String BACKUP_PATH ="resources" + File.separator + "game_settings_error.json";

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
            Files.readAllLines(settingsFile.toPath()).forEach( (l) -> stringBuilder.append(l) );
            contents = stringBuilder.toString();
            settingsModel = gson.fromJson(contents, SettingsModel.class);
        } 

        // File did not exist
        catch (FileNotFoundException e) {
            createAndPopulateFile(settingsFile, defaultSettingsString);
        }

        // The JSON in the file was wrong
        catch (JsonSyntaxException e) {
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
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(contents);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
