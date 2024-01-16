package com.example;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserEntry3 {
    Scanner scan = new Scanner(System.in);
    boolean validEmail = false;
    String email = "";

    public void start() {
        boolean validChoice = false;

        while (!validChoice) {
            try {
                System.out.println("To register, click: 1");
                System.out.println("To log in, click: 2");
                System.out.println("To exit, click: 3");

                int choice = scan.nextInt();
                scan.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        userReg();
                        validChoice = true;
                        break;
                    case 2:
                        userLog();
                        validChoice = true;
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        validChoice = true;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scan.nextLine(); // Consume the invalid input
            }
        }
    }

    public void userEmail() {
        try {
            System.out.println("Please enter your email");
            email = scan.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Error reading email. Please try again.");
        }
    }

    public void userReg() {
        userEmail();
        try {
            System.out.println("Please enter your password");
            String password = scan.nextLine();
            Hash.registration(email, password);
        } catch (NoSuchElementException e) {
            System.out.println("Error reading password. Please try again.");
        }
    }

    public void userLog() {
        String password;
        do {
            userEmail();
            try {
                System.out.println("Please enter your password");
                password = scan.nextLine();

                if (!DataBaseConnectivity.emailExists(email)) {
                    System.out.println("The email entered doesn't exist. Register here.");
                    userReg();
                    break;
                } else if (DataBaseConnectivity.authentication(email, password)) {
                    System.out.println("Successful login");
                } else {
                    System.out.println("Wrong email or password. Try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error reading input. Please try again.");
                break;
            }
        } while (!DataBaseConnectivity.authentication(email, password));
    }
}
