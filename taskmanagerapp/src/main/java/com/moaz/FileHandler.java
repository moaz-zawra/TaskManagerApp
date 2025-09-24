package com.moaz;

import java.io.File; // Import the File library for file creation and handling
import java.io.IOException; // Import the IOException library for error handling

// Import fasterxml jackson libraries for using TypeReference, ObjectMapper, and SerializationFeature
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.List; // Import the List class

public class FileHandler {

    // Create two methods for saving and loading task data from a JSON file

    // Save data:
    public void saveToFile(List<Task> taskList) {
        // Create an ObjectMapper object called mapper
        ObjectMapper mapper = new ObjectMapper();
        // Enable pretty printing for formatted output
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Create and initialize the save file
        File saveFile = new File("tasks.json");

        // Utilize try & catch for error handling
        try {
            // Write the List as a JSON array to the save file
            mapper.writeValue(saveFile, taskList);
            // Print a message if the operation was completed successfully
            System.out.println("Task data successfully saved to " + saveFile.getAbsolutePath());
        } catch (IOException e) { // Catch any errors during the operation and print the error
            e.printStackTrace();
        }
    }

    // Load data:
    public List<Task> loadFromFile() {
        // Create an ObjectMapper object called mapper
        ObjectMapper mapper = new ObjectMapper();

        // Utilize try & catch for error handling
        try {
            // Path to JSON file
            File saveFile = new File("tasks.json");
            // Create a List holding Task objects and read JSON data from save file into it
            // Use TypeReference to correctly deserialize the JSON array in the form of
            // List<Task>
            List<Task> taskList = mapper.readValue(saveFile, new TypeReference<List<Task>>() {
            });

            return taskList;
        } catch (IOException e) { // Catch any errors during the operation and print the error
            e.printStackTrace();
        }
        // Return an empty list if loading fails
        return new ArrayList<>();
    }

}
