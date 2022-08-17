package com.techelevator.models.dao;

import com.techelevator.models.dto.Park;
import com.techelevator.models.dto.Reservation;
import com.techelevator.models.dto.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcReservationDao implements ReservationDao{
    JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Reservation> getAllReservations() {
        String sql = "SELECT reservation_id, site_id, name, from_date, to_date, create_date FROM reservation";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        List<Reservation> reservations = new ArrayList<>();
        while (result.next()) {
            reservations.add(mapRowToReservations(result));
        }
        return reservations;
    }

    @Override
    public int getNumberOfId() {
        String sql = "SELECT reservation_id\n" +
                "FROM reservation\n" +
                "ORDER BY reservation_id desc " +
                "LIMIT 1";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        int id = 0;
        if(result.next()) {
            id = result.getInt("reservation_id");;
        }
        return id + 1;
    }

    public void createNewReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, reservation.getReservationId(), reservation.getSiteId(), reservation.getName(), reservation.getFromDate(), reservation.getToDate(), reservation.getCreateDate());

    }

    public List<Reservation> getReservationNextMonth(int parkId) {
        String sql = "SELECT reservation_id, r.site_id, name, from_date, to_date, create_date\n" +
                "    FROM reservation as r\n" +
                "    inner join site s on s.site_id = r.site_id\n" +
                "    where from_date >= DATE(now()) AND from_date <= DATE(now()) + 30\n" +
                "    AND campground_id = ?\n" +
                "    ORDER BY from_date";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId);

        List<Reservation> reservations = new ArrayList<>();

        while (result.next()) {
            reservations.add(mapRowToReservations(result));
        }

        return reservations;
    }

    private Reservation mapRowToReservations(SqlRowSet rowSet) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(rowSet.getInt("reservation_id"));
        reservation.setSiteId(rowSet.getInt("site_id"));
        reservation.setName(rowSet.getString("name"));
        if (rowSet.getDate("from_date") != null){
            reservation.setFromDate(rowSet.getDate("from_date").toLocalDate());
        }
        if (rowSet.getDate("to_date") != null){
            reservation.setToDate(rowSet.getDate("to_date").toLocalDate());
        }
        if (rowSet.getDate("create_date") != null){
            reservation.setCreateDate(rowSet.getDate("create_date").toLocalDate());
        }
        return reservation;
    }
}
