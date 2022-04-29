/**
 * This is the PaymentDBHelper class implementation which deals with the
 * gamer payment related interactions with the database
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

import model.Order;
import model.Payment;
import model.ShippingInfo;

public class PaymentDBHelper {
	private DataSource dSource;

	//overloaded constructor fetches the connection pool resource sent by the servlet
	public PaymentDBHelper(DataSource dSource) {
		this.dSource = dSource;
	}

	//saves a new payment in the db in two tables shipping info and payment.
	public void addPaymentDetails(ShippingInfo shipinfo, Payment payment) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;

		int orderId = shipinfo.getOrderId();
		
		try {
			conn = dSource.getConnection();
			String sqlquery1 = "INSERT INTO `payment`(`gamerID`, `oID`, `card_type`, `name_on_card`, `card_number`, `expiration_date`, `cvv`, `paidDate`) VALUES (?,?,?,?,?,?,?,?);";

			statement = conn.prepareStatement(sqlquery1);
			statement.setInt(1, payment.getGamerId());
			statement.setInt(2, payment.getOrderId());
			statement.setString(3, payment.getCardType());
			statement.setString(4, payment.getNameOnCard());
			statement.setString(5, payment.getCardNumber());
			statement.setString(6, payment.getExpiryDate());
			statement.setString(7, payment.getCvv());
			statement.setString(8, payment.getPaidDate());
			statement.execute();
			statement.close();

			String sqlquery2 = "INSERT INTO `shipping_info`(`oID`, `fname`, `lname`, `addressline1`, `addressline2`, `country`, `state`) VALUES (?,?,?,?,?,?,?)";

			statement = conn.prepareStatement(sqlquery2);
			statement.setInt(1, shipinfo.getOrderId());
			statement.setString(2, shipinfo.getfName());
			statement.setString(3, shipinfo.getlName());
			statement.setString(4, shipinfo.getAddLine1());
			statement.setString(5, shipinfo.getAddLine2());
			statement.setString(6, shipinfo.getCountry());
			statement.setString(7, shipinfo.getState());
			statement.execute();
			statement.close();
			
			String sqlquery3 = "UPDATE `orders` SET `paymentStatus`=? WHERE `oID`=?";
			
			statement = conn.prepareStatement(sqlquery3);
			statement.setString(1, "paid");
			statement.setInt(2, orderId);
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

	//returns all orders fetched from the database as a list of order objects
	public List<Order> getAllOrders() throws SQLException {
		List<Order> allOrders = new ArrayList<>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = dSource.getConnection();
			String sql = "select * from `orders`;";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);

			while(resultSet.next()) {
				int orderid = Integer.parseInt(resultSet.getString("oID"));
				int gamerid = Integer.parseInt(resultSet.getString("gamerID"));
				String orderdate = resultSet.getString("oDate");
				double orderpayment = Double.parseDouble(resultSet.getString("orderPayment"));
				double orderdiscount = Double.parseDouble(resultSet.getString("orderDiscount"));
				String paymentstatus = resultSet.getString("paymentStatus");

				Order order = new Order(orderid, gamerid, orderdate, orderpayment, orderdiscount,paymentstatus);
				allOrders.add(order);
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
		return allOrders;
	}

	//deletes an existing order from db when an order id is sent as an parameter
	public void deleteOrder(int orderid) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = dSource.getConnection();
			String sql = "DELETE FROM `orders` WHERE `oID`=?;";

			statement = conn.prepareStatement(sql);
			statement.setInt(1, orderid);
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
	
	//returns all information about a single order when requested by order id
	public Order getOrder(int orderid) throws NumberFormatException, SQLException {
		Order order = null;

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			conn = dSource.getConnection();
			String sql = "SELECT * FROM `orders` where `oID` = ?";

			statement = conn.prepareStatement(sql);
			statement.setInt(1, orderid);

			resultSet = statement.executeQuery( );

			if (resultSet.next()) {
				int gamerid = Integer.parseInt(resultSet.getString("gamerID"));
				String orderdate = resultSet.getString("oDate");
				double orderpayment = Double.parseDouble(resultSet.getString("orderPayment"));
				double orderdiscount = Double.parseDouble(resultSet.getString("orderDiscount"));
				String paymentstatus = resultSet.getString("paymentStatus");

				order = new Order(orderid, gamerid, orderdate, orderpayment, orderdiscount,paymentstatus);
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
		return order;
	}

	//returns shipping info related to a given order id
	public ShippingInfo getShippingInfo(int orderid) throws SQLException {
		ShippingInfo shipinfo = null;

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			conn = dSource.getConnection();
			String sql = "SELECT * FROM `shipping_info` where `oID` = ?";

			statement = conn.prepareStatement(sql);
			statement.setInt(1, orderid);

			resultSet = statement.executeQuery( );

			if (resultSet.next()) {
				String fname = resultSet.getString("fname");
				String lname = resultSet.getString("lname");
				String add1 = resultSet.getString("addressline1");
				String add2 = resultSet.getString("addressline2");
				String country = resultSet.getString("country");
				String state = resultSet.getString("state");

				shipinfo = new ShippingInfo (orderid, fname, lname, add1, add2, country, state);
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
		return shipinfo;
	}

	//updates existing shipping info in db by order id
	public void updateShippingInfo(ShippingInfo shipinfo) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			conn = dSource.getConnection();
			
			String sqlquery = "UPDATE `shipping_info` SET `fname`=?,`lname`=?,`addressline1`=?,`addressline2`=?,`country`=?,`state`=? WHERE `oID`=?";

			statement = conn.prepareStatement(sqlquery);
			statement.setString(1, shipinfo.getfName());
			statement.setString(2, shipinfo.getlName());
			statement.setString(3, shipinfo.getAddLine1());
			statement.setString(4, shipinfo.getAddLine2());
			statement.setString(5, shipinfo.getCountry());
			statement.setString(6, shipinfo.getState());
			statement.setInt(7, shipinfo.getOrderId());
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
