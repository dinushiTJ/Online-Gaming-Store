/**
 * This is the Order model class implementation which helps with
 * order data transfers between servlets and DBUtil classes
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package model;

public class Order {
	private int oID;
	private int gamerID;
	private String orderDate;
	double orderPayment;
	double orderDiscount;
	String paymentStatus = "pending";

	public Order(int gamerID, String orderDate, double orderPayment, double orderDiscount, String paymentStatus) {
		this.gamerID = gamerID;
		this.orderDate = orderDate;
		this.orderPayment = orderPayment;
		this.orderDiscount = orderDiscount;
		this.paymentStatus = paymentStatus;
	}

	public Order(int oID, int gamerID, String orderDate, double orderPayment, double orderDiscount, String paymentStatus) {
		this.oID = oID;
		this.gamerID = gamerID;
		this.orderDate = orderDate;
		this.orderPayment = orderPayment;
		this.orderDiscount = orderDiscount;
		this.paymentStatus = paymentStatus;
	}

	public int getoID() {
		return oID;
	}

	public void setoID(int oID) {
		this.oID = oID;
	}

	public int getGamerID() {
		return gamerID;
	}

	public void setGamerID(int gamerID) {
		this.gamerID = gamerID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(double orderPayment) {
		this.orderPayment = orderPayment;
	}

	public double getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(double orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	
}
