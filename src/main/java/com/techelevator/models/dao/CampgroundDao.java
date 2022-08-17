package com.techelevator.models.dao;

import com.techelevator.models.dto.Campground;

import java.util.List;

public interface CampgroundDao
{
    List<Campground> getAllCampgroundByPark(int parkId);
    Campground getCampgroundById(int campgroundId);
}
