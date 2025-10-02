module com.moaz.taskmanager {
    // JavaFX modules
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires java.prefs;

    // Jackson (non-modular, automatic modules)
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    // Open packages for reflection
    opens com.moaz.taskmanager to javafx.fxml;
    opens com.moaz.taskmanager.controller to javafx.fxml;
    opens com.moaz.taskmanager.model to com.fasterxml.jackson.databind;

    // Export for other modules (JavaFX runtime & future use)
    exports com.moaz.taskmanager;
    exports com.moaz.taskmanager.controller;
    exports com.moaz.taskmanager.model;
}
