package com.moaz;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

    // JavaFX property fields
    private StringProperty title;
    private StringProperty description;
    private StringProperty category;
    private BooleanProperty isCompleted;

    // Default (no-argument) constructor - used for new object creations made by the
    // Jackson library during loading saved data
    public Task() {
        this("", "", "", false);
    }

    // Parameterized constructor
    public Task(String title, String description, String category, boolean isCompleted) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.isCompleted = new SimpleBooleanProperty(isCompleted);
    }

    // Overloaded constructor for new tasks (defaults to incomplete)
    public Task(String title, String description, String category) {
        this(title, description, category, false);
    }

    // Getters:
    // Create a method to return the title of a given task
    public String getTitle() {
        return title.get();
    }

    // Create a method to return the description of a given task
    public String getDescription() {
        return description.get();
    }

    // Create a method to return the category of a given task
    public String getCategory() {
        return category.get();
    }

    // Create a method to return the completion status of a given task
    public boolean getIsCompleted() {
        return isCompleted.get();
    }

    // Property Getters (needed by JavaFX TableView) for MainView table UI updates
    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public BooleanProperty isCompletedProperty() {
        return isCompleted;
    }

    // Setters:
    // Create a method to set/update the title of a given task
    public void setTitle(String title) {
        this.title.set(title);
    }

    // Create a method to set/update the description of a given task
    public void setDescription(String description) {
        this.description.set(description);
    }

    // Create a method to set the category of a given task
    public void setCategory(String category) {
        this.category.set(category);
    }

    // Create a method to set task status to completed
    public void setIsCompleted() {
        this.isCompleted.set(true);
    }

    // Display task details - for debugging
    public void getTaskDetails() {
        // Safety check:
        System.out.println("Task title: " + this.getTitle());
        System.out.println("Task description: " + this.getDescription());
        System.out.println("Task category: " + this.getCategory());
        System.out.println("Is task completed?: " + this.getIsCompleted());
        System.out.println("----------------------------------------------");
    }

}
