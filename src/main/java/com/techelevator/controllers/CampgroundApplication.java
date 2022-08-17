package com.techelevator.controllers;

import com.techelevator.models.dao.*;
import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Reservation;
import com.techelevator.models.dto.Site;
import com.techelevator.views.Output;
import com.techelevator.views.UserInterface;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CampgroundApplication extends Application {
    private ParkDao parkDao;
    private CampgroundDao campgroundDao;
    private SiteDao siteDao;
    private ReservationDao reservationDao;

    public CampgroundApplication(DataSource datasource) {
        campgroundDao = new JdbcCampgroundDao(datasource);
        parkDao = new JdbcParkDao(datasource);
        siteDao = new JdbcSiteDao(datasource);
        reservationDao = new JdbcReservationDao(datasource);
    }

    public void run() {

        while(true) {
            List<Park> parks = parkDao.getAllParks();
            // Show all parks and get user input
            String choice = UserInterface.displayAllParks(parks);

            if(choice.equalsIgnoreCase("quit")) {
                break;
            } else {
                showParkInformation(choice);
            }


        }
    }

    private void showParkInformation(String choice) {
        int parkId = parseInt(choice);
        Park park = parkDao.getParkById(parkId);

        if(park == null) {
            Output.displayMessage("Please select a valid code.");
        } else {
            String parkName = parkDao.getParkById(parkId).getName();
            // Display park information and menu
            Output.displayParkById(park);
            parkMenu(parkId, parkName);
        }

    }

    private void parkMenu(int parkId, String parkName) {
        while(true) {
            String choice = UserInterface.displayParkMenu(parkName);

            if(choice.equalsIgnoreCase("campgrounds")) {
                // Show campgrounds
                showCampgroundInformation (parkId, parkName);

            } else if (choice.equalsIgnoreCase("reservation park")) {
                reservationByPark(parkId, parkName);

            } else if (choice.equalsIgnoreCase("reservation campground")) {
                // Make a reservation by choosing a campground
                reservationByCampground (parkId, parkName);

            } else if (choice.equalsIgnoreCase("upcoming reservations")) {
              List<Reservation> upcomingReservations = reservationDao.getReservationNextMonth(parkId);
              if (upcomingReservations.size()< 1){
                  Output.displayMessage("There are no upcoming reservation this month for " + parkName + " National Park.");
              }
              else {
                  Output.displayReservationsForMonth(upcomingReservations, parkName);
              }

            } else if (choice.equalsIgnoreCase("previous")) {
                break;
            } else {
                Output.displayMessage("Please select a valid code.");
            }
        }

    }

    private void reservationByPark(int parkId, String parkName) {

        while (true) {
            // Get user input
            LocalDate arrivalDate = LocalDate.now();
            LocalDate departureDate = LocalDate.now();
            List<Site> availableSites = new ArrayList<>();
            long daysDiff = 0;
            List<BigDecimal> campgroundFees = new ArrayList<>();

            try {
                arrivalDate = LocalDate.parse(UserInterface.searchQuerys("What is the arrival date? yyyy-mm-dd: "));
                departureDate = LocalDate.parse(UserInterface.searchQuerys("What is the departure date? yyyy-mm-dd: "));
                int monthFrom = arrivalDate.getMonthValue();
                int monthTo = departureDate.getMonthValue();

                // Calculate the amount of days of the reservation to get the total cost
                Period period = Period.between(arrivalDate, departureDate);
                daysDiff = Math.abs(period.getDays());

                // Get a list of available Sites by date
                availableSites = siteDao.getSitesAvailableByPark(parkId, arrivalDate,departureDate, monthFrom, monthTo);

            } catch (DateTimeParseException e) {
                Output.displayMessage("Please input a valid date in the format yyyy-mm-dd.");
                break;
            }

            for(Site site : availableSites) {
                campgroundFees.add(campgroundDao.getCampgroundById(site.getCampgroundID()).getDailyFee());
            }

            if (availableSites.size() < 1){
                Output.displayMessage("There are no available sites for this park.");
            }
            else {
                Output.displayAvailableSitesByPark(availableSites, parkName, daysDiff, campgroundFees);
                reservationPath(arrivalDate, departureDate);
            }
        }


    }

    private void reservationByCampground(int parkId, String parkName) {
        List<Campground> campgrounds = campgroundDao.getAllCampgroundByPark(parkId);
        Output.displayCampgroundByPark(campgrounds, parkName);
        String choice = UserInterface.selectSearchType();

        if (choice.equalsIgnoreCase("simple")) {
            // Make a simple search to make a reservation
            simpleSearch();
        } else if (choice.equalsIgnoreCase("advanced")) {
            Output.displayMessage("This function is not available yet. We're working on it! Thanks for your patience.");
        }
        else {
            Output.displayMessage("Please select a valid code.");
        }

    }

    private void simpleSearch() {
        int campgroundId = Integer.parseInt(UserInterface.searchQuerys("Which campground (enter 0 to cancel)?: "));

        while(true) {

            if(campgroundId == 0) {
                // Go to previous menu if 0 selected
                break;
            } else if (campgroundDao.getCampgroundById(campgroundId) == null) {
                Output.displayMessage("Please select a valid code.");
            } else {

                // Get campground information
                BigDecimal campgroundFee = campgroundDao.getCampgroundById(campgroundId).getDailyFee();

                // Get user input
                LocalDate arrivalDate;
                LocalDate departureDate;
                List<Site> availableSitesByDate = new ArrayList<>();
                long daysDiff = 0;

                try {
                    arrivalDate = LocalDate.parse(UserInterface.searchQuerys("What is the arrival date? yyyy-mm-dd: "));
                    departureDate = LocalDate.parse(UserInterface.searchQuerys("What is the departure date? yyyy-mm-dd: "));
                    int monthFrom = arrivalDate.getMonthValue();
                    int monthTo = departureDate.getMonthValue();

                    // Calculate the amount of days of the reservation to get the total cost
                    Period period = Period.between(arrivalDate, departureDate);
                    daysDiff = Math.abs(period.getDays());

                    // Get a list of available Sites by date
                    availableSitesByDate = siteDao.getSitesByAvailability(campgroundId, arrivalDate, departureDate, monthFrom, monthTo);

                } catch (DateTimeParseException e) {
                    Output.displayMessage("Please input a valid date in the format yyyy-mm-dd.");
                    break;
                }

                if(availableSitesByDate.size() < 1) {
                    // Output if there's no available sites
                    if(!noAvailableDates()) {
                        break;
                    }
                } else {
                    // Display available sites
                    Output.displayAvailableSitesByDate(availableSitesByDate, campgroundFee, daysDiff);
                    // Path if there's reservations opptions
                    boolean isFinish = reservationPath(arrivalDate, departureDate);
                    if(isFinish) {
                        break;
                    }
                }

            }
        }

    }

    private boolean reservationPath(LocalDate arrivalDate, LocalDate departureDate) {

        // Get user input
        int siteNumber = Integer.parseInt(UserInterface.searchQuerys("Which site should be reserved (enter 0 to cancel)? "));
        boolean isFinish = false;

        if(siteNumber == 0) {
            // Cancel option
            isFinish = true;
        } else {

            // Create new reservation and get info
            Reservation reservation = new Reservation();
            LocalDate today = getTodayDate();
            int reservationId = reservationDao.getNumberOfId();

            // Get user input
            String nameReservation = UserInterface.searchQuerys("What name should the reservation be made under?: ");

            // Get info to complete a new reservation
            reservation.setName(nameReservation);
            reservation.setFromDate(arrivalDate);
            reservation.setToDate(departureDate);
            reservation.setCreateDate(today);
            reservation.setSiteId(siteNumber);
            reservation.setReservationId(reservationId);

            // Send info to new database
            reservationDao.createNewReservation(reservation);

            Output.displayMessage("The reservation has been made and the confirmation ID is " + reservationId + ".");

            isFinish = true;
        }

        return isFinish;
    }

    private LocalDate getTodayDate() {
        // Get today's date formatted
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatDateTime = LocalDate.parse(now.format(formatter));

        return formatDateTime;
    }

    private boolean noAvailableDates() {
        String selection = UserInterface.searchQuerys("There are no available sites for those dates. Would you like to try a different date range? (y/n) ");


        if(selection.equalsIgnoreCase("n")) {
            // Break
            return  false;
        } else if(selection.equalsIgnoreCase("y")) {
            // Try new dates
            return  true;
        } else {
            Output.displayMessage("Please select a valid option.");
        }

        return true;
    }

    private void showCampgroundInformation(int parkId, String parkName) {
        // List of campgrounds with info and display
        List<Campground> campgrounds = campgroundDao.getAllCampgroundByPark(parkId);
        Output.displayCampgroundByPark(campgrounds, parkName);

        while (true) {
            String choice = UserInterface.displayCampgroundMenu();

            if (choice.equalsIgnoreCase("available reservation")) {
                // Make a simple search to make a reservation
                simpleSearch();
            } else if (choice.equalsIgnoreCase("return")) {
                break;
            }
            else {
                Output.displayMessage("Please select a valid code.");
            }
        }
    }


}
