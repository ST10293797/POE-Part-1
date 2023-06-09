/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Task class.
 * Author: FS
 */
public class TaskUnitTest {
    Task testData = new Task();
    
    String taskName1 = "Login Feature";
    String DEVELOPER_NAME1 = "Robyn";
    String DEVELOPER_SURNAME1 = "Harrison";
    int taskDuration1 = 8;
    String description1 = "Create Login to authenticate users";
    String taskStatus1 = "To do";
    
    String taskName2 = "Add Task Feature";
    String DEVELOPER_NAME2 = "Mike";
    String DEVELOPER_SURNAME2 = "Smith";
    int taskDuration2 = 10;
    String description2 = "Create Add Task feature to add task users";
    String taskStatus2 = "Doing";
    
   
    

    /**
     * Test of checkTaskDescription method, of class Task.
     */
    @Test
    public void testCheckTaskDescription() {
        // Testing the first case with a valid description
        assertTrue(testData.checkTaskDescription(description1));
        
        String actual;
        String expected = "Task successfully captured";
        if (testData.checkTaskDescription(description1)) {
            actual = "Task successfully captured";
        } else {
            actual = "Please enter a task description.  Description should not exceed 50 characters";
        }
        
        assertEquals(expected, actual);
    }

    /**
     * Test of createTaskID method, of class Task.
     */
    @Test
    public void testCreateTaskID() {
        // Testing the first case
        String expected1 = "LO:0:SON";
        int taskNumber1 = 0;
        String actual1 = testData.createTaskID(taskName1, taskNumber1, 
DEVELOPER_NAME1, DEVELOPER_SURNAME1, taskStatus1);
        assertEquals(expected1, actual1);
    
        // Testing the second case
        String expected2 = "AD:1:ITH";
        int taskNumber2 = 1;
        String actual2 = testData.createTaskID(taskName2, taskNumber2, 
DEVELOPER_NAME2, DEVELOPER_SURNAME2, taskStatus2); 
        assertEquals(expected2, actual2);
    }

    /**
     * Test of printTaskDetails method, of class Task.
     */
    @Test
    public void testPrintTaskDetails() {
        // No test case provided for this method
    }

    /**
     * Test of returnTotalHours method, of class Task.
     */
    @Test
    public void testReturnTotalHours() {
        testData.returnTotalHours(taskDuration1);
        testData.returnTotalHours(taskDuration2);
        int expectedHours = 18;   
        int actualHours = testData.displayTotalHours();
      
        assertEquals(expectedHours, actualHours);
    }

    /**
     * Test of displayTotalHours method, of class Task.
     */
    @Test
    public void testDisplayTotalHours() {
        // No test case provided for this method
    }
    
    private static class Task {

        public Task() {
        }

        private boolean checkTaskDescription(String description1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        private String createTaskID(String taskName1, int taskNumber1, String DEVELOPER_NAME1, String DEVELOPER_SURNAME1, String taskStatus1) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        private void returnTotalHours(int taskDuration1) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        private int displayTotalHours() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }
}