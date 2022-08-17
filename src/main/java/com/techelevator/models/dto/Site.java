package com.techelevator.models.dto;

public class Site {
    private int siteId;
    private int campgroundID;
    private int siteNumber;
    private int maxOccupancy;
    private boolean accessible;
    private int maxRvLength;
    private boolean utilities;

    public Site(int siteId, int campgroundID, int siteNumber, int maxOccupancy, boolean accessible, int maxRvLength, boolean utilities) {
        this.siteId = siteId;
        this.campgroundID = campgroundID;
        this.siteNumber = siteNumber;
        this.maxOccupancy = maxOccupancy;
        this.accessible = accessible;
        this.maxRvLength = maxRvLength;
        this.utilities = utilities;
    }

    public Site(){};

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getCampgroundID() {
        return campgroundID;
    }

    public void setCampgroundID(int campgroundID) {
        this.campgroundID = campgroundID;
    }

    public int getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(int siteNumber) {
        this.siteNumber = siteNumber;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public int getMaxRvLength() {
        return maxRvLength;
    }

    public void setMaxRvLength(int maxRvLength) {
        this.maxRvLength = maxRvLength;
    }

    public boolean isUtilities() {
        return utilities;
    }

    public void setUtilities(boolean utilities) {
        this.utilities = utilities;
    }
}

