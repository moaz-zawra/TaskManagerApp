package com.moaz;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

    // Standard fields for Jackson
    private String title;
    private String description;
    private String category;
    private boolean isCompleted;

    // JavaFX property fields (transient so Jackson ignores them)
    private transient StringProperty titleProperty;
    private transient StringProperty descriptionProperty;
    private transient StringProperty categoryProperty;
    private transient BooleanProperty isCompletedProperty;

    // Default (no-argument) constructor - used for new object creations made by the
    // Jackson library during the process of loading saved data
    public Task() {
        this("", "", "", false);
    }

    // Parameterized constructor
    public Task(String title, String description, String category, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.isCompleted = isCompleted;

        this.titleProperty = new SimpleStringProperty(title);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.categoryProperty = new SimpleStringProperty(category);
        this.isCompletedProperty = new SimpleBooleanProperty(isCompleted);

        // Sync JavaFX properties with standard fields
        this.titleProperty.addListener((obs, oldVal, newVal) -> this.title = newVal);
        this.descriptionProperty.addListener((obs, oldVal, newVal) -> this.description = newVal);
        this.categoryProperty.addListener((obs, oldVal, newVal) -> this.category = newVal);
        this.isCompletedProperty.addListener((obs, oldVal, newVal) -> this.isCompleted = newVal);
    }

    // Overloaded constructor for new tasks (defaults to incomplete)
    public Task(String title, String description, String category) {
        this(title, description, category, false);
    }

    // Getters for Jackson
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    // Setters for Jackson
    public void setTitle(String title) {
        this.title = title;
        if (this.titleProperty != null)
            this.titleProperty.set(title);
        else
            this.titleProperty = new SimpleStringProperty(title);
    }

    public void setDescription(String description) {
        this.description = description;
        if (this.descriptionProperty != null)
            this.descriptionProperty.set(description);
        else
            this.descriptionProperty = new SimpleStringProperty(description);
    }

    public void setCategory(String category) {
        this.category = category;
        if (this.categoryProperty != null)
            this.categoryProperty.set(category);
        else
            this.categoryProperty = new SimpleStringProperty(category);
    }

    public void setIsCompleted() {
        this.isCompleted = true;
        if (this.isCompletedProperty != null)
            this.isCompletedProperty.set(isCompleted);
        else
            this.isCompletedProperty = new SimpleBooleanProperty(isCompleted);
    }

    // JavaFx property getters (needed by JavaFX TableView) for MainView table UI
    // updates
    public StringProperty titleProperty() {
        return titleProperty;
    }

    public StringProperty descriptionProperty() {
        return descriptionProperty;
    }

    public StringProperty categoryProperty() {
        return categoryProperty;
    }

    public BooleanProperty isCompletedProperty() {
        return isCompletedProperty;
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
