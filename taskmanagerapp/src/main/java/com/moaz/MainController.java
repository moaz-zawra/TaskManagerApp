package com.moaz;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    // Observable list for tasks - for dynamic task data display in UI
    private ObservableList<Task> taskList = FXCollections.observableArrayList();
    // Create a TaskManager object
    private TaskManager taskManager = new TaskManager();

    // Initialization
    @FXML
    public void initialize() {
        // Link columns to task fields
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(taskList.indexOf(cellData.getValue())));
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        completedColumn.setCellValueFactory(cellData -> cellData.getValue().isCompletedProperty());

        // Bind the observable list to the table
        taskTable.setItems(taskList);

        // Load some demo data
        loadSampleData();
    }

    private void loadSampleData() {
        // Create, initialize, and load two tasks using the taskmanager
        taskManager.addTask(new Task("Work on Java Project", "Finish working on the app core login", "Work"));
        taskManager.addTask(new Task("Become a better person", "Pray", "Religion"));

        taskList.addAll(taskManager.getAllTasks());
    }

    @FXML
    private void onAddTask() {
        System.out.println("Add Task button clicked!");
        // Later: will open a dialog box for task creation
    }

    @FXML
    private void onEditTask() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null)
            System.out.println("Editing task: " + selected.getTitle());
        else
            showAlert("No task selected", "Please select a task to edit.");
    }

    @FXML
    private void onDeleteTask() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            taskManager.removeTask(selected);
            taskList.remove(selected);
        } else
            showAlert("No task selected", "Please select a task to delete.");
    }

    @FXML
    private void onCompleteTask() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            taskManager.markComplete(selected);
        } else
            showAlert("No task selected", "Please select a task to mark as complete.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
