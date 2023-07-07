/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



package com.mycompany.userauthentication2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

public class UserAuthentication2 {
    private static final HashMap<String, String> users = new HashMap<>();

    private static final HashMap<String, String> usersFirstName = new HashMap<>();
    private static final HashMap<String, String> usersLastName = new HashMap<>();
    private static int taskNumber = 0; // Counter for task number
    private static int totalHours = 0; // Accumulator for total hours
    private static final List<Task> tasks = new ArrayList<>(); // List to store tasks
    private static final List<String> developers = new ArrayList<>();
    private static final List<String> taskNames = new ArrayList<>();
    private static final List<String> taskIDs = new ArrayList<>();
    private static final List<String> taskDurations = new ArrayList<>();
    private static final List<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        boolean isRunning = true;
        boolean isLoggedIn = false; // Track login status

        while (isRunning) {
            String choice = JOptionPane.showInputDialog(null,
                    "Choose an option: \n1. Register \n2. Login \n3.Exit ");

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    isLoggedIn = login(); // Set login status based on login result
                    if (isLoggedIn) {
                        // Call the method to handle EasyKanban functionality
                        easyKanban();
                    }
                    break;
                case "3":
                    isRunning = false;
                    break;
                default:
                    JOptionPane.showInputDialog("Invalid choice.");
                    break;
            }
        }
    }

    public static void register() {
        String username = JOptionPane.showInputDialog("Enter a username");
        String password = JOptionPane.showInputDialog("Enter a password: \n That consists of\n • At least 8 characters long\n" +
                "• Contains a capital letter\n" +
                "• Contains a number\n" +
                "• Contains a special character :");
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");

        // Check if the username already exists
        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different username.");
            return; // Exit the method
        }

        // Add the new user to the users HashMap
        users.put(username, password);

        // Add the first name and last name to the respective HashMaps
        usersFirstName.put(username, firstName);
        usersLastName.put(username, lastName);

        JOptionPane.showMessageDialog(null, "Registration successful!");
    }

    public static boolean login() {
        String username = JOptionPane.showInputDialog("Enter your username:");
        String password = JOptionPane.showInputDialog("Enter your password:");

        // Check if the username and password match
        if (users.containsKey(username) && users.get(username).equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful!");

            // Display welcome message
            String firstName = usersFirstName.get(username);
            String lastName = usersLastName.get(username);

            // Show "It is great to see you again" message
            JOptionPane.showMessageDialog(null, " Welcome, " + firstName + " " + lastName + " It is great to see you again");

            return true; // Return true if login is successful
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            return false; // Return false if login is unsuccessful
        }
    }

    public static void easyKanban() {
        boolean isRunning = true;
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
        while (isRunning) {
            String choice = JOptionPane.showInputDialog(null,
                    "Choose an option: \n1. Add Tasks \n2. Show Report \n3. Display Tasks with Status 'Done' " +
                            "\n4. Display Task with Longest Duration \n5. Search Task by Name \n6. Search Tasks by Developer " +
                            "\n7. Delete Task by Name \n8. Quit ");

            switch (choice) {
                case "1":
                    addTasks();
                    break;
                case "2":
                    showReport();
                    break;
                case "3":
                    displayTasksWithStatusDone();
                    break;
                case "4":
                    displayTaskWithLongestDuration();
                    break;
                case "5":
                    String taskNameToSearch = JOptionPane.showInputDialog(null, "Enter the Task Name to search:");
                    searchTaskByName(taskNameToSearch);
                    break;
                case "6":
                    String developerToSearch = JOptionPane.showInputDialog(null, "Enter the Developer to search:");
                    searchTasksByDeveloper(developerToSearch);
                    break;
                case "7":
                    String taskNameToDelete = JOptionPane.showInputDialog(null, "Enter the Task Name to delete:");
                    deleteTaskByName(taskNameToDelete);
                    break;
                case "8":
                    isRunning = false;
                    JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
                    break;
            }
        }
    }

    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of tasks to add:"));

        for (int i = 1; i <= numTasks; i++) {
            String taskName = JOptionPane.showInputDialog(null, "Enter the name of Task " + i + ":");
            String taskDescription = JOptionPane.showInputDialog(null, "Enter the description of Task " + i + ":");

            // Check if the task description exceeds 50 characters
            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                i--; // Decrement i to repeat the current task input
                continue; // Skip to the next iteration
            }

            String taskID = null;

            // Display a confirmation message for each task added
            JOptionPane.showMessageDialog(null, "Task " + i + " successfully captured. Task ID: " + taskID);
            String duration = JOptionPane.showInputDialog(null, "Enter the duration of Task " + i + " (in hours):");
            String developerDetails = JOptionPane.showInputDialog(null, "Enter the developer details for Task " + i + ":");

            // Generate the task ID using the provided generateTaskID() method
            taskID = generateTaskID(taskName, developerDetails);

            // Add task data to the respective arrays
            taskIDs.add(taskID);
            taskNames.add(taskName);
            taskDurations.add(duration);
            developers.add(developerDetails);

            // Create a Task object with the provided details
            Task task = new Task(taskID, taskName, taskDescription, duration, developerDetails);

            // Add the task to the tasks list
            tasks.add(task);

            // Update the task status
            String taskStatus = selectTaskStatus();
            taskStatuses.add(taskStatus);

            // Display a confirmation message for each task added
            JOptionPane.showMessageDialog(null, "Task " + i + " added successfully. Task ID: " + taskID);

            // Accumulate the duration for the total hours
            
           totalHours += Integer.parseInt(duration);
        }
    }
    
   
    
        public static void showReport() 
        
        {
            if (taskIDs.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tasks have been added yet.");
                return;
            }

            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Task Report:\n");

            for (int i = 0; i < taskIDs.size(); i++) {
                String taskID = taskIDs.get(i);
                String taskName = taskNames.get(i);
                String taskDescription = tasks.get(i).getTaskDescription();
                String duration = taskDurations.get(i);
                String developerDetails = developers.get(i);
                String taskStatus = taskStatuses.get(i);

                // Append task details to the report
                reportBuilder.append("Task ID: ").append(taskID).append("\n");
                reportBuilder.append("Task Name: ").append(taskName).append("\n");
                reportBuilder.append("Task Description: ").append(taskDescription).append("\n");
                reportBuilder.append("Duration: ").append(duration).append(" hours").append("\n");
                reportBuilder.append("Developer: ").append(developerDetails).append("\n");
                reportBuilder.append("Status: ").append(taskStatus).append("\n");
                reportBuilder.append("------------------------\n");
            }

            reportBuilder.append("Total Hours: ").append(totalHours).append(" hours");

            JOptionPane.showMessageDialog(null, reportBuilder.toString());
        }

        private static void displayTasksWithStatusDone() {
            boolean tasksFound = false;

            for (int i = 0; i < tasks.size(); i++) {
                String taskStatus = taskStatuses.get(i);
                if (taskStatus.equalsIgnoreCase("Done")) {
                    tasksFound = true;
                    String developer = developers.get(i);
                    String taskName = taskNames.get(i);
                    String duration = taskDurations.get(i);
                    JOptionPane.showMessageDialog(null, "Developer: " + developer +
                            "\nTask Name: " + taskName +
                            "\nTask Duration: " + duration + " hours");
                }
            }

            if (!tasksFound) {
                JOptionPane.showMessageDialog(null, "No tasks with the status of 'Done' found.");
            }
        }

        private static void displayTaskWithLongestDuration() {
            int longestDurationIndex = -1;
            int maxDuration = -1;

            for (int i = 0; i < taskDurations.size(); i++) {
                int duration = Integer.parseInt(taskDurations.get(i));
                if (duration > maxDuration) {
                    maxDuration = duration;
                    longestDurationIndex = i;
                }
            }

            if (longestDurationIndex != -1) {
                String developer = developers.get(longestDurationIndex);
                String duration = taskDurations.get(longestDurationIndex);
                JOptionPane.showMessageDialog(null, "Developer: " + developer +
                        "\nDuration: " + duration + " hours");
            } else {
                JOptionPane.showMessageDialog(null, "No tasks found.");
            }
        }

        private static void searchTaskByName(String taskNameToSearch) {
            boolean taskFound = false;

            for (int i = 0; i < taskNames.size(); i++) {
                String taskName = taskNames.get(i);
                if (taskName.equalsIgnoreCase(taskNameToSearch)) {
                    taskFound = true;
                    String developer = developers.get(i);
                    String taskStatus = taskStatuses.get(i);
                    JOptionPane.showMessageDialog(null, "Task Name: " + taskName +
                            "\nDeveloper: " + developer +
                            "\nTask Status: " + taskStatus);
                }
            }

            if (!taskFound) {
                JOptionPane.showMessageDialog(null, "No task found with the given Task Name.");
            }
        }

        public static void searchTasksByDeveloper(String developerToSearch) {
            boolean tasksFound = false;

            for (int i = 0; i < developers.size(); i++) {
                String developer = developers.get(i);
                if (developer.equalsIgnoreCase(developerToSearch)) {
                    tasksFound = true;
                    String taskName = taskNames.get(i);
                    String taskStatus = taskStatuses.get(i);
                    JOptionPane.showMessageDialog(null, "Task Name: " + taskName +
                            "\nTask Status: " + taskStatus);
                }
            }

            if (!tasksFound) {
                JOptionPane.showMessageDialog(null, "No tasks found assigned to the given developer.");
            }
        }

        private static void deleteTaskByName(String taskName) {
        if (taskIDs.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }

        int indexToDelete = -1;

        for (int i = 0; i < taskIDs.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            // Update the total hours before deleting the task
            int hoursToRemove = Integer.parseInt(taskDurations.get(indexToDelete));
            totalHours -= hoursToRemove;

            // Remove the task and its associated data
            taskIDs.remove(indexToDelete);
            taskNames.remove(indexToDelete);
            taskDurations.remove(indexToDelete);
            developers.remove(indexToDelete);
            taskStatuses.remove(indexToDelete);

            JOptionPane.showMessageDialog(null, "Task '" + taskName + "' deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Task '" + taskName + "' not found.");
        }
    }

        private static String generateTaskID(String taskName, String developerDetails) {
            String taskID = taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" +
                    developerDetails.substring(developerDetails.length() - 3).toUpperCase();
            taskNumber++; // Increment the taskNumber for each new task
            return taskID;
        }

        private static String selectTaskStatus() {
            String[] statusOptions = {"To Do", "Done", "Doing"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select the task status:", "Task Status",
                    JOptionPane.PLAIN_MESSAGE, null, statusOptions, "To Do");
            return taskStatus;
        }

    public Object getDevelopers() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public Object displayDeveloperAndDurationForTaskWithLongestDuration() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public Object searchTasksByTaskName(String taskName) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public Object deleteTaskFromArray(String taskName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object displayReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

        private static class Task {
            private String taskID;
            private String taskName;
            private String taskDescription;
            private String duration;
            private String developerDetails;

            public Task(String taskID, String taskName, String taskDescription, String duration, String developerDetails) {
                this.taskID = taskID;
                this.taskName = taskName;
                this.taskDescription = taskDescription;
                this.duration = duration;
                this.developerDetails = developerDetails;
            }

            public String getTaskID() {
                return taskID;
            }

            public String getTaskName() {
                return taskName;
            }

            public String getTaskDescription() {
                return taskDescription;
            }

            public String getDuration() {
                return duration;
            }

            public String getDeveloperDetails() {
                return developerDetails;
            }
        }
    }
