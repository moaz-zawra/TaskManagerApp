package com.moaz;

import java.io.IOException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    // Observable list for tasks - for dynamic task data display in UI
    private ObservableList<Task> taskList = FXCollections.observableArrayList();
    // Create a TaskManager object
    private TaskManager taskManager = new TaskManager();
    // Variable to keep track of completed tasks
    private int completedTasks = 0;

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

        // Save sample demo data
        saveSampleData();

        // Load saved user data, if any, through the taskmanager and then add it to the
        // ObservableList
        taskList.addAll(taskManager.loadData());

        // Display total tasks through the totalTasksLabel
        updateTotalTasks();

        // Display total completed tasks through the completedTasksLabel
        updateCompletedTasks();
    }

    private void saveSampleData() {
        // Create, initialize, and load two tasks using the taskmanager
        taskManager.addTask(new Task("Work on Java Project", "Finish working on the app core login", "Work"));
        taskManager.addTask(new Task("Become a better person", "Pray", "Religion"));

        taskManager.saveData();
    }

    @FXML
    private void onAddTask() {
        // Set the value of task as null to help the system identify its a new task
        Task newTask = new Task();
        // Open a dialog box for task creation
        boolean saveClicked = showTaskDialog(newTask);
        if (saveClicked) {
            taskManager.addTask(newTask);
            taskList.add(newTask);
            updateTotalTasks();
        }
        System.out.println("Add Task button clicked!");
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
            updateTotalTasks();
        } else
            showAlert("No task selected", "Please select a task to delete.");
    }

    @FXML
    private void onCompleteTask() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!selected.getIsCompleted()) {
                taskManager.markComplete(selected);
                completedTasks++;
                updateCompletedTasks();
            } else
                showAlert("Operation failed", "Please select an incomplete task to mark as complete");
        } else
            showAlert("No task selected", "Please select a task to mark as complete.");
    }

    private void updateTotalTasks() {
        totalTasksLabel.setText("Total: " + taskList.size());
    }

    private void updateCompletedTasks() {
        completedTasksLabel.setText("Completed: " + completedTasks);
    }

    // Implement a method to show the dialog box
    private boolean showTaskDialog(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("taskDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            // Check if we are adding or editing tasks
            dialogStage.setTitle(task.getTitle() == "" ? "Add Task" : "Edit Task");

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(taskTable.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
