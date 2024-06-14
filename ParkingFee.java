package Parking_Ticket;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Scanner;


// for account havers only
public class ParkingFee {


    public static void handleParkingFee() {

        Scanner mandy = new Scanner(System.in);  // Create a Scanner object

        System.out.println(" ");

        System.out.println("Enter your type of vehicle: ");
        System.out.println("1. Car \n2. Motorcycle \n3.Truck");
        String input = mandy.nextLine();
        Integer input2 = mandy.nextInt();

        switch (input) {
            case "Car":
                break;
            case "Motorcycle":
                break;
            case "Truck":
                break;

        }


    }

}