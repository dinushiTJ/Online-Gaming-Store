/**
 * This is the StoreDBHelper class implementation which deals with the
 * gamer's store related interactions with the database
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import model.Game;

public class StoreDBHelper {
	private DataSource dSource;
	
	//overloaded constructor fetches the connection pool resource sent by the servlet
	public StoreDBHelper(DataSource dSource) {
		this.dSource = dSource;
	}
	
	//returns a list of games fetched from the db
	public List<Game> getAllGames() throws Exception {
		List<Game> allGames = new ArrayList<>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = dSource.getConnection();
			String sql = "select * from `games` order by `addedDate`;";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);

			while(resultSet.next()) {
				int gID = Integer.parseInt(resultSet.getString("gID"));
				int adminID = Integer.parseInt(resultSet.getString("adminID"));
				String name = resultSet.getString("name");
				double price = Double.parseDouble(resultSet.getString("price"));
				String releaseDate = resultSet.getString("releaseDate");
				String developers = resultSet.getString("developers");
				String description = resultSet.getString("description");
				String addedDate = resultSet.getString("addedDate");
				String banner = resultSet.getString("banner");
				
				Game newGame = new Game(gID, adminID, name, price, releaseDate, developers, description, addedDate, banner);
				allGames.add(newGame);
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
		return allGames;
	}

	//adds a given game to the cart along with the given user id
	public void addToCart(String gameid, int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = dSource.getConnection();
			String sqlquery = "INSERT INTO `cart`(`gamerID`, `gID`) VALUES (?,?);";

			statement = conn.prepareStatement(sqlquery);
			statement.setString(1, Integer.toString(userid));
			statement.setString(2, gameid);
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
