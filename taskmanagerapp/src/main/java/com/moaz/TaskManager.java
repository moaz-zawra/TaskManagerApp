package com.moaz;

import java.time.Instant;
import java.util.ArrayList; // Import the ArrayList class
import java.util.List; // Import the List class

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskManager {
    // Create a task list to store objects of type Task
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    // Class methods:
    // Add a given task to the taskList
    public void addTask(Task task) {
        taskList.add(task);
    }

    // Remove a given task from the taskList
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    // Update an existing task with a new title
    public void updateTask(Task task, String newTitle) {
        task.setTitle(newTitle);
    }

    // Mark a given task as 'completed'
    public void markComplete(Task task) {
        task.setIsCompleted(true);
    }

    // Retrieve the entire taskList
    public ObservableList<Task> getTaskList() {
        return taskList;
    }

    // Save a copy of all the user's tasks to the save file
    public void saveData() {
        // Initialize a FileHandler object called fileHandler
        FileHandler fileHandler = new FileHandler();
        // Pass the list of task objects to the method saveToFile
        fileHandler.saveToFile(taskList);
    }

    // Load the user's data from the save file
    public ObservableList<Task> loadData() {
        // Initialize a FileHandler object called fileHandler
        FileHandler fileHandler = new FileHandler();
        // Call the loadToFile method using the FileHandler object
        ObservableList<Task> taskList = fileHandler.loadFromFile();
        return taskList;
    }

}
