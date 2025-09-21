package com.moaz;

public class MainApp {
    public static void main(String[] args) {
        // Create a TaskManager object
        TaskManager taskManager = new TaskManager();

        // Create, initialize, and load two tasks using the taskmanager
        taskManager.addTask(new Task(0, "Work on Java Project", "Finish working on the app core login", "Work"));
        taskManager.addTask(new Task(1, "Become a better person", "Pray", "Religion"));

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

        // Save the user's task data in an external save file
        taskManager.saveData();

        // Load the user's data from the save file
        taskManager.loadData();

    }
}
