package com.moaz;

import javafx.application.Application; // Import the JavaFX Application class
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage; // Import the JavaFX Stage class
import javafx.scene.Group; // Import the JavaFX Group class
import javafx.scene.Parent;
import javafx.scene.Scene; // Import the JavaFX Scene class
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainApp extends Application {
    public static void main(String[] args) {

        launch();

        // Create a TaskManager object
        /* TaskManager taskManager = new TaskManager(); */

        /*
         * // Get all tasks from the taskList
         * taskManager.getAllTasks();
         * 
         * // Update the second task
         * taskManager.updateTask(1, "Pray the remaining prayers");
         * 
         * // Mark task 1 as complete
         * taskManager.markComplete(0);
         * 
         * // Display the info about each task - after the changes
         * taskManager.getAllTasks();
         * 
         * // Remove the first task
         * taskManager.removeTask(0);
         * 
         * // Get all tasks from the taskList - after removal of the first task
         * taskManager.getAllTasks();
         */

        /*
         * // Save the user's task data in an external save file
         * taskManager.saveData();
         * 
         * // Load the user's data from the save file
         * taskManager.loadData();
         */
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        stage.setTitle("Task Manager App");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
