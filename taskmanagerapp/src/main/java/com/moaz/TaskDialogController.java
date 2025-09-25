package com.moaz;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskDialogController {
    // FXML variable for the text fields
    @FXML
    private TextField titleField, descriptionField, categoryField;

    private Stage dialogStage; // Stage object representing the popup dialog box
    private Task task; // Task object to invoke operations from the Task class
    private boolean saveClicked = false; // Boolean variable to handle the click state of the save button

    // Set the stage for the dialog box
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Pass in the task to populate fields for editing
    public void setTask(Task task) {
        this.task = task;

        titleField.setText(task.getTitle());
        descriptionField.setText(task.getDescription());
        categoryField.setText(task.getCategory());
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        // Save user input into the task
        task.setTitle(titleField.getText());
        task.setDescription(descriptionField.getText());
        task.setCategory(categoryField.getText());
        saveClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
