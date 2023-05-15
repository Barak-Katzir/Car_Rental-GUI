package ex4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 
 * The Branch class represents a branch of a car rental company. Each branch has
 * a unique id, an address, opening hours, and a list of vehicles.
 * 
 * @author Barak Katzir & Ben Zion
 * 
 */
public class Branch implements Serializable {

	private int id;
	private String address;
	private String openingHours;
	private ArrayList<Vehicle> vehicles;

	/**
	 * Constructs a new Branch object with a given id, address, and opening hours.
	 * 
	 * @param id           the id of the branch
	 * @param address      the address of the branch
	 * @param openingHours the opening hours of the branch
	 */
	public Branch(int id, String address, String openingHours) {
		this.id = id;
		this.address = address;
		this.openingHours = openingHours;
		this.vehicles = new ArrayList<Vehicle>();
	}

	/**
	 * Returns the id of the branch.
	 * 
	 * @return the id of the branch
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the address of the branch.
	 * 
	 * @return the address of the branch
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Returns the opening hours of the branch.
	 * 
	 * @return the opening hours of the branch
	 */
	public String getOpeningHours() {
		return openingHours;
	}

	/**
	 * Adds a vehicle to the list of vehicles at the branch and sets the vehicle's
	 * availability to true.
	 * 
	 * @param v the vehicle to add
	 */
	public void addVehicle(Vehicle v) {
		v.setAvailableForRent(true);
		this.vehicles.add(v);
	}

	/**
	 * Returns the list of vehicles at the branch.
	 * 
	 * @return the list of vehicles at the branch
	 */
	public ArrayList<Vehicle> getVehicleList() {
		return vehicles;
	}

	/**
	 * Returns the hash code value for this branch.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(address, id, openingHours);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		return Objects.equals(address, other.address) && id == other.id
				&& Objects.equals(openingHours, other.openingHours);
	}

	private static final long serialVersionUID = 3061020820037530284L;
}
