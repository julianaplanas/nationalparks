package com.techelevator.models.dto;

import java.time.LocalDate;

public class Park
{
    private int parkId;
    private String name;
    private String location;
    private LocalDate date;
    private int area;
    private int visitors;
    private String description;

    public Park()
    {
    }

    public Park(int parkId, String name, String location, LocalDate date, int area, int visitors, String description)
    {
        this.parkId = parkId;
        this.name = name;
        this.location = location;
        this.date = date;
        this.area = area;
        this.visitors = visitors;
        this.description = description;
    }

    public int getParkId()
    {
        return parkId;
    }

    public void setParkId(int parkId)
    {
        this.parkId = parkId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public int getArea()
    {
        return area;
    }

    public void setArea(int area)
    {
        this.area = area;
    }

    public int getVisitors()
    {
        return visitors;
    }

    public void setVisitors(int visitors)
    {
        this.visitors = visitors;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
