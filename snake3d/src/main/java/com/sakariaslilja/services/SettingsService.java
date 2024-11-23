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

    private final String SETTINGS_PATH = "resources" + File.separator + "game_settings.json";
    private final String BACKUP_PATH ="resources" + File.separator + "game_settings_error.json";

    private static Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat(DateFormat.MEDIUM)
        .create();

    public SettingsService() {
    }

    public SettingsModel getSettings() {
        SettingsModel settingsModel = new SettingsModel();
        String contents = "";
        File settingsFile = new File(SETTINGS_PATH);
        try 
        {
            StringBuilder stringBuilder = new StringBuilder();
            Files.readAllLines(settingsFile.toPath()).forEach( (l) -> stringBuilder.append(l) );
            contents = stringBuilder.toString();
            settingsModel = gson.fromJson(contents, SettingsModel.class);
        } 

        // File did not exist
        catch (FileNotFoundException e) {
            String defaultSettings = gson.toJson(settingsModel);
            createAndPopulateFile(settingsFile, defaultSettings);
        }

        // The JSON in the file was wrong
        catch (JsonSyntaxException e) {
            File backup = new File(BACKUP_PATH);
            createAndPopulateFile(backup, contents);
            String settingsJson = gson.toJson(settingsModel);
            createAndPopulateFile(settingsFile, settingsJson);
        }

        // I/O Exception
        catch (IOException e) {
            e.printStackTrace();
        }

        return settingsModel;
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
