package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao
{
    JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Park> getAllParks()
    {
        String sql = "SELECT park_id, name, location, establish_date, area, visitors,description FROM park";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        List<Park> parks = new ArrayList<>();
        while (result.next()) {
            parks.add(mapRowToPark(result));
        }
        return parks;
    }

    @Override
    public Park getParkById(int parkId)
    {
        Park park = null;

        String sql = "SELECT park_id, name, location, establish_date, area, visitors,description FROM park WHERE park_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId);

        if (result.next()) {
            park = mapRowToPark(result);
        }

        return park;
    }

    private Park mapRowToPark(SqlRowSet rowSet) {
        Park park = new Park();
        park.setParkId(rowSet.getInt("park_id"));
        park.setName(rowSet.getString("name"));
        park.setLocation(rowSet.getString("location"));
        if (rowSet.getDate("establish_date") != null){
            park.setDate(rowSet.getDate("establish_date").toLocalDate());
        }
        park.setArea(rowSet.getInt("area"));
        park.setVisitors(rowSet.getInt("visitors"));
        park.setDescription(rowSet.getString("description"));
        return park;
    }

}
