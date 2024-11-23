package com.sakariaslilja.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.models.SettingsModel;

public class SettingsServiceTests {

    @Test
    @DisplayName("SettingsService getSettings")
    void getSettings() {
        SettingsService service = new SettingsService();
        SettingsModel defaultSettings = new SettingsModel();
        assertEquals("validate", defaultSettings.validate, "The validation message should be correct");

        SettingsModel settings = service.getSettings();
        
        assertEquals(defaultSettings.validate, settings.validate, "The getSettings method should work");

    }

    @Test
    @DisplayName("SettingsService saveSettings, resetSettings")
    void saveSettings() {
        SettingsModel settings = new SettingsModel();
        String defaultValidate = settings.validate;
        String newValidate = "this";
        settings.validate = newValidate;

        SettingsService service = new SettingsService();
        service.saveSettings(settings);
        SettingsModel modified = service.getSettings();

        assertEquals(newValidate, modified.validate, "The saveSettings should work as expected");

        service.resetSettings();
        SettingsModel reseted = service.getSettings();

        assertEquals(defaultValidate, reseted.validate, "The reset method should work as expected");
    }
    
}
