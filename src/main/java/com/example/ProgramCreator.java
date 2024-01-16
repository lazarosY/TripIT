package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ProgramCreator {

    public static void programMaker(String gptResponse, String dest) throws IOException {
        String FILE_NAME = "Vacation_Program" + dest + ".txt";
        // Get the desktop directory dynamically
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        // Construct the file path
        File file = new File(desktopPath, FILE_NAME);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {

            out.write(gptResponse);

        } catch (IOException e) {
            System.err.println("Couldn't write byte" + e.getMessage());
            System.exit(1);
        }

    }
}
