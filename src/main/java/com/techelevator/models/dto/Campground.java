package com.techelevator.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Campground {
    private int campgroundId;
    private int parkId;
    private String name;
    private int openFrom;
    private int openTo;
    private BigDecimal dailyFee;

    public Campground(int campgroundId, int parkId, String name, int openFrom, int openTo, BigDecimal dailyFee) {
        this.campgroundId = campgroundId;
        this.parkId = parkId;
        this.name = name;
        this.openFrom = openFrom;
        this.openTo = openTo;
        this.dailyFee = dailyFee;
    };

    public Campground(){};

    public int getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(int campgroundId) {
        this.campgroundId = campgroundId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenFrom() {
        String monthName = returnMonth(openFrom);
        return monthName;
    }

    public int getOpenFromInt() {
        return openFrom;
    }

    public void setOpenFrom(int openFrom) {
        this.openFrom = openFrom;
    }

    public String getOpenTo() {
        String monthName = returnMonth(openTo);
        return monthName;
    }

    public int getOpentToInt() {
        return openTo;
    }

    public void setOpenTo(int openTo) {
        this.openTo = openTo;
    }

    public BigDecimal getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(BigDecimal dailyFee) {
        this.dailyFee = dailyFee;
    }
    private String returnMonth (int monthInt) {
        switch (monthInt) {
            case 01:
                return "January";
            case 02:
                return "February";
            case 03:
                return "March";
            case 04:
                return "April";
            case 05:
                return "May";
            case 06:
                return "June";
            case 07:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Invalid";

        }
    }
}
