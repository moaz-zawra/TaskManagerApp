package com.moaz.taskmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;

public class ThemeController {
    private static final String darkMode = ThemeController.class
            .getResource("/com/moaz/taskmanager/view/dark-mode.css").toExternalForm();
    private static final List<Scene> registeredScenes = new ArrayList<>();

    // Set the initial state of dark mode to false
    private static boolean darkModeEnabled = SettingsWindowController.loadDarkMode();

    // Register a scene so it updates when the theme changes
    public static void registerScene(Scene scene) {
        registeredScenes.add(scene);
        applyTheme(scene); // If dark mode is enabled, apply theme to scene
    }

    // Toggle dark mode
    public static void setDarkMode(boolean enable) {
        darkModeEnabled = enable;
        registeredScenes.forEach(ThemeController::applyTheme);
    }

    // Apply dark mode to scene(s)
    private static void applyTheme(Scene scene) {
        // Clear the scene of any previously applied stylesheets (if any)
        scene.getStylesheets().clear();
        if (darkModeEnabled)
            scene.getStylesheets().add(darkMode);
    }

    // Getter for dark mode status
    public static boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }
}
