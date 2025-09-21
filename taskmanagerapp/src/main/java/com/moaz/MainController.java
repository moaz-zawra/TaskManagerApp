package com.moaz;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    private Button addTaskButton, editTaskButton, deleteTaskButton;

    // Backend data
    private final TaskManager taskManager = new TaskManager();
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    // Initialization
    @FXML
    public void initialization() {
        // Link columns to task fields
    }

}
