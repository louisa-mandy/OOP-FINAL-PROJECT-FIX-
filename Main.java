package Parking_Ticket;

//idea


/* make a Parking_Ticket.parking ticket program */

//start program


// user would input their vehicle type ( car, motorcycle, truck )
// user would input the amount of time they have spent Parking_Ticket.parking in the area
// the output would show the total amount they have to pay for the Parking_Ticket.parking fee




/* output :
 *  1. check your card balance
 *  2. top up your card
 *  3. register an account - input account and password??
 *  3. pay Parking_Ticket.parking fee */


import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Scanner;

import java.util.InputMismatchException;

public class Main {


    public static void main(String[] args) {

        Jframe.handleJframe();
        //program
        Scanner mandy = new Scanner(System.in); // create the scanner


        while (true) {


            System.out.println(" ");
            System.out.println("Welcome to our our Parking Ticket program ");
            System.out.println(" ");
            System.out.println("Enter a choice :  "); // buat label sama textfield nya
            System.out.println("1. Continue As Guest  \n2. Don't have an account yet? Sign Up \n3. Log In Account \n4. Exit Program");


            Integer user_input = mandy.nextInt(); // user input


            switch (user_input) {
                case 1: // continue as guest
                    guest.handleGuest();
                    break;
                case 2: // sign in
                    SignUp.handleSignUp();
                    break;

                case 3: ///login
                    //after user would log in
                    //make another switch statement and provide the rest of the options

                    // make another user input / scanner???
                    System.out.println(" ");
                    System.out.println("Login");

                    System.out.println("Enter your email : ");
                    String enteredEMAIL = mandy.next();
                    System.out.println(" ");

                    System.out.println("Enter your password : ");
                    String enteredPASSWORD = mandy.next();


                    boolean found = SignUp.verifyCredentials(enteredEMAIL, enteredPASSWORD);

                    if (found) {
                        System.out.println(" ");
                        System.out.println("Login Successful!");

                        parking.handleParking();
                    } else {
                        System.out.println("Invalid email or password. Please try again.");
                    }
                    break;
                case 4:
                    // stop program

                    System.out.println("Exiting program");
                    mandy.close(); // closing the scanner
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, Please try again");

            }





        }
    }
}