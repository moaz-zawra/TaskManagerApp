package com.moaz.taskmanager.utils;

import java.io.File;

public class DataUtils {
    private static final String APP_FOLDER_NAME = "TaskManager";

    /**
     * Returns the standard folder for storing app data based on the OS:
     * - Windows: %APPDATA%\TaskManager
     * - macOS: ~/Library/Application Support/TaskManager
     * - Linux/Unix: ~/.taskmanager
     */
    public static File getAppDataFolder() {
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");
        File appDir;

        // Windows
        if (os.contains("win")) {
            String appData = System.getenv("APPDATA"); // e.g., C:\Users\Username\AppData\Roaming
            if (appData == null) {
                // fallback
                appData = userHome;
            }
            appDir = new File(appData, APP_FOLDER_NAME);
        }
        // macOS
        else if (os.contains("mac")) {
            appDir = new File(userHome + "/Library/Application Support/" + APP_FOLDER_NAME);
        }
        // Linux/Unix
        else {
            appDir = new File(userHome, "." + APP_FOLDER_NAME.toLowerCase());
        }

        if (!appDir.exists())
            appDir.mkdirs();

        return appDir;
    }

    /**
     * Returns the tasks.json file inside the native app data folder.
     */
    public static File getTasksFile() {
        return new File(getAppDataFolder(), "tasks.json");
    }
}
