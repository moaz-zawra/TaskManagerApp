# TaskNest - A Task Manager Application
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/moaz-zawra/TaskManagerApp)

TaskNest is a simple, intuitive desktop task management application built with Java and JavaFX. It provides essential features for organizing your tasks, with a clean user interface that supports both light and dark themes. Your tasks are automatically saved and loaded, ensuring your data is always preserved between sessions.

## 🚀 Features

*   **Task Management**: Add, edit, and delete tasks with ease.
*   **Task Properties**: Each task includes a title, description, and category.
*   **Status Tracking**: Mark tasks as complete and track your progress.
*   **Persistent Storage**: Tasks are automatically saved to a `tasks.json` file upon closing the application and are loaded at startup.
*   **Cross-Platform Data**: User data is stored in the appropriate native application data directory for Windows, macOS, and Linux.
*   **Theming**: Switch between a visually-pleasing light mode and a comfortable dark mode. Your theme preference is saved for future sessions.
*   **Dashboard**: At-a-glance statistics for total and completed tasks.
*   **Intuitive UI**: A clean, table-based view of all your tasks.

## 🛠️ Technologies Used

*   **Java 25**: Core application language.
*   **JavaFX 25**: Framework for the graphical user interface.
*   **Maven**: For project build management and dependencies.
*   **Jackson**: For serializing and deserializing task data to/from JSON format.
*   **Java Preferences API**: For storing user's theme preferences between sessions.
*   **CSS**: For customizing and implementing light and dark modes.

## ⚙️ Getting Started

### Prerequisites

*   Java Development Kit (JDK) 25 or newer.
*   Apache Maven 3.6 or newer.
*   JavaFX SDK 25 or newer.

### Running the Application

A pre-packaged, executable JAR is available in the `executables` directory. To run the application, navigate to the root of the repository and execute the following command:

```bash
java -jar executables/taskmanager-shaded.jar
```

### Building from Source

You can also build the application from the source code.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/moaz-zawra/TaskManagerApp.git
    cd TaskManagerApp
    ```

2.  **Build with Maven:**
    Run the following command to compile the code and package it into an executable JAR file.
    ```bash
    mvn clean package
    ```
    This command creates two artifacts:
    *   `target/taskmanager-shaded.jar`: The runnable JAR file with all dependencies included.
    *   `executables/taskmanager-shaded.jar`: A copy of the same JAR, placed for convenience.

3.  **Run the built application:**
    ```bash
    java -jar target/taskmanager-shaded.jar
    ```

## 📂 Project Structure

The project follows a standard Maven layout with an MVC-like pattern for the application's source code.

```
.
├── executables/
│   └── taskmanager-shaded.jar      # Pre-packaged runnable application
├── src/main/java/com/moaz/taskmanager/
├── MainApp.java                    # Application entry point
├── controller/                     # UI controllers
│   ├── MainController.java
│   ├── TaskDialogController.java
│   ├── SettingsWindowController.java
│   └── ThemeController.java
├── model/                          # Data models and business logic
│   ├── Task.java
│   ├── TaskServices.java
│   └── FileHandler.java
└── utils/                          # Utility classes
│    └── DataUtils.java
│
├── src/main/resources/com/moaz/taskmanager/view/
├── mainView.fxml                   # Main window layout
├── taskDialog.fxml                 # Task dialog layout
├── settingsWindow.fxml             # Settings window layout
├── dark-mode.css                   # Dark theme stylesheet
└── light-mode.css                  # Light theme stylesheet
│
└── pom.xml                         # Maven project configuration
```

## 📖 How It Works

### Data Persistence

The application ensures that your tasks are not lost when you close it.

*   **Saving**: When the main window is closed, all current tasks are serialized into a JSON format and saved to a `tasks.json` file.
*   **Loading**: On startup, the application checks for the `tasks.json` file and deserializes its content back into `Task` objects, repopulating the task list.
*   **File Location**: The `DataUtils` class determines the correct, OS-specific directory for storing `tasks.json`:
    *   **Windows**: `%APPDATA%\TaskManager`
    *   **macOS**: `~/Library/Application Support/TaskManager`
    *   **Linux/Unix**: `~/.taskmanager`

### Theming

TaskNest offers both a light and a dark theme for user comfort.

*   **Theme Switching**: The theme can be changed in the Settings window (`⚙️`). The selection is applied instantly across all open windows of the application.
*   **CSS Stylesheets**: The `ThemeController` applies either `light-mode.css` or `dark-mode.css` to the active scenes based on the user's selection.
*   **Preference Storage**: The chosen theme (light or dark) is saved using the `java.util.prefs.Preferences` API, so your selection persists across application restarts.

### UI and Logic

*   **Views**: The UI is defined using FXML files, which separates the layout from the application logic.
*   **Controllers**: Each FXML view has a corresponding controller class that manages user input and updates the view.
*   **Model**: The `Task` class uses JavaFX Properties (`StringProperty`, `BooleanProperty`) to allow the UI to automatically update when the underlying data changes. The `TaskServices` class acts as a facade for managing the collection of tasks.
    ```

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
