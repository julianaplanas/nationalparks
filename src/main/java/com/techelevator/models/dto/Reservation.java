package com.techelevator.models.dto;

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private int siteId;
    private String name;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate createDate;

    public Reservation(int reservationId, int siteId, String name, LocalDate fromDate, LocalDate toDate, LocalDate createDate) {
        this.reservationId = reservationId;
        this.siteId = siteId;
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createDate = createDate;
    }
    public Reservation (){};

    public int getReservationId() {
        return reservationId;
    }

    public int getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
