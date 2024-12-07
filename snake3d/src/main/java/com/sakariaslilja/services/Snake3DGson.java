package com.sakariaslilja.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Interface for the Gson object used by the game
 */
public interface Snake3DGson {

    /**
     * The Gson service used by the application I/O services
     */
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
    
}
