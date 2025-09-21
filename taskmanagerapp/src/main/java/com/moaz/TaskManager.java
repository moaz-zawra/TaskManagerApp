package com.moaz;

import java.util.ArrayList; // Import the ArrayList class
import java.util.List; // Import the List class

public class TaskManager {
    // Create a task list to store objects of type Task
    private List<Task> taskList = new ArrayList<>();

    // Class methods:
    // Add a given task to the taskList
    public void addTask(Task task) {
        taskList.add(task);
    }

    // Remove a task from the taskList given its id
    public void removeTask(int id) {
        // Safety check:
        if (id < 0 || id >= taskList.size())
            System.out.println("Invalid Task ID");
        else
            taskList.remove(id);
    }

    // Update an existing task given its id with a new title
    public void updateTask(int id, String newTitle) {
        // Safety check:
        if (id < 0 || id >= taskList.size())
            System.out.println("Invalid Task ID");
        else {
            Task taskObj = taskList.get(id);
            taskObj.setTitle(newTitle);
        }
    }

    // Mark a task as 'completed' given its id
    public void markComplete(int id) {
        // Safety check:
        if (id < 0 || id >= taskList.size())
            System.out.println("Invalid Task ID");
        else {
            Task taskObj = taskList.get(id);
            taskObj.setIsCompleted();
        }
    }

    // Retrieve each task in the taskList and display it
    public void getAllTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).getTaskDetails();
        }
    }

    // Save a copy of all the user's tasks to the save file
    public void saveData() {
        // If the taskList is empty upon saving, print an appropriate message to the
        // user
        if (taskList.isEmpty())
            System.out.println("User did not create any tasks. No available tasks to save");
        else {
            // Initialize a FileHandler object called fileHandler
            FileHandler fileHandler = new FileHandler();
            // Pass the list of task objects to the method saveToFile
            fileHandler.saveToFile(taskList);
        }
    }

    // Load the user's data from the save file
    public void loadData() {
        // Initialize a FileHandler object called fileHandler
        FileHandler fileHandler = new FileHandler();
        // Call the loadToFile method using the FileHandler object
        fileHandler.loadFromFile();
    }

}
