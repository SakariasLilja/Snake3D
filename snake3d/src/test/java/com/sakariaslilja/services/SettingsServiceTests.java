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
    
}
