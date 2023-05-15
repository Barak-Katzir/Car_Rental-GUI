/**
 * 
 */
package ex4;

import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Barak Katzir & Ben Zion	
 *
 */
/**
 * 
 * The AddCarFrame class creates a window that allows a manger to add a car to
 * the system.
 * 
 * It takes in a Data_Management object and a User object as parameters.
 * 
 * It initializes the car's ID, manufacture year, car company, model, price per
 * day, category, gearbox, and branch.
 * 
 * It also has a "Add Car" button and labels for displaying errors and success
 * messages.
 */
public class AddCarFrame extends JFrame {
	private Data_Mangement data;
	private User user;
	private JTextField carID;
	private JTextField manufacture_year;
	private JTextField carCompany;
	private JTextField model;
	private Choice category;
	private Choice gearbox;
	private JTextField pricePerDay;
	private Choice branch;
	private JButton addcar;
	private JLabel errorLabel;
	private JLabel successLabel;

	/**
	 * 
	 * Creates a new AddCarFrame object
	 * 
	 * @param data The Data_Mangement object for the system
	 * 
	 * @param user The User object of the logged in user
	 */
	public AddCarFrame(Data_Mangement data, User user) {
		super("Add Car");
		this.data = data;
		this.user = user;
		carID = new JTextField(20);
		manufacture_year = new JTextField(20);
		carCompany = new JTextField(20);
		model = new JTextField(20);
		pricePerDay = new JTextField(20);
		category = new Choice();
		gearbox = new Choice();
		branch = new Choice();
		addcar = new JButton("Add Car");
		errorLabel = new JLabel();
		successLabel = new JLabel();

		initializeChoice();
		initialize();
	}

	/**
	 * The initialize method sets the default close operation of the JFrame, sets
	 * the location of the frame to the center of the screen, creates a JPanel for
	 * the view, sets the border for the view, adds the view to the frame, sets the
	 * layout for the view to a BoxLayout with the Y_AXIS, adds the addP, error,
	 * success, and addButton methods to the view, sets the color of the errorLabel
	 * to red, and packs the frame.
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel view = new JPanel();
		view.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(view);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		view.add(addP());
		view.add(error());
		view.add(success());
		view.add(addButton());
		errorLabel.setForeground(Color.RED);
		pack();
		/**
		 * The addcar button is given an action listener that calls the addNewCar method
		 * when clicked.
		 */
		addcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewCar();
			}

		});

	}

	/**
	 * The addP method creates a JPanel with a GridLayout and adds labels and text
	 * fields for the car ID, manufacture year, car company, model, car category,
	 * car gearbox, price per day, and branch to the panel.
	 * 
	 * @return the panel for the form
	 */
	private JPanel addP() {
		JPanel p = new JPanel(new GridLayout(0, 2));
		p.add(new JLabel("Car ID:"));
		p.add(carID);
		p.add(new JLabel("Manufacture Year:"));
		p.add(manufacture_year);
		p.add(new JLabel("Car Company:"));
		p.add(carCompany);
		p.add(new JLabel("Model:"));
		p.add(model);
		p.add(new JLabel("Car category:"));
		p.add(category);
		p.add(new JLabel("Car gearbox:"));
		p.add(gearbox);
		p.add(new JLabel("Price Per Day:"));
		p.add(pricePerDay);
		p.add(new JLabel("Add Car To Branch:"));
		p.add(branch);
		return p;
	}

	/**
	 * The addButton method creates a JPanel with a FlowLayout and adds the addcar
	 * button to the panel.
	 * 
	 * @return the panel for the button
	 */
	private JPanel addButton() {
		JPanel p = new JPanel(new FlowLayout());
		p.add(addcar);
		return p;
	}

	/**
	 * 
	 * The method creates a new panel that contains the error label, which will be
	 * displayed if there is an error while adding a new car.
	 * 
	 * @return JPanel A new panel that contains the error label.
	 */
	private JPanel error() {
		JPanel label = new JPanel(new FlowLayout());
		label.add(errorLabel);
		return label;
	}

	/**
	 * 
	 * The method creates a new panel that contains the success label, which will be
	 * displayed if a new car is added successfully.
	 * 
	 * @return JPanel A new panel that contains the success label.
	 */
	private JPanel success() {
		JPanel label = new JPanel(new FlowLayout());
		label.add(successLabel);
		return label;
	}

	/**
	 * 
	 * The method initializes the choices for car category, gearbox, and branch.
	 * 
	 * it will fill the choices with the predefined options for category and gearbox
	 * and will fill the branch choices with the branches already in the system.
	 */
	private void initializeChoice() {
		category.add("Mini");
		category.add("Sedan");
		category.add("Execuitive");
		category.add("SUV");
		gearbox.add("Manual transmission");
		gearbox.add("Automatic transmission");
		gearbox.add("Torque converter");
		gearbox.add("Automated-Manual Transmission");
		gearbox.add("Dual-clutch transmissions (DCT)");
		gearbox.add("Continuously variable transmission (CVT)");

		for (Branch b : data.getBranches().values()) {
			branch.add(b.getAddress());
		}
	}

	/**
	 * 
	 * The method is used to add a new car to the system. it will check if the input
	 * fields are empty and if the car id is already exist, it will also check if
	 * the entered manufacture year and price per day are valid. If the input is
	 * valid, it will create a new vehicle object and add it to the system, and
	 * update the error and success labels accordingly.
	 */
	public void addNewCar() {
		if (carID.getText().isEmpty() || manufacture_year.getText().isEmpty() || pricePerDay.getText().isEmpty()) {
			errorLabel.setText("Empty Fields! Please Fill Out All The Information.");
			return;
		}
		int id = Integer.valueOf(carID.getText());
		int year = Integer.valueOf(manufacture_year.getText());
		double price = Double.valueOf(pricePerDay.getText());
		String branchAddress = branch.getSelectedItem();
		for (Branch b : data.getBranches().values()) {
			if (b.getAddress().equals(branchAddress)) {
				Branch branchToAdd = b;
				for (Vehicle v : branchToAdd.getVehicleList()) {
					if (v.getCarNumber() == id) {
						errorLabel.setText("Car ID Already Exist, Please Provide A New One.");
						return;
					}
				}
				Vehicle newVehicle = new Vehicle(id, year, carCompany.getText(), model.getText(),
						category.getSelectedItem(), gearbox.getSelectedItem(), price, branchToAdd, "", "");
				newVehicle.setAvailableForRent(true);
				data.addVehicle(newVehicle);
				branchToAdd.addVehicle(newVehicle);
				break;
			}
		}
		errorLabel.setText("");
		successLabel.setText("The Car Added Successfully!");
		carID.setText("");
		manufacture_year.setText("");
		pricePerDay.setText("");
		carCompany.setText("");
		model.setText("");
	}

}
