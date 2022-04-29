/**
 * This is the Game model class implementation which deals with the
 * game objects transfers between servlets and DBUtil classes
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package model;

public class Game {
	private int gID;
	private int adminID;
	private String  name;
	private double price;
	private String releaseDate;
	private String developers;
	private String description;
	private String addedDate;
	private String banner;
	
	public Game(int gID, int adminID, String name, double price, String releaseDate, String developers,
			String description, String addedDate, String banner) {
		this.gID = gID;
		this.adminID = adminID;
		this.name = name;
		this.price = price;
		this.releaseDate = releaseDate;
		this.developers = developers;
		this.description = description;
		this.addedDate = addedDate;
		this.banner = banner;
	}

	public Game(int adminID, String name, double price, String releaseDate, String developers, String description,
			String addedDate, String banner) {
		this.adminID = adminID;
		this.name = name;
		this.price = price;
		this.releaseDate = releaseDate;
		this.developers = developers;
		this.description = description;
		this.addedDate = addedDate;
		this.banner = banner;
	}

	public int getgID() {
		return gID;
	}

	public void setgID(int gID) {
		this.gID = gID;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}
		
	
}
