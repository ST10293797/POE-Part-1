/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.userauthentication2;

class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;

    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration,
            String taskID, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskID = taskID;
        this.taskStatus = taskStatus;
    }

    Task(String taskID, String taskName, String taskDescription, String duration, String developerDetails) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    public String createTaskID() {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":"
                + developerDetails.substring(developerDetails.length() - 3).toUpperCase();
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\nDeveloper Details: " + developerDetails + "\nTask Number: "
                + taskNumber + "\nTask Name: " + taskName + "\nTask Description: " + taskDescription + "\nTask ID: "
                + taskID + "\nDuration: " + taskDuration + " hours";
    }

    public static int returnTotalHours(Task[] tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.taskDuration;
        }
        return totalHours;
    }

    AbstractStringBuilder getTaskID() {
        throw new UnsupportedOperationException("Not supported yet."); 
        
    }

    AbstractStringBuilder getTaskName() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    AbstractStringBuilder getDeveloperDetails() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    String getTaskDescription() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    String getDuration() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}