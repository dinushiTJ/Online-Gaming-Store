/**
 * This is the ProfileDBHelper class implementation which deals with the
 * gamer profile related interactions with the database
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import model.GamerProfile;

public class ProfileDBHelper {
private DataSource dSource;
	
	//overloaded constructor fetches the connection pool resource sent by the servlet
	public ProfileDBHelper(DataSource dSource) {
		this.dSource = dSource;
	}

	//returns gamer details and their registered data as a GamerProfile object
	public GamerProfile getProfile(String username) throws Exception {
		GamerProfile gamer = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = dSource.getConnection();
			String sql = "select * from `registereduser` where `username`=?;";
			statement = conn.prepareStatement(sql);
			statement.setString(1, (username));
			resultSet = statement.executeQuery( );

			if (resultSet.next()) {
				int userID = Integer.parseInt(resultSet.getString("regUserID"));
				String uname = resultSet.getString("username");
				String email = resultSet.getString("email");
				int points = getPoints(userID);
				
				gamer = new GamerProfile(userID, uname, email, "", points);
				
			} else {
				throw new Exception ("User doesn't exist");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		//closing sql connections
		} finally {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return gamer;
	}

	//returns points of a given gamer by his id
	private int getPoints(int userID) throws Exception {
		int points = 0;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = dSource.getConnection();
			String sql = "select * from `gamer` where `gamerID`=?;";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, (userID));
			resultSet = statement.executeQuery( );

			if (resultSet.next()) {
				points = resultSet.getInt("points");
			} else {
				points = 0;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		//closing sql connections
		} finally {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return points;
		
	}

	//updates username and password of a gamer
	public void setNewUserDetails(int userID, String newUserName,String newPassword) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = dSource.getConnection();
			String sqlquery = "UPDATE `registeredUser` SET `username` = ?, `password` = ? WHERE `regUserID` = ?";

			statement = conn.prepareStatement(sqlquery);
			statement.setString(1, newUserName);
			statement.setString(2, newPassword);
			statement.setInt(3, userID);
			statement.execute();
		
		} catch (Exception e) {
			e.printStackTrace();
		//closing sql connections
		} finally {
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	
}