/**
 * This is the CartDBHelper class implementation which deals with the
 * gamer cart related interactions with the database
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
import model.Order;

public class CartDBHelper {
private DataSource dSource;
	
	//overloaded constructor fetches the connection pool resource sent by the servlet
	public CartDBHelper(DataSource dSource) {
		this.dSource = dSource;
	}

	//returns a list of all games
	public List<Game> getCart() throws Exception {
		List<Game> cart = new ArrayList<>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = dSource.getConnection();
			String sql = "select * from `cart`;";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);

			while(resultSet.next()) {
				int gID = Integer.parseInt(resultSet.getString("gID"));
				Game cartItem = getGame(gID);
				cart.add(cartItem);
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

		return cart;
	}

	//returns a game object when the game id is given as a parameter
	private Game getGame(int gID) throws Exception {
		Game game = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = dSource.getConnection();
			String sql = "select * from `games` where `gID`=?;";
			statement = conn.prepareStatement(sql);
			statement.setString(1, Integer.toString(gID));
			resultSet = statement.executeQuery( );

			if (resultSet.next()) {
				int gameID = Integer.parseInt(resultSet.getString("gID"));
				int adminID = Integer.parseInt(resultSet.getString("adminID"));
				String name = resultSet.getString("name");
				double price = Double.parseDouble(resultSet.getString("price"));
				String releaseDate = resultSet.getString("releaseDate");
				String developers = resultSet.getString("developers");
				String description = resultSet.getString("description");
				String addedDate = resultSet.getString("addedDate");
				String banner = resultSet.getString("banner");
				
				game = new Game(gameID, adminID, name, price, releaseDate, developers, description, addedDate, banner);
				
			} else {
				throw new Exception ("Game doesn't exist");
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
		
		return game;
	}

	//removes a game from the cart of a user from the db when the userid and the gameid is given
	public void removeGame(int gameid, int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = dSource.getConnection();
			String sql = "DELETE FROM `cart` WHERE `gID`=? AND `gamerID`=?;";

			statement = conn.prepareStatement(sql);
			statement.setInt(1, gameid);
			statement.setInt(2, userid);
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

	//deletes all recodes in cart table of a particular user
	public void clearCart(int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = dSource.getConnection();
			String sql = "DELETE FROM `cart` WHERE `gamerID`=?;";

			statement = conn.prepareStatement(sql);
			statement.setInt(1, userid);
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

	//saves a new order placed by a gamer in the database by receiving the user's cart and order as an object.
	public int addNewOrder(Order newOrder, List<Game> myCart) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int generatedKey = 0;

		try {
			conn = dSource.getConnection();
			String sqlquery = "INSERT INTO `orders`(`gamerID`, `oDate`, `orderPayment`, `orderDiscount`, `paymentStatus`) VALUES (?,?,?,?,?);";

			statement = conn.prepareStatement(sqlquery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, newOrder.getGamerID());
			statement.setString(2, newOrder.getOrderDate());
			statement.setDouble(3, newOrder.getOrderPayment());
			statement.setDouble(4, newOrder.getOrderDiscount());
			statement.setString(5, newOrder.getPaymentStatus());
			statement.execute();
//			 
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
			    generatedKey = resultSet.getInt(1);
			}

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

		return generatedKey;
	}
	
	
	
}
