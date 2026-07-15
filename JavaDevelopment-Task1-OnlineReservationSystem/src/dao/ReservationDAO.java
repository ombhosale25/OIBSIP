package dao;

import db.DBConnection;
import model.Reservation;

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

}