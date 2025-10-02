package com.moaz.taskmanager.controller;

import com.moaz.taskmanager.model.*;

import java.io.IOException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    // FXML variable for the TableView representing the entire task table
    @FXML
    private TableView<Task> taskTable;

    // Table Columns:
    @FXML
    private TableColumn<Task, Integer> idColumn;
    @FXML
    private TableColumn<Task, String> titleColumn;
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    @FXML
    private TableColumn<Task, String> categoryColumn;
    @FXML
    private TableColumn<Task, Boolean> completedColumn;

    // FXML variable for the ListView representing categories
    @FXML
    private ListView<Task> categoryListView;

    // FXML variable for the stats labels
    @FXML
    private Label totalTasksLabel, completedTasksLabel, lastSavedLabel;

    // FXML variable for the action buttons
    @FXML
    private Button addTaskButton, editTaskButton, deleteTaskButton, completeTaskButton;

    // Create a TaskServices object
    private TaskServices taskServices = new TaskServices();

    // Initialization
    @FXML
    public void initialize() {
        // Link columns to task fields
        idColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(taskServices.getTaskList().indexOf(cellData.getValue())));
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        completedColumn.setCellValueFactory(cellData -> cellData.getValue().isCompletedProperty());
        completedColumn.setCellFactory(tc -> new CheckBoxTableCell<>());

        // Load saved user data, if any, through the taskServices and then add it to the
        // ObservableList
        taskServices.getTaskList().addAll(taskServices.loadData());

        // Bind the observable list to the table
        taskTable.setItems(taskServices.getTaskList());

        // Display total tasks through the totalTasksLabel
        updateTotalTasks();

        // Display total completed tasks through the completedTasksLabel
        updateCompletedTasks();
    }

    @FXML
    private void onAddTask() {
        // Set the value of task as null to help the system identify its a new task
        Task newTask = new Task();
        // Open a dialog box for task creation
        boolean saveClicked = showTaskDialog(newTask);
        if (saveClicked) {
            taskServices.addTask(newTask);
            updateTotalTasks();
        }
    }

    @FXML
    private void onEditTask() {
        // Store the user selected task to be edited
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected == null)
            showAlert("No task selected", "Please select a task to edit.");
        else {
            // Open a dialog box for task edit
            boolean saveClicked = showTaskDialog(selected);
        }
    }

    @FXML
    private void onDeleteTask() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            taskServices.removeTask(selected);
            updateTotalTasks();
        } else
            showAlert("No task selected", "Please select a task to delete.");
    }

    @FXML
    private void onCompleteTask() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!selected.getIsCompleted()) {
                taskServices.markComplete(selected);
                updateCompletedTasks();
            } else
                showAlert("Invalid selection", "Please select an incomplete task to mark as complete");
        } else
            showAlert("No task selected", "Please select a task to mark as complete.");
    }

    private void updateTotalTasks() {
        totalTasksLabel.setText("Total: " + taskServices.getTaskList().size());
    }

    private void updateCompletedTasks() {
        // Variable to keep track of completed tasks
        int completedTasks = 0;

        // Iterate over all tasks in TaskList
        for (Task task : taskServices.getTaskList()) {
            if (task.getIsCompleted())
                completedTasks++; // Increment the counter for each completed task
        }
        // Dynamically update the completed tasks label
        completedTasksLabel.setText("Completed: " + completedTasks);
    }

    // Implement a method to show the dialog box
    private boolean showTaskDialog(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/moaz/taskmanager/view/taskDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            // Check if we are adding or editing tasks
            dialogStage.setTitle(task.getTitle() == null ? "Add Task" : "Edit Task");

            Scene scene = new Scene(root);

            // Register the scene for automatic theme updates
            ThemeController.registerScene(scene);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(taskTable.getScene().getWindow());
            dialogStage.setScene(scene);

            TaskDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Only set the task if we are editing
            if (task != null)
                controller.setTask(task);

            dialogStage.showAndWait();
            return controller.isSaveClicked();

        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            return false;
        }
    }

    // Implement a method to show the settings window
    @FXML
    private void showSettingsWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/moaz/taskmanager/view/settingsWindow.fxml"));
            Parent root = loader.load();

            Stage settingStage = new Stage();
            settingStage.setTitle("Settings");

            Scene scene = new Scene(root);

            // Register the scene for automatic theme updates
            ThemeController.registerScene(scene);

            settingStage.initModality(Modality.WINDOW_MODAL);
            settingStage.initOwner(taskTable.getScene().getWindow());
            settingStage.setScene(scene);

            SettingsWindowController controller = loader.getController();
            controller.setSettingStage(settingStage);

            settingStage.showAndWait();
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    public TaskServices getTaskServices() {
        return taskServices;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
