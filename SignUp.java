package Parking_Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SignUp {

    static LinkedList<String> Email = new LinkedList<String>();
    static LinkedList<String> Password = new LinkedList<String>();
    static LinkedList<String> Username = new LinkedList<String>();

    static LinkedList<User> users = new LinkedList<>(); // Store user objects

    public static void handleSignUp() {
        // Membuat dialog untuk input sign-in
        JDialog signInDialog = new JDialog((Frame) null, "Sign Up", true);
        signInDialog.setSize(400, 300);
        signInDialog.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel emailLabel = new JLabel("Enter Your Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter Your Password:");
        JPasswordField passwordField = new JPasswordField();
        JLabel usernameLabel = new JLabel("Enter Your Username:");
        JTextField usernameField = new JTextField();
        JLabel userTypeLabel = new JLabel("Enter Your UserType (Regular or Premium):");
        JTextField userTypeField = new JTextField();
        JButton signInButton = new JButton("Sign Up");

        signInDialog.add(emailLabel);
        signInDialog.add(emailField);
        signInDialog.add(passwordLabel);
        signInDialog.add(passwordField);
        signInDialog.add(usernameLabel);
        signInDialog.add(usernameField);
        signInDialog.add(userTypeLabel);
        signInDialog.add(userTypeField);
        signInDialog.add(new JLabel()); // Spacer
        signInDialog.add(signInButton);

        signInButton.addActionListener(e -> {
            try {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String username = usernameField.getText();
                String userType = userTypeField.getText();

                // default balance
                double initialBalance = 50;

                UserActions newUser;
                if (userType.equalsIgnoreCase("Regular")) {
                    newUser = new RegularUser(email, password, username, initialBalance);
                } else if (userType.equalsIgnoreCase("Premium")) {
                    newUser = new PremiumUser(email, password, username, initialBalance);
                } else {
                    throw new IllegalArgumentException("Invalid user type.");
                }

                users.add((User) newUser);

                Email.add(email);
                Password.add(password);
                Username.add(username); // adding the acc info inside each linked list

                JOptionPane.showMessageDialog(signInDialog, "You have successfully created an account!\n" +
                        "Email: " + email + "\nPassword: " + password + "\nUsername: " + username +
                        "\nBalance: $" + initialBalance + "\nUserType: " + userType + "\nPlease proceed to the login option.");

                signInDialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(signInDialog, "An unexpected error occurred: " + ex.getMessage());
            }
        });

        signInDialog.setLocationRelativeTo(null);
        signInDialog.setVisible(true);
    }

    public static boolean verifyCredentials(String email, String password) {
        // Check if the entered email and password match the stored data
        for (int i = 0; i < Email.size(); i++) {
            if (email.equals(Email.get(i)) && password.equals(Password.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static Double getUserBalanceByUsername(String username) {
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                return user.getBalance();
            }
        }
        return null; // Return null if user not found
    }

    public static void updateUserBalance(String username, double newBalance) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setBalance(newBalance);
                System.out.println("Updated balance for " + username + ": $" + newBalance);
                return;
            }
        }
        System.out.println("User not found.");
    }
}
