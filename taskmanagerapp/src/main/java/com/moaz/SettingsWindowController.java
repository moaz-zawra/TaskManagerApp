package com.moaz;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class SettingsWindowController {
    // FXML variable for the CheckBox
    @FXML
    private CheckBox darkModeCheckBox;

    // Stage object representing the settings window
    private Stage settingStage;

    // Set the stage for the settings window
    public void setSettingStage(Stage settingStage) {
        this.settingStage = settingStage;
    }

    @FXML
    private void handleOk() {
        settingStage.close();
    }

    @FXML
    private void handleCancel() {
        settingStage.close();
    }
}
