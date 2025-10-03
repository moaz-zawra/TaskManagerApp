package com.moaz.taskmanager.model;

import java.io.File; // Import the File library for file creation and handling
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException library for error handling

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moaz.taskmanager.utils.DataUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Import the List class

import java.util.List;

public class FileHandler {

    // Create two methods for saving and loading task data from a JSON file

    // Save data:
    public static void saveToFile(ObservableList<Task> taskList) {
        // Retrieve the tasks.json file using the method in DataUtils class
        File tasksFile = DataUtils.getTasksFile();

        // Create an ObjectMapper object called mapper
        ObjectMapper mapper = new ObjectMapper();
        // Enable pretty printing for formatted output
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Utilize try & catch for error handling
        try (FileWriter writer = new FileWriter(tasksFile)) {
            // Write the List as a JSON array to the save file
            mapper.writeValue(writer, taskList);
            // Print a message if the operation was completed successfully
            System.out.println("Task data successfully saved to " + tasksFile.getAbsolutePath());
        } catch (IOException e) { // Catch any errors during the operation and print the error
            e.printStackTrace();
        }
    }

    // Load data:
    public static ObservableList<Task> loadFromFile() {
        // Retrieve the tasks.json file using the method in DataUtils class
        File tasksFile = DataUtils.getTasksFile();

        // Create an ObjectMapper object called mapper
        ObjectMapper mapper = new ObjectMapper();

        // Utilize try & catch for error handling
        try (FileReader reader = new FileReader(tasksFile)) {
            // Deserialize into a List
            List<Task> taskList = mapper.readValue(reader, new TypeReference<List<Task>>() {
            });

            // Convert List to an ObservableList
            return FXCollections.observableArrayList(taskList);
        } catch (IOException e) { // Catch any errors during the operation and print the error
            e.printStackTrace();
        }
        // Return an empty list if loading fails
        return FXCollections.observableArrayList();
    }

}
