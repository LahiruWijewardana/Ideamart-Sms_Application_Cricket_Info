package mappers;

import dbconnect.DatabaseConnect;
import model.Users;
import java.sql.*;
import java.util.ArrayList;

public class UsersMapper {
	
	public Boolean findUser(Users user) throws SQLException{
        Boolean userFound = false;

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String query = "SELECT msisdn from Users";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(query);

        ArrayList<String> msisdnArray = new ArrayList<String>();

        while (rs.next()) {
            msisdnArray.add(rs.getString("msisdn"));

        }

        if (msisdnArray.contains(user.getMsisdn())){
            userFound = true;
        }

        con.close();

        return userFound;
    }
	
	public Boolean findRegister(Users user) throws SQLException{
        Boolean userRegistered = false;

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String sql = "SELECT register from Users Where msisdn = ?";
        
     // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, user.getMsisdn());
        
        ResultSet rs = preparedStmt.executeQuery();

        while (rs.next()) {
        		if (rs.getString("register").equalsIgnoreCase("1")){
        			userRegistered = true;
            }

        }

        con.close();

        return userRegistered;
    }
	
	public void addUser(Users user) throws SQLException {
		
		String Type = null;
		
		switch (user.getType()) {
		case "Register":
			Type = "1";
			break;
			
		case "Unregister":
			Type = "0";
			break;
			
		case "Pending":
			Type = "2";
			break;

		}

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String sql = "INSERT INTO Users (msisdn, register, addTime) VALUES (?,?, current_time)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, user.getMsisdn());
        preparedStmt.setString(2, Type);

        System.out.println(preparedStmt);
        preparedStmt.execute();
        con.close();

    }
	
	public void updateUser(Users user) throws SQLException {
		
		String Type = null;
		
		switch (user.getType()) {
		case "Register":
			Type = "1";
			break;
			
		case "Unregister":
			Type = "0";
			break;
			
		case "Pending":
			Type = "2";
			break;
			
		}

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String sql = "UPDATE Users SET register = ? WHERE msisdn = ?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setString(1, Type);
        preparedStmt.setString(2, user.getMsisdn());

        System.out.println(preparedStmt);
        preparedStmt.execute();
        con.close();

    }
}
