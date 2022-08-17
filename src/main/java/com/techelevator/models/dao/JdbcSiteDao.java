package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDao implements SiteDao{
    JdbcTemplate jdbcTemplate;

    public JdbcSiteDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Site> getAllSites() {
        String sql = "SELECT site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities FROM site";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        List<Site> sites = new ArrayList<>();
        while (result.next()) {
            sites.add(mapRowToSite(result));
        }
        return sites;
    }

    @Override
    public List<Site> getSitesByCampgroundId(int campgroundId) {
        String sql = "SELECT site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities FROM site WHERE campground_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, campgroundId);

        List<Site> sites = new ArrayList<>();
        while (result.next()) {
            sites.add(mapRowToSite(result));
        }
        return sites;
    }

    public List<Site> getSitesByAvailability(int campgroundId, LocalDate fromDate, LocalDate toDate, int monthFrom, int monthTo) {
        String sql = "SELECT DISTINCT s.site_id, s.campground_id, s.site_number, s.max_occupancy, s.accessible, s.max_rv_length, s.utilities\n" +
                "FROM site as s\n" +
                "INNER JOIN campground c ON c.campground_id = s.campground_id\n" +
                "WHERE c.campground_id = ?\n" +
                "    and CAST(open_from_mm as int) <= ?\n" +
                "    AND cast(open_to_mm as int) >= ?\n" +
                "    AND s.site_id not in (SELECT DISTINCT s.site_id\n" +
                "                    FROM reservation AS r\n" +
                "                             INNER JOIN site s\n" +
                "                                        ON s.site_id = r.site_id\n" +
                "                    WHERE campground_id = ?\n" +
                "                      AND (\n" +
                "                            (from_date <= ? AND to_date > ?)\n" +
                "                            OR (from_date > ? AND from_date < ?)\n" +
                "                        ))\n" +
                "LIMIT 5;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, campgroundId, monthFrom, monthTo, campgroundId, fromDate, fromDate, fromDate, toDate);

        List<Site> sites = new ArrayList<>();

        while (result.next()) {
            sites.add(mapRowToSite(result));
        }

        return sites;
    }
    public List<Site> getSitesAvailableByPark(int parkId, LocalDate fromDate, LocalDate toDate, int monthFrom, int monthTo) {
        String sql = "SELECT DISTINCT s.site_id, s.campground_id, s.site_number, s.max_occupancy, s.accessible, s.max_rv_length, s.utilities\n" +
                "FROM site as s\n" +
                "         INNER JOIN campground c ON c.campground_id = s.campground_id\n" +
                "         INNER JOIN park p ON p.park_id = c.park_id\n" +
                "WHERE p.park_id = ?\n" +
                "  and CAST(open_from_mm as int) <= ?\n" +
                "  AND cast(open_to_mm as int) >= ?\n" +
                "  AND s.site_id not in (SELECT DISTINCT s.site_id\n" +
                "                        FROM reservation AS r INNER JOIN site s ON s.site_id = r.site_id\n" +
                "                        WHERE (\n" +
                "                                      (from_date <= ? AND to_date >?)\n" +
                "                                      OR (from_date > ? AND from_date < ?)\n" +
                "                                  ))\n" +
                "LIMIT 5;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId, monthFrom, monthTo, fromDate, fromDate, fromDate, toDate);

        List<Site> sites = new ArrayList<>();

        while (result.next()) {
            sites.add(mapRowToSite(result));
        }

        return sites;
    }
    private Site mapRowToSite(SqlRowSet rowSet) {
        Site site = new Site();

        site.setSiteId(rowSet.getInt("site_id"));
        site.setCampgroundID(rowSet.getInt("campground_id"));
        site.setSiteNumber(rowSet.getInt("site_number"));
        site.setMaxOccupancy(rowSet.getInt("max_occupancy"));
        site.setAccessible(rowSet.getBoolean("accessible"));
        site.setMaxRvLength(rowSet.getInt("max_rv_length"));
        site.setUtilities(rowSet.getBoolean("utilities"));

        return site;
    }
}
