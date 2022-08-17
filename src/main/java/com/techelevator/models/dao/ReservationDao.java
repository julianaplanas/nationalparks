package com.techelevator.models.dao;

import com.techelevator.models.dto.Reservation;
import com.techelevator.models.dto.Site;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao {
        List<Reservation> getAllReservations();

        public int getNumberOfId();

        public void createNewReservation(Reservation reservation);

        public List<Reservation> getReservationNextMonth(int parkId);
}
