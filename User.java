package ex4;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 
 * Class representing a User.
 * 
 * @author Barak Katzir & Ben Zion
 */
public class User implements Serializable {

	private static final long serialVersionUID = 8265121885512552343L;
	private String name;
	private String lastName;
	private String id;
	private String email;
	private String dateOfBirth;
	private String licenseIssuanceDate;
	private String password;
	private boolean isManger;
	private ArrayList<Vehicle> rentedCars;

	/**
	 * 
	 * Constructs a User object with the given parameters.
	 * 
	 * @param name                the user's first name
	 * @param lastName            the user's last name
	 * @param id                  the user's ID
	 * @param email               the user's email
	 * @param dateOfBirth         the user's date of birth
	 * @param licenseIssuanceDate the date the user's license was issued
	 * @param password            the user's password
	 * @param isManger            a boolean indicating if the user is a manager or
	 *                            not
	 */
	public User(String name, String lastName, String id, String email, String dateOfBirth, String licenseIssuanceDate,
			String password, boolean isManger) {
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.licenseIssuanceDate = licenseIssuanceDate;
		this.password = password;
		this.isManger = false;
		this.rentedCars = new ArrayList<Vehicle>();
	}

	/**
	 * 
	 * Rent a car for this user.
	 * 
	 * @param selectedCar the car to be rented
	 * @param startDate   the start date of the rental
	 * @param endDate     the end date of the rental
	 */
	public void rentCar(Vehicle selectedCar, String startDate, String endDate) {
		if (rentedCars == null) {
			rentedCars = new ArrayList<Vehicle>();
		}
		selectedCar.setStartDate(startDate);
		selectedCar.setEndDate(endDate);
		selectedCar.setAvailableForRent(false);
		rentedCars.add(selectedCar);
	}

	/**
	 * 
	 * Returns the email of this user.
	 * 
	 * @return the email of this user.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * Returns the password of this user.
	 * 
	 * @return the password of this user.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * Sets the isManger flag of this user.
	 * 
	 * @param isManger the isManger flag to set
	 */
	public void setManger(boolean isManger) {
		this.isManger = isManger;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the licenseIssuanceDate
	 */
	public String getLicenseIssuanceDate() {
		return licenseIssuanceDate;
	}

	/**
	 * @return the isManger
	 */
	public boolean isManger() {
		return isManger;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, email, id, isManger, lastName, licenseIssuanceDate, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && isManger == other.isManger
				&& Objects.equals(lastName, other.lastName)
				&& Objects.equals(licenseIssuanceDate, other.licenseIssuanceDate) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}

}
