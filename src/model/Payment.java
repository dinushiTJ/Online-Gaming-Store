/**
 * This is the Payment model class implementation which deals with the
 * payment data transfers between servlets and DBUtil classes
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package model;

public class Payment {
	private int paymentId;
	private int gamerId;
	private int orderId;
	private String cardType;
	private String nameOnCard;
	private String cardNumber;
	private String expiryDate;
	private String cvv;
	private String paidDate;
	
	public Payment(int gamerId, int orderId, String cardType, String nameOnCard, String cardNumber, String expiryDate,
			String cvv, String paidDate) {
		super();
		this.gamerId = gamerId;
		this.orderId = orderId;
		this.cardType = cardType;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.paidDate = paidDate;
	}

	public Payment(int paymentId, int gamerId, int orderId, String cardType, String nameOnCard, String cardNumber,
			String expiryDate, String cvv, String paidDate) {
		super();
		this.paymentId = paymentId;
		this.gamerId = gamerId;
		this.orderId = orderId;
		this.cardType = cardType;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.paidDate = paidDate;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getGamerId() {
		return gamerId;
	}

	public void setGamerId(int gamerId) {
		this.gamerId = gamerId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	
}
