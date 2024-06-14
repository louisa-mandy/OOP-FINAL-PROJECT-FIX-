package Parking_Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestPanel {
    public static void handleGuestPanel(JFrame parentFrame) {
        // Membuat dialog untuk guest
        JDialog guestDialog = new JDialog(parentFrame, "Guest Parking", true);
        guestDialog.setSize(400, 300);
        guestDialog.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel vehicleLabel = new JLabel("Vehicle Type (Car/Truck/Motorcycle):");
        JTextField vehicleField = new JTextField();
        JLabel timeLabel = new JLabel("Parking Time (hours):");
        JTextField timeField = new JTextField();
        JLabel transactionLabel = new JLabel("Payment Method (Cash):");
        JTextField transactionField = new JTextField();
        JButton payButton = new JButton("Pay");

        guestDialog.add(vehicleLabel);
        guestDialog.add(vehicleField);
        guestDialog.add(timeLabel);
        guestDialog.add(timeField);
        guestDialog.add(transactionLabel);
        guestDialog.add(transactionField);
        guestDialog.add(new JLabel()); // Spacer
        guestDialog.add(payButton);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vehicleType = vehicleField.getText();
                double parkingTime;
                try {
                    parkingTime = Double.parseDouble(timeField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(guestDialog, "Invalid parking time. Please enter a numeric value.");
                    return;
                }
                String transaction = transactionField.getText();

                if (!transaction.equalsIgnoreCase("Cash")) {
                    JOptionPane.showMessageDialog(guestDialog, "Invalid payment method. Please enter 'Cash'.");
                    return;
                }

                double rate;
                switch (vehicleType.toLowerCase()) {
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
                        JOptionPane.showMessageDialog(guestDialog, "Invalid vehicle type. Please enter Car, Truck, or Motorcycle.");
                        return;
                }

                double parkingFee = rate * parkingTime;
                JOptionPane.showMessageDialog(guestDialog, "Total amount: $" + parkingFee + " in cash at the parking exit");

                handleCash(guestDialog, parkingFee);
            }
        });

        guestDialog.setLocationRelativeTo(parentFrame);
        guestDialog.setVisible(true);
    }

    public static void handleCash(JDialog guestDialog, double parkingFee) {
        double givenCash;
        String input = JOptionPane.showInputDialog(guestDialog, "Please input the amount of cash you will give:");

        try {
            givenCash = Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(guestDialog, "Invalid amount. Please enter a numeric value.");
            return;
        }

        if (givenCash < parkingFee) {
            JOptionPane.showMessageDialog(guestDialog, "Insufficient cash, please input an enough amount of cash");
        } else if (givenCash == parkingFee) {
            JOptionPane.showMessageDialog(guestDialog, "The payment was a success! (no change needed)");
        } else {
            double change = givenCash - parkingFee;
            JOptionPane.showMessageDialog(guestDialog, "The payment was a success!, here is your change: $" + change);
        }
    }
}