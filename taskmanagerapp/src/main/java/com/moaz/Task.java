package com.moaz;

public class Task {

    // Attributes
    private int id;
    private String title;
    private String description;
    private String category;
    private boolean isCompleted;

    // Default (no-argument) constructor - used for new object creations made by the
    // Jackson library during loading saved data
    public Task() {
    }

    // Parameterized constructor
    public Task(int id, String title, String description, String category) {
        this.id = id; // Auto increment the id for each created task
        this.title = title;
        this.description = description;
        this.category = category;
        this.isCompleted = false;
    }

    // Getters:
    // Create a method to return the id of a given task
    public int getId() {
        return this.id;
    }

    // Create a method to return the title of a given task
    public String getTitle() {
        return this.title;
    }

    // Create a method to return the description of a given task
    public String getDescription() {
        return this.description;
    }

    // Create a method to return the category of a given task
    public String getCategory() {
        return this.category;
    }

    // Create a method to return the completion status of a given task
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    // Create a method to display full details for a specific task
    public void getTaskDetails() {
        // Safety check:
        System.out.println("Task id: " + this.getId());
        System.out.println("Task title: " + this.getTitle());
        System.out.println("Task description: " + this.getDescription());
        System.out.println("Task category: " + this.getCategory());
        System.out.println("Is task completed?: " + this.getIsCompleted());
        System.out.println("----------------------------------------------");
    }

    // Setters:

    // Create a method to set the id of a given task
    public void setId(int id) {
        this.id = id;
    }

    // Create a method to set/update the title of a given task
    public void setTitle(String title) {
        this.title = title;
    }

    // Create a method to set/update the description of a given task
    public void setDescription(String description) {
        this.description = description;
    }

    // Create a method to set the category of a given task
    public void setCategory(String category) {
        this.category = category;
    }

    // Create a method to set task status to completed
    public void setIsCompleted() {
        this.isCompleted = true;
    }

}
