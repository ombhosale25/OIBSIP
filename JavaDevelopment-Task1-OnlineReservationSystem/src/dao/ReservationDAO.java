package dao;

import db.DBConnection;
import model.Reservation;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationDAO {
    public boolean bookTicket(Reservation reservation){
        try{
            Connection con = DBConnection.getConnection();
            String sql="INSERT INTO reservations VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,reservation.getPnr());
            ps.setString(2,reservation.getPassengerName());
            ps.setInt(3,reservation.getTrainNo());
            ps.setString(4,reservation.getTrainName());
            ps.setString(5,reservation.getClassType());
            ps.setString(6,reservation.getJourneyDate());
            ps.setString(7,reservation.getSource());
            ps.setString(8,reservation.getDestination());
            int rows=ps.executeUpdate();
            return rows>0;

        }

        catch(Exception e){

            e.printStackTrace();

        }

        return false;

    }
    public Reservation getReservationByPNR(String pnr) {

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM reservations WHERE pnr=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, pnr);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Reservation reservation = new Reservation();

            reservation.setPnr(rs.getString("pnr"));
            reservation.setPassengerName(rs.getString("passenger_name"));
            reservation.setTrainNo(rs.getInt("train_no"));
            reservation.setTrainName(rs.getString("train_name"));
            reservation.setClassType(rs.getString("class_type"));
            reservation.setJourneyDate(rs.getString("journey_date"));
            reservation.setSource(rs.getString("source_station"));
            reservation.setDestination(rs.getString("destination_station"));

            return reservation;

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return null;

}
public boolean cancelReservation(String pnr) {

    try {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM reservations WHERE pnr=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, pnr);

        int rows = ps.executeUpdate();

        return rows > 0;

    } catch (Exception e) {

        e.printStackTrace();

    }

    return false;

}

}