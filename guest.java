package Parking_Ticket;

import java.util.Scanner;

public class guest {

    public static void handleGuest () {
        System.out.println(" Pay Parking Fee ");

        Scanner Pay = new Scanner(System.in);

        System.out.println("1. What type of vehicle are you driving? (Car / Truck/ Motorcycle)");
        String Vehicle = Pay.next();

        System.out.println("2. Enter the amount of time of your parked vehicle.. ( hours )");
        double Time = Pay.nextDouble();

        System.out.println("3. Please pay using cash ( Type in 'Cash' )");
        String Transaction = Pay.next();

        // for vehicle type
        // car = $3 per hour, truck = $4 per hour, Motorcycle = $2 per hour

        double rate = 0;
        switch (Vehicle.toLowerCase()) {
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

        }

        // for the hours
        // multiply each selected vehicle by the amount of hour the user input
        double parkingFee = rate * Time;
        System.out.println("Total amount : $" + parkingFee + "in cash at the parking exit");

        handleCash(Pay, parkingFee);
        // transaction
        // ask user for how much cash they will give
        // output : give them the remaining change in cash

    }
    public static void handleCash(Scanner Pay, double parkingFee){

        System.out.println("Please input the amount of cash you will give.. ");
        double GivenCash = Pay.nextDouble();

        if (GivenCash < parkingFee) {
            System.out.println("Insufficient cash, please input an enough amount of cash");
        } else if ( GivenCash == parkingFee ){
            System.out.println("The payment was a success! (no change needed) ");
        }else {
            double Change = GivenCash - parkingFee;
            System.out.println("The payment was a success!, here is your change: $" + Change);
        }


    }

    public static void main(String[] args) {
        handleGuest();
    }



}
