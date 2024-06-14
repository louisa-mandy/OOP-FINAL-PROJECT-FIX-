package Parking_Ticket;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingGUI {

    public static void handleParking(JFrame parentFrame) {
        JDialog parkingDialog = new JDialog(parentFrame, "Parking Options", true);
        parkingDialog.setSize(400, 300);
        parkingDialog.setLayout(new GridLayout(5, 1, 10, 10));

        JButton checkBalanceButton = new JButton("Check Card Balance");
        JButton topUpButton = new JButton("Top Up Card Balance");
        JButton payParkingFeeButton = new JButton("Pay Parking Fee");
        JButton backButton = new JButton("Go to previous page");

        parkingDialog.add(checkBalanceButton);
        parkingDialog.add(topUpButton);
        parkingDialog.add(payParkingFeeButton);
        parkingDialog.add(backButton);

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckBalance(parkingDialog);
            }
        });

        topUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTopUp(parkingDialog);
            }
        });

        payParkingFeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePayParkingFee(parkingDialog);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parkingDialog.dispose();
            }
        });

        parkingDialog.setLocationRelativeTo(parentFrame);
        parkingDialog.setVisible(true);
    }

    private static void handleCheckBalance(JDialog parentDialog) {
        JDialog checkBalanceDialog = new JDialog(parentDialog, "Check Card Balance", true);
        checkBalanceDialog.setSize(300, 200);
        checkBalanceDialog.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel usernameLabel = new JLabel("Please Enter Your Username:");
        JTextField usernameField = new JTextField();
        JButton checkButton = new JButton("Check Balance");

        checkBalanceDialog.add(usernameLabel);
        checkBalanceDialog.add(usernameField);
        checkBalanceDialog.add(checkButton);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                Double balance = SignUp.getUserBalanceByUsername(username);

                if (balance != null) {
                    JOptionPane.showMessageDialog(checkBalanceDialog, username + "'s Balance: $" + balance);
                } else {
                    JOptionPane.showMessageDialog(checkBalanceDialog, "User not found, please try again");
                }
            }
        });

        checkBalanceDialog.setLocationRelativeTo(parentDialog);
        checkBalanceDialog.setVisible(true);
    }

    private static void handleTopUp(JDialog parentDialog) {
        JDialog topUpDialog = new JDialog(parentDialog, "Top Up Card Balance", true);
        topUpDialog.setSize(300, 200);
        topUpDialog.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel usernameLabel = new JLabel("Please Enter Your Username:");
        JTextField usernameField = new JTextField();
        JLabel amountLabel = new JLabel("Enter the amount to add to your balance:");
        JTextField amountField = new JTextField();
        JButton topUpButton = new JButton("Top Up");

        topUpDialog.add(usernameLabel);
        topUpDialog.add(usernameField);
        topUpDialog.add(amountLabel);
        topUpDialog.add(amountField);
        topUpDialog.add(topUpButton);

        topUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                double amountToAdd = Double.parseDouble(amountField.getText());

                Double currentBalance = SignUp.getUserBalanceByUsername(username);

                if (currentBalance != null) {
                    double newBalance = currentBalance + amountToAdd;
                    SignUp.updateUserBalance(username, newBalance);
                    JOptionPane.showMessageDialog(topUpDialog, "New Balance: $" + newBalance);
                } else {
                    JOptionPane.showMessageDialog(topUpDialog, "User not found, please try again");
                }
            }
        });

        topUpDialog.setLocationRelativeTo(parentDialog);
        topUpDialog.setVisible(true);
    }

    private static void handlePayParkingFee(JDialog parentDialog) {
        JDialog payParkingFeeDialog = new JDialog(parentDialog, "Pay Parking Fee", true);
        payParkingFeeDialog.setSize(400, 300);
        payParkingFeeDialog.setLayout(new GridLayout(6, 1, 10, 10));

        JLabel vehicleLabel = new JLabel("Enter your type of vehicle: (1. Car / 2. Motorcycle / 3. Truck)");
        JTextField vehicleField = new JTextField();
        JLabel timeLabel = new JLabel("Enter the amount of time your vehicle has been parked (in hours):");
        JTextField timeField = new JTextField();
        JLabel paymentLabel = new JLabel("Which type of payment would you like to use? (Card or Cash)");
        JTextField paymentField = new JTextField();
        JButton payButton = new JButton("Pay");

        payParkingFeeDialog.add(vehicleLabel);
        payParkingFeeDialog.add(vehicleField);
        payParkingFeeDialog.add(timeLabel);
        payParkingFeeDialog.add(timeField);
        payParkingFeeDialog.add(paymentLabel);
        payParkingFeeDialog.add(paymentField);
        payParkingFeeDialog.add(payButton);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vehicle = vehicleField.getText();
                double time = Double.parseDouble(timeField.getText());
                String transaction = paymentField.getText();

                double rate = 0;
                switch (vehicle.toLowerCase()) {
                    case "car":
                        rate = 3.0;
                        break;
                    case "truck":
                        rate = 4.0;
                        break;
                    case "motorcycle":
                        rate = 2.0;
                        break;
                    default:
                        JOptionPane.showMessageDialog(payParkingFeeDialog, "Invalid input");
                        return;
                }

                double parkingFee = rate * time;
                JOptionPane.showMessageDialog(payParkingFeeDialog, "Your Total Parking Fee: $" + parkingFee);

                if (transaction.equalsIgnoreCase("Card")) {
                    String username = JOptionPane.showInputDialog("Please enter your username:");
                    Double userBalance = SignUp.getUserBalanceByUsername(username);

                    if (userBalance != null) {
                        if (userBalance >= parkingFee) {
                            double newBalance = userBalance - parkingFee;
                            SignUp.updateUserBalance(username, newBalance);
                            JOptionPane.showMessageDialog(payParkingFeeDialog, "Payment was a success!\nCurrent Balance: $" + newBalance);
                        } else {
                            JOptionPane.showMessageDialog(payParkingFeeDialog, "Insufficient balance. Please top up your card.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(payParkingFeeDialog, "User not found, please try again.");
                    }

                } else if (transaction.equalsIgnoreCase("Cash")) {
                    System.out.println("Payment Successful!");
                } else {
                    JOptionPane.showMessageDialog(payParkingFeeDialog, "Invalid input, please choose a valid payment option");
                }
            }
        });

        payParkingFeeDialog.setLocationRelativeTo(parentDialog);
        payParkingFeeDialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        handleParking(frame);
        frame.setVisible(true);
    }
}
