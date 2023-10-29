package com.liveasy.Assignment.Db.Repository;

import com.liveasy.Assignment.Db.LoadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Rep implements Ilog {
    @Autowired
    private JdbcTemplate template;

    @Override
    public LoadData adddata(LoadData loadData) {

        template.execute("insert into logistics(loadingpoint,unloadingpoint,producttype,trucktype,nooftrucks,weight,comment,shipperid,Date) values (?,?,?,?,?,?,?,?,?);", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, loadData.getLoadingpoint());
                ps.setString(2, loadData.getUnloadingpoint());
                ps.setString(3, loadData.getProducttype());
                ps.setString(4, loadData.getTrucktype());
                ps.setInt(5, loadData.getNooftrucks());
                ps.setInt(6, loadData.getWeight());
                ps.setString(7, loadData.getComment());
                ps.setString(8, loadData.getShipperid());
                ps.setString(9, loadData.getDate());
                return ps.execute();
            }
        });
        return loadData;
    }

    @Override
    public List<LoadData> getdata(String ship) throws SQLException {
        List<LoadData> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            // Assume that connection is properly established
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liveasy", "root", "gss@1009");
            // Prepare the SQL query
            String sql = "SELECT * FROM logistics WHERE shipperid = ?";

            // Create a prepared statement
            statement = connection.prepareStatement(sql);

            // Set the parameter values
            statement.setString(1, ship);

            // Execute the query
            rs= statement.executeQuery();

            // Process the result set
            while (rs.next()) {
                // Access the columns of the result set
                LoadData loadData= new LoadData(rs.getString("loadingpoint"), rs.getString("unloadingpoint"), rs.getString("producttype"), rs.getString("trucktype"), rs.getInt("nooftrucks"), rs.getInt("weight"), rs.getString("comment"), rs.getString("shipperid"), rs.getString("Date"));
                list.add(loadData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the resources in the reverse order of their creation
            if (rs!= null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return list;
    }

    @Override
    public String delete(String loadid) {
        template.execute("delete  from logistics where shipperid= ?;", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1,loadid);
                return ps.execute();
            }
        });
        return "deleted successfully";
    }@Override
    public int updatetable(String loadid, String loadingpoint) {

        Connection connection=null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liveasy", "root", "gss@1009");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int result=0;
        try{

            PreparedStatement ps= connection.prepareStatement("update logistics  set loadingpoint=? where shipperid=?;");
            ps.setString(1,loadingpoint);
            ps.setString(2,loadid);
            result=ps.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
        return result;
    }
}





