package com.techelevator.views;

import com.techelevator.models.dto.Park;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    private static Scanner in = new Scanner(System.in);

    public static void setIn(InputStream inStream)
    {
        in = new Scanner(inStream);
    }

    public static String displayAllParks(List<Park> parks){
        System.out.println(ColorCodes.GREEN + "List of National Parks: ");
        System.out.println("---------------------" + ColorCodes.RESET);

        for (Park park: parks){
            System.out.println(park.getParkId() + ") " + park.getName());
        }
        System.out.println("Q) Quit");

        System.out.println();
        System.out.print("Select a code to display information about the park: ");
        String choice = in.nextLine().trim().toLowerCase();
        System.out.println();

        if(choice.equalsIgnoreCase("Q")) {
            return "quit";
        }
        return choice;

    }

    public static String displayParkMenu(String parkName) {
        System.out.println(ColorCodes.YELLOW + "Main Menu for " + parkName + " National Park:");
        System.out.println("----------------------------------------------" + ColorCodes.RESET);
        System.out.println("1) View campgrounds");
        System.out.println("2) Search for Reservation by Park");
        System.out.println("3) Search  for Reservation by Campground");
        System.out.println("4) See all upcoming reservations");
        System.out.println("5) Return to Previous Screen");
        System.out.println();
        System.out.print("Select an option: ");
        String choice = in.nextLine().trim().toLowerCase();
        System.out.println();

        if(choice.equalsIgnoreCase("1")) {
            return "campgrounds";
        } else if (choice.equalsIgnoreCase("2")) {
            return "reservation park";
        } else if (choice.equalsIgnoreCase("3")) {
            return "reservation campground";
        } else if (choice.equalsIgnoreCase("4")) {
            return "upcoming reservations";
        } else if (choice.equalsIgnoreCase("5")) {
            return "previous";
        } else {
            return "error";
        }

    }

    public static String displayCampgroundMenu (){
        System.out.println(ColorCodes.YELLOW + "Menu");
        System.out.println("------" + ColorCodes.RESET);
        System.out.println("1) Search for available reservation");
        System.out.println("2) Return to previous screen");
        System.out.println();
        System.out.print("Select a command: ");
        String choice = in.nextLine().trim().toLowerCase();
        System.out.println();

        if(choice.equalsIgnoreCase("1")) {
            return "available reservation";
        } else if (choice.equalsIgnoreCase("2")) {
            return "return";
        }
        else return "error";

    }

    public static String selectSearchType (){
        System.out.println(ColorCodes.YELLOW + "Menu");
        System.out.println("------" + ColorCodes.RESET);
        System.out.println("1) Simple search");
        System.out.println("2) Advanced search");
        System.out.println();
        System.out.print("Select a command: ");
        String choice = in.nextLine().trim().toLowerCase();
        System.out.println();

        if(choice.equalsIgnoreCase("1")) {
            return "simple";
        } else if (choice.equalsIgnoreCase("2")) {
            return "advanced";
        }
        else return "error";

    }

    public static String searchQuerys(String message) {
        System.out.print(message);
        String choice = in.nextLine().trim().toLowerCase();
        System.out.println();

        return choice;
    }
}
