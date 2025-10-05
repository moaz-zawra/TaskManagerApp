package com.moaz.taskmanager.controller;

import java.util.prefs.Preferences;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class SettingsWindowController {

    // FXML variable for the ToggleButton
    @FXML
    private ToggleButton themeToggle;

    @FXML
    public void initialize() {
        // Set initial state for the theme toggle
        themeToggle.setSelected(ThemeController.isDarkModeEnabled());

        // Set theme icon depending if dark mode is enabled
        if (ThemeController.isDarkModeEnabled())
            themeToggle.setText("🌙"); // dark mode icon
        else
            themeToggle.setText("☀"); // light mode icon

        // Listen for toggle changes
        themeToggle.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            ThemeController.setDarkMode(isSelected);
        });
    }

    // Stage object representing the settings window
    private Stage settingStage;

    // Set the stage for the settings window
    public void setSettingStage(Stage settingStage) {
        this.settingStage = settingStage;
    }

    // Create a Preferences object to handle user settings
    private static final Preferences userPrefs = Preferences.userNodeForPackage(SettingsWindowController.class);

    // Create a method to save dark mode settings
    public static void saveDarkMode(boolean enabled) {
        userPrefs.putBoolean("darkMode", enabled);
    }

    // Create a method to load dark mode settings
    public static boolean loadDarkMode() {
        return userPrefs.getBoolean("darkMode", false); // Default: false
    }

    @FXML
    private void changeThemeIcon() {
        // Set theme icon depending if dark mode is enabled
        if (ThemeController.isDarkModeEnabled())
            themeToggle.setText("🌙"); // dark mode icon
        else
            themeToggle.setText("☀"); // light mode icon
    }

    @FXML
    private void handleOk() {
        // Save the user's theme selection before closing the settings window
        saveDarkMode(ThemeController.isDarkModeEnabled());
        settingStage.close();
    }

    @FXML
    private void handleCancel() {
        // If the current theme is the same as the last saved theme, no change occurred
        if (ThemeController.isDarkModeEnabled() == loadDarkMode())
            settingStage.close();
        else {
            // Revert the theme on cancel and close the dialog
            ThemeController.setDarkMode(!ThemeController.isDarkModeEnabled());
            settingStage.close();
        }
    }
}
