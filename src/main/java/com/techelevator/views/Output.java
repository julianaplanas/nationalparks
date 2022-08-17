package com.techelevator.views;

import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Reservation;
import com.techelevator.models.dto.Site;

import java.math.BigDecimal;
import java.util.List;

public class Output {

    public static void displayParkById(Park park) {
        String description = park.getDescription();
        int lineLength = 50;

        String message = park.getName() + " National Park";
        System.out.println(ColorCodes.GREEN + message);
        System.out.println("-------------------------------" + ColorCodes.RESET);
        System.out.println();

        System.out.println(String.format("%-18s %-25s", "Location: ", park.getLocation()));
        System.out.println(String.format("%-18s %-25s", "Establish: ", park.getDate()));
        System.out.println(String.format("%-18s %-25s", "Area: ", park.getArea()));
        System.out.println(String.format("%-18s %-25s", "Annual Visitors: ", park.getVisitors()));
        System.out.println();

        while(description.length() > 0) {
            String line;

            if(description.length() > lineLength) {
                line = description.substring(0, lineLength);
                description = description.substring(lineLength);
            } else {
                line = description;
                description = "";
            }

            System.out.println(line);

        }
        System.out.println();
    }

    public static void displayMessage(String message) {
        System.out.println(ColorCodes.RED + message + ColorCodes.RESET);
        System.out.println();
    }

    public static void displayCampgroundByPark(List<Campground> campgrounds, String parkName) {
        String message = parkName + " National Park Campgrounds";
        System.out.println(ColorCodes.GREEN + message);
        System.out.println("-----------------------------------" + ColorCodes.RESET);
        System.out.println();

        System.out.println(String.format("%-34s %-10s %-10s %-10s", "Name", "Open", "Close", "Daily Fee"));

        for (Campground campground : campgrounds) {
            String name = String.format("%-35s", "#" + campground.getCampgroundId() + " " + campground.getName());
            String open = String.format("%-11s", campground.getOpenFrom());
            String close = String.format("%-11s", campground.getOpenTo());
            String fee = String.format("%-10s", "$" + campground.getDailyFee());

            System.out.println(name + open + close + fee);
        }
        System.out.println();

    }

    public static void displayAvailableSitesByDate(List<Site> sites, BigDecimal campgroundFee, long daysDiff) {
        System.out.println(ColorCodes.CYAN + "Results Matching Your Search Criteria");
        System.out.println("-------------------------------------" + ColorCodes.RESET);
        System.out.println();

        System.out.println(String.format("%-10s %-13s %-13s %-15s %-13s %-15s", "Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utilities?", "Total Cost"));

        for (Site site : sites) {
            BigDecimal totalCost = campgroundFee.multiply(BigDecimal.valueOf(daysDiff));
            String accessible = site.isAccessible() ? "Yes" : "No";
            String utilities = site.isUtilities() ? "Yes" : "No";

            String number = String.format("%-11s", site.getSiteNumber());
            String occupancy = String.format("%-14s", site.getMaxOccupancy());
            String accessibilty = String.format("%-14s", accessible);
            String rvLength = String.format("%-16s", site.getMaxRvLength());
            String utility = String.format("%-14s", utilities);
            String cost = String.format("%-15s", "$" + totalCost);

            System.out.println(number + occupancy + accessibilty + rvLength + utility + cost);
        }
        System.out.println();
    }

    public static void displayAvailableSitesByPark(List<Site> sites, String parkName, long daysDiff, List<BigDecimal> campgroundFees) {
        System.out.println(ColorCodes.CYAN + "Results Matching Your Search Criteria in " + parkName + " National Park");
        System.out.println("------------------------------------------------------------------------" + ColorCodes.RESET);
        System.out.println();

        System.out.println(String.format("%-10s %-13s %-13s %-15s %-13s %-15s", "Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utilities?", "Total Cost"));

        for (int i = 0; i < sites.size(); i++) {
            BigDecimal totalCost = campgroundFees.get(i).multiply(BigDecimal.valueOf(daysDiff));
            String accessible = sites.get(i).isAccessible() ? "Yes" : "No";
            String utilities = sites.get(i).isUtilities() ? "Yes" : "No";

            String number = String.format("%-11s", sites.get(i).getSiteNumber());
            String occupancy = String.format("%-14s", sites.get(i).getMaxOccupancy());
            String accessibilty = String.format("%-14s", accessible);
            String rvLength = String.format("%-16s", sites.get(i).getMaxRvLength());
            String utility = String.format("%-14s", utilities);
            String cost = String.format("%-15s", "$" + totalCost);

            System.out.println(number + occupancy + accessibilty + rvLength + utility + cost);

        }
        System.out.println();

    }

    public static void displayReservationsForMonth(List<Reservation> reservations, String parkName) {
        System.out.println(ColorCodes.PURPLE + "Upcoming Reservations for next month in " + parkName + " National Park");
        System.out.println("----------------------------------------------------------------------" + ColorCodes.RESET);
        System.out.println();

        System.out.println(String.format("%-15s %-12s %-32s %-14s %-14s %-15s", "Reservation ID", "Site ID", "Name", "From", "To", "Creation Date"));

        for (Reservation reservation : reservations) {

            String reservationId = String.format("%-16s", "#" + reservation.getReservationId());
            String sideId = String.format("%-13s", reservation.getSiteId());
            String name = String.format("%-33s", reservation.getName());
            String fromDate = String.format("%-15s", reservation.getFromDate());
            String toDate = String.format("%-15s", reservation.getToDate());
            String createDate = String.format("%-15s", reservation.getCreateDate());

            System.out.println(reservationId + sideId + name + fromDate + toDate + createDate);
        }

        System.out.println();
    }

}
