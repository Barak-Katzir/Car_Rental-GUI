package ex4;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 
 * Data_Mangement class which is responsible for managing the data of the car
 * rental system
 * 
 * This class contains HashMap and ArrayList of Users, Branches and Vehicles
 * 
 * It also contains methods to add, get, update, and search for Users, Branches
 * and Vehicles
 * 
 * @author Barak Katzir & Ben Zion
 */
class Data_Mangement implements Serializable {

	private HashMap<String, User> users;
	private HashMap<Integer, Branch> branches;
	private ArrayList<Vehicle> vehicles;

	/**
	 * 
	 * Constructor for Data_Mangement class Initializes HashMap and ArrayList of
	 * Users, Branches and Vehicles
	 */
	public Data_Mangement() {
		this.users = new HashMap<String, User>();
		this.branches = new HashMap<Integer, Branch>();
		this.vehicles = new ArrayList<Vehicle>();
	}

	/**
	 * 
	 * @return the list of vehicles
	 */
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * 
	 * adds a user to the HashMap of users
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		this.users.put(user.getEmail(), user);
		save();
	}

	/**
	 * 
	 * @param email of the user
	 * @return the user with the given email
	 */
	public User getUser(String email) {
		return this.users.get(email);
	}

	/**
	 * 
	 * adds a branch to the HashMap of branches
	 * 
	 * @param branch
	 */
	public void addBranch(Branch branch) {
		this.branches.put(branch.getId(), branch);
		save();
	}

	/**
	 * 
	 * Retrieves a {@link Branch} object associated with the provided branch ID.
	 * 
	 * @param branchID The ID of the branch to retrieve.
	 * @return The {@link Branch} object associated with the provided branch ID, or
	 *         null if no such branch exists.
	 */
	public Branch getBranch(Integer branchID) {
		return this.branches.get(branchID);
	}

	/**
	 * 
	 * Returns an unmodifiable map of all branches in this Data_Mangement object,
	 * where the key is the branch ID and the value is the Branch object.
	 * 
	 * @return An unmodifiable map of all branches in this Data_Mangement object.
	 */
	final public HashMap<Integer, Branch> getBranches() {
		return new HashMap<Integer, Branch>(Collections.unmodifiableMap(branches));
	}

	/**
	 * 
	 * Returns an unmodifiable map of all users in this Data_Mangement object, where
	 * the key is the user's email and the value is the User object.
	 * 
	 * @return An unmodifiable map of all users in this Data_Mangement object.
	 */
	final public HashMap<String, User> getUsers() {
		return new HashMap<String, User>(Collections.unmodifiableMap(users));
	}

	/**
	 * 
	 * Adds the provided {@link Vehicle} object to the list of vehicles in this
	 * Data_Mangement object.
	 * 
	 * @param vehicle The {@link Vehicle} object to add.
	 */
	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
		save();
	}

	/**
	 * 
	 * Updates the list of vehicles in this Data_Mangement object with the provided
	 * updated list of vehicles.
	 * 
	 * @param updatedVehicles The new list of vehicles to set.
	 */
	public void updateVehicles(ArrayList<Vehicle> updatedVehicles) {
		this.vehicles = updatedVehicles;
		save();
	}

	/**
	 * 
	 * Retrieves a {@link Vehicle} object associated with the provided carNumber.
	 * 
	 * @param carNumber The carNumber of the vehicle to retrieve.
	 * @return The {@link Vehicle} object associated with the provided carNumber, or
	 *         null if no such vehicle exists.
	 */
	public Vehicle getVehicleByNumber(int carNumber) {
		for (Vehicle v : vehicles) {
			if (v.getCarNumber() == carNumber) {
				return v;
			}
		}
		return null;
	}

	/**
	 * 
	 * The saveRentelDate method updates the rental start and end date of a vehicle,
	 * sets its availability to false, and saves the changes to the Data_Management
	 * object.
	 * 
	 * @param rentedVehicle The vehicle object whose rental date needs to be
	 *                      updated.
	 * @param startDate     The start date of the rental.
	 * @param endDate       The end date of the rental.
	 */
	public void saveRentelDate(Vehicle rentedVehicle, String startDate, String endDate) {
		rentedVehicle.setStartDate(startDate);
		rentedVehicle.setEndDate(endDate);
		rentedVehicle.setAvailableForRent(false);
		save();
	}

	/**
	 * 
	 * The searchVehicles method searches for vehicles based on the provided search
	 * criteria and returns a list of vehicles that match the criteria.
	 * 
	 * @param branchAddress    The address of the branch where the vehicle is
	 *                         located.
	 * @param manufacture_year The year of manufacture of the vehicle.
	 * @param carCompany       The company that manufactured the vehicle.
	 * @param model            The model of the vehicle.
	 * @param category         The category of the vehicle.
	 * @param gearbox          The type of gearbox of the vehicle.
	 * @param pricePerDay      The price per day to rent the vehicle.
	 * @param startDate        The start date of the rental.
	 * @param endDate          The end date of the rental.
	 * @return An ArrayList of vehicles that match the search criteria.
	 */
	public ArrayList<Vehicle> searchVehicles(String branchAddress, String manufacture_year, String carCompany,
			String model, String category, String gearbox, String pricePerDay, String startDate, String endDate) {
		ArrayList<Vehicle> searchResults = new ArrayList<Vehicle>();
		for (Vehicle car : vehicles) {
			if (!branchAddress.isEmpty() && !car.getBranch().getAddress().equals(branchAddress))
				continue;
			if (!manufacture_year.isEmpty() && car.getYearOfManufacture() != Integer.valueOf(manufacture_year))
				continue;
			if (!carCompany.isEmpty() && !car.getCarCompany().equals(carCompany))
				continue;
			if (!model.isEmpty() && !car.getModel().equals(model))
				continue;
			if (!category.isEmpty() && !car.getCategory().equals(category))
				continue;
			if (!gearbox.isEmpty() && !car.getGearbox().equals(gearbox))
				continue;
			if (!pricePerDay.isEmpty() && car.getPricePerday() != Integer.valueOf(pricePerDay))
				continue;
			searchResults.add(car);

		}
		return searchResults;
	}

	/**
	 * 
	 * The save method saves the Data_Management object to a file called
	 * "Data_Mangement.ser"
	 */
	public void save() {
		String filename = "Data_Mangement.ser";
		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static final long serialVersionUID = 610748386165472706L;

}
