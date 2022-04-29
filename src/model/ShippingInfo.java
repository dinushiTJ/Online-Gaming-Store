/**
 * This is the Shipping Information model class implementation which deals with the
 * shipping info data transfers between relevant servlets and DBUtil classes
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package model;

public class ShippingInfo {
	private int orderId;
	private String fName;
	private String lName;
	private String addLine1;
	private String addLine2;
	private String country;
	private String state;

	public ShippingInfo(int orderId, String fName, String lName, String addLine1, String addLine2, String country,
			String state) {
		this.orderId = orderId;
		this.fName = fName;
		this.lName = lName;
		this.addLine1 = addLine1;
		this.addLine2 = addLine2;
		this.country = country;
		this.state = state;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddLine1() {
		return addLine1;
	}

	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}

	public String getAddLine2() {
		return addLine2;
	}

	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
