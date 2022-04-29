/**
 * This is the GamerProfile model class implementation which helps to
 * transfer data between relevant servlets and DBUtil classes
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package model;

public class GamerProfile {
	private int points;
	private int userID;
	private String username;
	private String email;
	private String password;
	
	public GamerProfile() {}
	
	public GamerProfile(int userID, String username, String email, String password, int points) {
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.password = password;
		this.points = points;
	}

	public GamerProfile(int points, int userID, String username, String email, String password) {
		super();
		this.points = points;
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
