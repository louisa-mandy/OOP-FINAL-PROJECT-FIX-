package Parking_Ticket;

import javax.sound.midi.Soundbank;

import java.util.LinkedList;
import java.util.Scanner;

public class parking {
    public static void handleParking() {

        while (true) {

            System.out.println(" ");

            System.out.println(" Enter a choice : ");
            System.out.println("1. Check Card Balance \n2. Top Up Card Balance \n3. Pay Parking Fee \n4. Go to previous page ");
            System.out.println(" ");

            Scanner mandy = new Scanner(System.in);  // Create a Scanner object
            Integer login = mandy.nextInt(); // user input

            switch (login) {
                case 1: // check card balance
                    System.out.println("Check Card Balance");
                    System.out.println("Please Enter Your Username: ");
                    String usernameToCheck = mandy.next();
                    Double balance = SignUp.getUserBalanceByUsername(usernameToCheck);

                    if (balance != null) {
                        System.out.println(usernameToCheck + "'s Balance: $" + balance);
                    } else {
                        System.out.println("User not found, please try again");
                    }
                    break;

                case 2: // top up card
                    System.out.println("Top Up Card Balance");
                    System.out.println("Please Enter Your Username: ");
                    String usernameToUpdate = mandy.next();
                    System.out.println("Enter the amount to add to your balance: ");
                    double amountToAdd = mandy.nextDouble();

                    Double currentBalance = SignUp.getUserBalanceByUsername(usernameToUpdate);

                    if (currentBalance != null) {
                        double newBalance = currentBalance + amountToAdd;
                        SignUp.updateUserBalance(usernameToUpdate, newBalance);
                        System.out.println("New Balance: $" + newBalance);
                    } else {
                        System.out.println("User not found, please try again");
                    }
                    break;

                case 3: // pay paking fee
                    System.out.println("Pay Parking Fee");
                    Scanner payScanner = new Scanner(System.in);

                    System.out.println("1. What type of vehicle are you driving? (Car / Truck / Motorcycle)");
                    String vehicle = payScanner.next();

                    System.out.println("2. Enter the amount of time your vehicle has been parked (in hours):");
                    double time = payScanner.nextDouble();

                    System.out.println("3. Which type of payment would you like to use? (Card or Cash)");
                    String transaction = payScanner.next();

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
                            System.out.println("Invalid input");
                            continue;
                    }

                    double parkingFee = rate * time;
                    System.out.println("Your Total Parking Fee: $" + parkingFee);

                    if (transaction.equalsIgnoreCase("Card")) {
                        System.out.println("Please enter your username: ");
                        String username = payScanner.next();
                        Double userBalance = SignUp.getUserBalanceByUsername(username);

                        if (userBalance != null) {
                            if (userBalance >= parkingFee) {
                                double newBalance = userBalance - parkingFee;
                                SignUp.updateUserBalance(username, newBalance);
                                System.out.println("Payment was a success!");
                                System.out.println("Current Balance: $" + newBalance);
                            } else {
                                System.out.println("Insufficient balance. Please top up your card.");
                            }
                        } else {
                            System.out.println("User not found, please try again.");
                        }

                    } else if (transaction.equalsIgnoreCase("Cash")) {

                        guest.handleCash(payScanner, parkingFee);
                    } else {
                        System.out.println("Invalid input, please choose a valid payment option");
                    }
                    break;

                case 4: // return to previous page
                    System.out.println("Returning to the main menu...");
                    return; // Exit the handleParking method, returning to the main menu

                default:
                    System.out.println("Please input a valid choice");
            }


        }


    }
}