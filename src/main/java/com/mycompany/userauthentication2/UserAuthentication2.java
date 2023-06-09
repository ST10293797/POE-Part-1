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
                    "Choose an option: \n1. Add Tasks \n2. Show Report \n3. Quit ");

            switch (choice) {
                case "1":
                    addTasks();
                    break;
                case "2":
                    showReport();
                    break;
                case "3":
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

            // Create a Task object with the provided details
            Task task = new Task(taskID, taskName, taskDescription, duration, developerDetails);

            // Add the task to the tasks list
            tasks.add(task);

            // Update the task status
            String taskStatus = selectTaskStatus();

            // Display a confirmation message for each task added
            JOptionPane.showMessageDialog(null, "Task " + i + " added successfully. Task ID: " + taskID);

            // Accumulate the duration for the total hours
            totalHours += Integer.parseInt(duration);
        }
    }

    public static void showReport() {
        // Check if any tasks have been added
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Coming soon.");
            return; // Exit the method if no tasks are available
        }

        JOptionPane.showMessageDialog(null, "Task Report");
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Task Report:\n");

        // Iterate over the tasks and generate the report
        for (Task task : tasks) {
            String taskID = task.getTaskID();
            String taskName = task.getTaskName();
            String taskDescription = task.getTaskDescription();
            String duration = task.getDuration();
            String developerDetails = task.getDeveloperDetails();

            // Update the task status
            String taskStatus = selectTaskStatus();
            String i = null;
            
            // Display a confirmation message for each task added
        JOptionPane.showMessageDialog(null, "Task " + i + " added successfully. Task ID: " + taskID);
        JOptionPane.showMessageDialog(null, "Task successfully captured"); // Display additional message
            
            reportBuilder.append("Task Status: ").append(taskStatus).append("\n");
            reportBuilder.append("Developer Details: ").append(developerDetails).append("\n");
            reportBuilder.append("Task ID: ").append(taskID).append("\n");
            reportBuilder.append("Task Name: ").append(taskName).append("\n");
            reportBuilder.append("Task Description: ").append(taskDescription).append("\n");
            reportBuilder.append("Duration: ").append(duration).append(" hours").append("\n");
            reportBuilder.append("------------------------\n");
        }

        JOptionPane.showMessageDialog(null, reportBuilder.toString());

        // Display the total number of hours across all tasks
        JOptionPane.showMessageDialog(null, "Total Hours: " + totalHours + " hours");
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

