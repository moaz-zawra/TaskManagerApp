package com.moaz.taskmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;

public class ThemeController {
    // Dark Mode CSS
    private static final String DARK_MODE = ThemeController.class
            .getResource("/com/moaz/taskmanager/view/dark-mode.css").toExternalForm();

    // Light Mode CSS
    private static final String LIGHT_MODE = ThemeController.class
            .getResource("/com/moaz/taskmanager/view/light-mode.css").toExternalForm();

    private static final List<Scene> registeredScenes = new ArrayList<>();

    // Set the initial state of dark mode to false
    private static boolean darkModeEnabled = SettingsWindowController.loadDarkMode();

    // Register a scene so it updates when the theme changes
    public static void registerScene(Scene scene) {
        registeredScenes.add(scene);
        applyTheme(scene); // If dark mode is enabled, apply theme to scene
    }

    // Set dark mode
    public static void setDarkMode(boolean enable) {
        darkModeEnabled = enable;
        registeredScenes.forEach(ThemeController::applyTheme);
    }

    // Apply theme to scene(s)
    private static void applyTheme(Scene scene) {
        // Clear the scene of any previously applied stylesheets (if any)
        scene.getStylesheets().clear();

        // If dark mode toggle is enabled, apply dark mode
        if (darkModeEnabled)
            scene.getStylesheets().add(DARK_MODE);
        // Else, apply light mode
        else
            scene.getStylesheets().add(LIGHT_MODE);
    }

    // Getter for dark mode status
    public static boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }
}
