package ex4;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * @author Barak Katzir & Ben Zion
 *
 */
public class RentCarFrame extends JFrame {

	private Data_Mangement data;
	private User user;
	private JTextField manufacture_year;
	private JTextField carCompany;
	private JTextField model;
	private Choice category;
	private Choice gearbox;
	private Choice branch;
	private JTextField pricePerDay;
	private JButton search;
	private JPanel view;
	private JTextField startDate;
	private JTextField endDate;

	/**
	 * 
	 * Creates a new AddCarFrame object
	 * 
	 * @param data The Data_Mangement object for the system
	 * 
	 * @param user The User object of the logged in user
	 */
	public RentCarFrame(Data_Mangement data, User user) {

		super("Rent A Car");
		this.data = data;
		this.user = user;
		manufacture_year = new JTextField(20);
		carCompany = new JTextField(20);
		model = new JTextField(20);
		pricePerDay = new JTextField(20);
		category = new Choice();
		gearbox = new Choice();
		branch = new Choice();
		search = new JButton("Search");
		view = new JPanel();
		startDate = new JTextField(20);
		endDate = new JTextField(20);

		initializeChoice();
		initialize();
	}

	/**
	 * The initialize method sets the default close operation of the JFrame, sets
	 * the location of the frame to the center of the screen, creates a JPanel for
	 * the view, sets the border for the view, adds the view to the frame, sets the
	 * layout for the view to a BoxLayout with the Y_AXIS, adds the addP and
	 * addButton methods to the view and packs the frame.
	 */
	private void initialize() {

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		view.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(view);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		view.add(addP());
		view.add(addButton());
		pack();
		/**
		 * The search button is given an action listener that calls the searchCar method
		 * when clicked.
		 */
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCar();
			}
		});
	}

	/**
	 * The addP method creates a JPanel with a GridLayout and adds labels and text
	 * fields for the car ID, manufacture year, car company, model, car category,
	 * car gearbox, price per day, branch and Dates to the panel.
	 * 
	 * @return the panel for the form
	 */
	private JPanel addP() {

		JPanel p = new JPanel(new GridLayout(0, 2));
		p.add(new JLabel("Branch:"));
		p.add(branch);
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
		p.add(new JLabel("Pick A Start Date (DD-MM-YYYY):"));
		p.add(startDate);
		p.add(new JLabel("Pick An End Date (DD-MM-YYYY):"));
		p.add(endDate);
		return p;
	}

	/**
	 * The addButton method creates a JPanel with a FlowLayout and adds the search
	 * button to the panel.
	 * 
	 * @return the panel for the button
	 */
	private JPanel addButton() {

		JPanel p = new JPanel(new FlowLayout());
		p.add(search);
		return p;
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
	 * Create vehicle JTable
	 *
	 * @param vehicles the vehicles that are in the system.
	 * @return JTable to present all the information and search in branches.
	 */
	public JTable createVehicleTable(ArrayList<Vehicle> vehicles) {

		// Create a table model with the necessary columns
		DefaultTableModel model = new DefaultTableModel(
				new String[] { "Car Number", "Year of Manufacture", "Car Company", "Model", "Category", "Gearbox",
						"Price per Day", "Branch Address", "Start Date", "End Date" },
				0);
		// Add the data of each vehicle to the table model
		for (Vehicle vehicle : vehicles) {
			model.addRow(new Object[] { vehicle.getCarNumber(), vehicle.getYearOfManufacture(), vehicle.getCarCompany(),
					vehicle.getModel(), vehicle.getCategory(), vehicle.getGearbox(), vehicle.getPricePerday(),
					vehicle.getBranch().getAddress(), startDate.getText(), endDate.getText() });
		}
		// Create a JTable with the table model
		JTable table = new JTable(model);
		return table;
	}

	/**
	 *
	 * Search car open a new frame to show the result of the search inside a JTabel
	 *
	 */
	private void searchCar() {

		ArrayList<Vehicle> results = data.searchVehicles(branch.getSelectedItem(), manufacture_year.getText(),
				carCompany.getText(), model.getText(), category.getSelectedItem(), gearbox.getSelectedItem(),
				pricePerDay.getText(), startDate.getText(), endDate.getText());
		SearchCarResultFrame results_frame = new SearchCarResultFrame(this.data, this.user, results);
		results_frame.setVisible(true);
	}
}
