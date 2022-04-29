/**
 * This is the ProcessOrder model class implementation which deals with
 * Orders in calculating discounts for a given order.
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Game;
import model.Order;

public class ProcessOrder {
	
	private Order order;
	private int gamerID;
	private List<Game> myCart;
	
	public ProcessOrder(int gamerID, List<Game> myCart) {
		this.gamerID = gamerID;
		this.myCart = myCart;
	}
	
	//creates a new order using the cart details being sent
	public Order getOrder () {
		double initPayment = getPayment();
		double discount = getDiscount(initPayment);
		
		double payment = initPayment - discount;
		String date = getCurrentDate();
		
		//creating a new order
		order = new Order(gamerID, date, payment, discount,"pending");
		return order;
	}

	//returns formatted current date
	private String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}

	//calculates the discount for a given order payment
	private double getDiscount(double initPayment) {
		int numberOfGames = myCart.size();
		
		if (numberOfGames >= 3) {
			return initPayment *((double) 5.0 / 100);
		} else {
			return 0;
		}
	}

	//calculates the total payment of an order based on the game prices
	private double getPayment () {
		double totalPayment = 0;
		for (Game tempGame: myCart) {
			totalPayment += tempGame.getPrice();
		}
		return totalPayment;
	}
	
	
}
