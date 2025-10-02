package com.moaz.taskmanager.controller;

import java.util.prefs.Preferences;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class SettingsWindowController {

    // FXML variable for the CheckBox
    @FXML
    private CheckBox darkModeCheckBox;

    @FXML
    public void initialize() {
        // Set initial state for the dark mode toggle
        darkModeCheckBox.setSelected(ThemeController.isDarkModeEnabled());

        // Listen for toggle changes
        darkModeCheckBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
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
