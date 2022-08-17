package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Site;

import java.time.LocalDate;
import java.util.List;

public interface SiteDao {
    List<Site> getAllSites();
    public List<Site> getSitesByCampgroundId(int campgroundId);

    public List<Site> getSitesByAvailability(int campgroundId, LocalDate fromDate, LocalDate toDate, int monthFrom, int monthTo);
    public List<Site> getSitesAvailableByPark(int parkId, LocalDate fromDate, LocalDate toDate, int monthFrom, int monthTo);
}
