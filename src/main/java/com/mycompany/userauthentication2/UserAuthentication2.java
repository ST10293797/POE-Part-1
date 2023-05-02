/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.userauthentication2;

import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class UserAuthentication2 {
    private static final HashMap<String, String> users = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final HashMap<String, String> usersFirstName = new HashMap<>();
    private static final HashMap<String, String> usersLastName = new HashMap<>();

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Choose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void register() {
        boolean isCheckUserName = false;
        boolean isCheckPasswordComplexity = false;
        String username = "";
        String password = "";
        String firstName = "";
        String lastName = ""; 

        while (!isCheckUserName) {
            System.out.println("Enter a username that contains an underscore and is no more than 5 characters long:");
            username = scanner.next();
            if (username.length() > 5 || !username.contains("_")) {
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            } else {
                isCheckUserName = true;
                System.out.println("Username successfully captured");
            }
        }

        while (!isCheckPasswordComplexity) {
            System.out.println("Enter a password that is at least 8 characters long, contains a capital letter, contains a number, contains a special character:");
            password = scanner.next();
            if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")) {
                System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            } else {
                isCheckPasswordComplexity = true;
                System.out.println("Password successfully captured");
            }
        }

        System.out.println("Enter your first name");
        firstName = scanner.next();
        System.out.println("Enter your last name");
        lastName = scanner.next();
        users.put(username, password);
        usersFirstName.put(username, firstName);
        usersLastName.put(username, lastName);
        System.out.println("Registration successful.");
    }

    public static void login() {
    System.out.println("Enter your username:");
    String username = scanner.next();
    System.out.println("Enter your password:");
    String password = scanner.next();
    
    if (users.containsKey(username)) {
        if (users.get(username).equals(password)) {
            String firstName = usersFirstName.get(username);
            String lastName = usersLastName.get(username);
            System.out.println("Welcome " + firstName + " " + lastName + " It is great to see you again.");
        } else {
            System.out.println("Incorrect password.");
        }
    } else {
        System.out.println("User not found.");
    }
}
}






