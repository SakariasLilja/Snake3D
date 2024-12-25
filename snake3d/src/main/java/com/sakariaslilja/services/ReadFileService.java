package com.sakariaslilja.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class ReadFileService {

    /**
     * Reads the contents of a file and returns it as a string.
     * @param path The file to read
     * @return String contents of the file
     * @throws NoSuchFileException File doesn't exist
     */
    public static String readFile(Path path) throws NoSuchFileException {
        String fileName = path.getFileName().toString();
        try {
            StringBuilder sb = new StringBuilder();
            System.out.println("Reading " + fileName + "...");
            Files.readAllLines(path).forEach(l -> sb.append(l));
            System.out.println(fileName + " successfully read.");
            return sb.toString();
        }
        catch (NoSuchFileException | FileNotFoundException e) {
            throw new NoSuchFileException(e.getLocalizedMessage());
        }

        catch (IOException e) {
            System.out.println("IOException when reading file: " + fileName);
            return "";
        }

        catch (SecurityException e) {
            System.out.println("Cannot access " + fileName + " due to security restrictions.");
            return "";
        }
    }
    
}
