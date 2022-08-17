package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class JdbcParkDaoTest extends DAOIntegrationTest
{
    JdbcParkDao jdbcParkDao;

    @Before
    public void setup(){
        jdbcParkDao = new JdbcParkDao(getDataSource());
    }

    @Test
    public void getParkById_should_returnParkFromDatabase_withValidId()
    {
        // arrange
        Park expected = new Park(1,"Acadia","Maine", LocalDate.parse("1919-02-26"),47389, 2563129,"Covering most of Mount Desert Island and other coastal islands, Acadia features the tallest mountain on the Atlantic coast of the United States, granite peaks, ocean shoreline, woodlands, and lakes. There are freshwater, estuary, forest, and intertidal habitats.");
        int searchId = 1;
        String message = "Because the park dao should have received Acadia from the database when searching for id 1.";

        // act
        Park actual = jdbcParkDao.getParkById(searchId);

        // assert
        assertNotNull(message, actual);
        assertEquals(message, expected.getParkId(), actual.getParkId());
        assertEquals(message, expected.getName(), actual.getName());
        assertEquals(message, expected.getLocation(), actual.getLocation());
        assertEquals(message, expected.getDate(), actual.getDate());
        assertEquals(message, expected.getArea(), actual.getArea());
        assertEquals(message, expected.getVisitors(), actual.getVisitors());
        assertEquals(message, expected.getDescription(), actual.getDescription());

    }
}
