package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainDAO {

    public String getTrainName(int trainNo) {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT train_name FROM trains WHERE train_no=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, trainNo);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                return rs.getString("train_name");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return "";
    }

}