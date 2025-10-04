package com.moaz.taskmanager;

import com.moaz.taskmanager.controller.*;

import javafx.application.Application; // Import the JavaFX Application class
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage; // Import the JavaFX Stage class
import javafx.scene.Parent;
import javafx.scene.Scene; // Import the JavaFX Scene class

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/moaz/taskmanager/view/mainView.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();

        Scene scene = new Scene(root, 800, 600);

        // Register the scene for automatic theme updates
        ThemeController.registerScene(scene);

        stage.setScene(scene);
        stage.show();

        // Link the save method to be called before the app closes
        stage.setOnCloseRequest(event -> {
            // Call the save method when the window is closing
            controller.getTaskServices().saveData();
        });
    }
}
