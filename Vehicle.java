package ex4;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 
 * The Vehicle class represents a vehicle object that contains information about
 * a specific vehicle. It contains information such as the car number, year of
 * manufacture, car company, model, category, gearbox, price per day, branch,
 * and availability for rent.
 * 
 * @author Barak & Ben Zion
 * 
 */
public class Vehicle implements Serializable {

	private int carNumber;
	private int yearOfManufacture;
	private String carCompany;
	private String model;
	private String category;
	private String gearbox;
	private double pricePerday;
	private boolean availableForRent;
	private Branch branch;
	private String startDate;
	private String endDate;

	/**
	 * Constructs a new Vehicle object with the given car number, year of
	 * manufacture, car company, model, category, gearbox, price per day, branch,
	 * and availability for rent.
	 * 
	 * @param carNumber         the car number of the vehicle
	 * @param yearOfManufacture the year of manufacture of the vehicle
	 * @param carCompany        the car company of the vehicle
	 * @param model             the model of the vehicle
	 * @param category          the category of the vehicle
	 * @param gearbox           the gearbox of the vehicle
	 * @param pricePerday       the price per day of the vehicle
	 * @param branch            the branch of the vehicle
	 * @param startDate         the start date of the vehicle
	 * @param endDate           the end date of the vehicle
	 */
	public Vehicle(int carNumber, int yearOfManufacture, String carCompany, String model, String category,
			String gearbox, double pricePerday, Branch branch, String startDate, String endDate) {

		this.carNumber = carNumber;
		this.yearOfManufacture = yearOfManufacture;
		this.carCompany = carCompany;
		this.model = model;
		this.category = category;
		this.gearbox = gearbox;
		this.pricePerday = pricePerday;
		this.branch = branch;
		this.availableForRent = true;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Gets the availability of the vehicle for rent.
	 * 
	 * @return the availability of the vehicle for rent
	 */
	public boolean isAvailableForRent(boolean isAvailable) {
		return (isAvailable);
	}

	/**
	 * Sets the availability of the vehicle for rent.
	 * 
	 * @param availableForRent the availability of the vehicle for rent
	 */
	public void setAvailableForRent(boolean availableForRent) {
		this.availableForRent = availableForRent;
	}

	/**
	 * Gets the car number of the vehicle.
	 * 
	 * @return the car number of the vehicle
	 */
	public int getCarNumber() {
		return carNumber;
	}

	/**
	 * Gets the year of manufacture of the vehicle.
	 * 
	 * @return the year of manufacture of the vehicle
	 */
	public int getYearOfManufacture() {
		return yearOfManufacture;
	}

	/**
	 * Gets the car company of the vehicle.
	 * 
	 * @return the car company of the vehicle
	 */
	public String getCarCompany() {
		return carCompany;
	}

	/**
	 * 
	 * Getter method for returning the model of a vehicle object
	 * 
	 * @return the model of the vehicle object
	 */
	public String getModel() {
		return model;
	}

	/**
	 * 
	 * Getter method for returning the category of a vehicle object
	 * 
	 * @return the category of the vehicle object
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * Getter method for returning the gearbox of a vehicle object
	 * 
	 * @return the gearbox of the vehicle object
	 */
	public String getGearbox() {
		return gearbox;
	}

	/**
	 * 
	 * Getter method for returning the price per day of a vehicle object
	 * 
	 * @return the price per day of the vehicle object
	 */
	public double getPricePerday() {
		return pricePerday;
	}

	/**
	 * 
	 * Getter method for returning the branch associated with a vehicle object
	 * 
	 * @return the branch object associated with the vehicle
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * @return the availableForRent
	 */
	public boolean isAvailableForRent() {
		return availableForRent;
	}

	@Override
	public String toString() {
		return "Vehicle [carNumber=" + carNumber + ", yearOfManufacture=" + yearOfManufacture + ", carCompany="
				+ carCompany + ", model=" + model + ", category=" + category + ", gearbox=" + gearbox + ", pricePerday="
				+ pricePerday + ", availableForRent=" + availableForRent + ", branch=" + branch + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return availableForRent == other.availableForRent && Objects.equals(branch, other.branch)
				&& Objects.equals(carCompany, other.carCompany) && carNumber == other.carNumber
				&& Objects.equals(category, other.category) && Objects.equals(gearbox, other.gearbox)
				&& Objects.equals(model, other.model)
				&& Double.doubleToLongBits(pricePerday) == Double.doubleToLongBits(other.pricePerday)
				&& yearOfManufacture == other.yearOfManufacture;
	}

	private static final long serialVersionUID = 355196406504083104L;

	public void setStartDate(String startDate2) {
		this.startDate = startDate2;
	}

	public void setEndDate(String endDate2) {
		this.endDate = endDate2;

	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

}