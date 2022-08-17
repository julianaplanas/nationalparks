package com.techelevator.models.dao;

import com.techelevator.models.dto.Campground;
import com.techelevator.models.dto.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcCampgroundDao implements CampgroundDao {

    JdbcTemplate jdbcTemplate;

    public JdbcCampgroundDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Campground> getAllCampgroundByPark(int parkId) {
        String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, cast(daily_fee as varchar(15)) as daily_fee FROM campground WHERE park_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId);

        List<Campground> campgrounds = new ArrayList<>();

        while (result.next()) {
            campgrounds.add(mapRowToCampground(result));
        }

        return campgrounds;
    }

    @Override
    public Campground getCampgroundById(int campgroundId) {
        Campground campground = null;

        String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, cast(daily_fee as varchar(15)) as daily_fee FROM campground WHERE campground_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, campgroundId);

        if(result.next()) {
            campground = mapRowToCampground(result);
        }

        return campground;
    }

    private Campground mapRowToCampground(SqlRowSet rowSet) {
        Campground campground = new Campground();

        campground.setCampgroundId(rowSet.getInt("campground_id"));
        campground.setParkId(rowSet.getInt("park_id"));
        campground.setName(rowSet.getString("name"));
        String fee = rowSet.getString("daily_fee").replace("$ ", "").replace(",",".");
        campground.setDailyFee(new BigDecimal(fee));
        campground.setOpenFrom(rowSet.getInt("open_from_mm"));
        campground.setOpenTo(rowSet.getInt("open_to_mm"));

        return campground;
    }


}
