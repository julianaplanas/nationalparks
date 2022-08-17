package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;

import java.util.List;

public interface ParkDao
{
     List<Park> getAllParks();
    Park getParkById(int parkId);
}
