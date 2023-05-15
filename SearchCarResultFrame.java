/**
 * 
 */
package ex4;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * @author Barak Katzir & Ben Zion	
 *
 */
/**
 * 
 * The SearchCarResultFrame class creates a window that show the result of the
 * car search in a JTabel
 */
public class SearchCarResultFrame extends JFrame {
	private Data_Mangement data;
	private User user;
	private JButton rentCar;
	private JButton confirmRentCar;
	private JLabel errorLabel;
	private JLabel successLabel;
	private ArrayList<Vehicle> results;
	private JTextField startDate;
	private JTextField endDate;

	public SearchCarResultFrame(Data_Mangement data, User user, ArrayList<Vehicle> results) {
		super("Opening Hours");
		this.data = data;
		this.user = user;
		this.results = results;
		rentCar = new JButton("Rent Car");
		confirmRentCar = new JButton("Press To Confirm!");
		errorLabel = new JLabel();
		successLabel = new JLabel();
		startDate = new JTextField(20);
		endDate = new JTextField(20);

		initialize();
	}

	/**
	 * The initializeof the JFrame sets the location of the frame to the center of
	 * the screen, creates a JPanel for the view, sets the border for the view, adds
	 * the view to the frame, sets the layout for the view to a BoxLayout with the
	 * Y_AXIS, adds the table,error, success, and addButton methods to the view and
	 * packs the frame.
	 */
	private void initialize() {
		confirmRentCar.setVisible(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(0, 1));
		JTable table = createVehicleTable(results);
		table.setFillsViewportHeight(true);
		table.setBorder(new EmptyBorder(100, 100, 10, 10));
		table.setPreferredScrollableViewportSize(new Dimension(2000, 1000));
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateColumnsFromModel(true);
		JTableHeader header = table.getTableHeader();
		this.add(header, BorderLayout.NORTH);
		JPanel view = new JPanel();
		view.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(view);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		view.add(table);
		view.add(errorLabel());
		view.add(successLabel());
		view.add(addButton());
		pack();
		/**
		 * The rentCar ActionListner when Rent Car clicked it checks if there are no
		 * values in the table or if there was no selection from the user if there was a
		 * choice it will take the data and verify it against the data that holds all
		 * the information. it will display the price per day for the car.
		 */
		rentCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (table.getModel().getRowCount() == 0) {
					errorLabel.setText("No cars at the branch this moment.");
				} else if (selectedRow == -1) {
					errorLabel.setText("Please select a car to rent.");
				} else {
					int carNumber = (int) table.getValueAt(selectedRow, 0);
					Vehicle selectedCar = data.getVehicleByNumber(carNumber);
					if (!selectedCar.isAvailableForRent()) {
						errorLabel.setText("Sorry, this car is not available for rent.");
					} else {
						double pricePerDay = selectedCar.getPricePerday();
						errorLabel.setText("");
						successLabel.setText("The price per day for this vehicle is: " + pricePerDay);
						confirmRentCar.setVisible(true);
					}
				}
			}
		});
		/**
		 * The confirmRentCar ActionListner when Press To Confirm clicked it checks if
		 * there are no values in the table or if there was no selection from the user.
		 * it takes the data and update all the relevant fields. present a Label
		 * according the result and delay the close of the window. at the end it will
		 * save it inside data_mangement
		 */
		confirmRentCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (table.getModel().getRowCount() == 0) {
					errorLabel.setText("No cars at the branch this moment.");
				} else if (selectedRow == -1) {
					errorLabel.setText("Please select a car to rent.");
				} else {
					int carNumber = (int) table.getValueAt(selectedRow, 0);
					Vehicle selectedCar = data.getVehicleByNumber(carNumber);
					selectedCar.setAvailableForRent(false);
					errorLabel.setText("");
					successLabel.setText("You have successfully rented the car.");
					table.setValueAt("false", selectedRow, 8);
					data.saveRentelDate(selectedCar, startDate.getText(), endDate.getText());
					data.updateVehicles(data.getVehicles());
					Timer timer = new Timer(3000, new ActionListener() { // 3000 is the delay time in milliseconds (3
																			// seconds)
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					timer.setRepeats(false); // set to false so the task only runs once
					timer.start();
				}
			}
		});
	}

	/**
	 * The addButton method creates a JPanel with a FlowLayout and adds the rentCar
	 * and confirmRentCar button to the panel.
	 * 
	 * @return the panel for the button
	 */
	private JPanel addButton() {
		JPanel p = new JPanel(new FlowLayout());
		p.add(rentCar);
		p.add(confirmRentCar);
		return p;
	}

	/**
	 * 
	 * The method creates a new panel that contains the error label, which will be
	 * displayed if there is an error in the choice.
	 * 
	 * @return JPanel A new panel that contains the error label.
	 */
	private JPanel errorLabel() {
		JPanel label = new JPanel(new FlowLayout());
		label.add(errorLabel);
		return label;
	}

	/**
	 * 
	 * The method creates a new panel that contains the success label, which will be
	 * displayed if a you rented a car successfully.
	 * 
	 * @return JPanel A new panel that contains the success label.
	 */
	private JPanel successLabel() {
		JPanel label = new JPanel(new FlowLayout());
		label.add(successLabel);
		return label;
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
						"Price Per Day", "Branch Address", "Available For Rent", "Start Date", "End Date" },
				0);
		// Add the data of each vehicle to the table model
		for (Vehicle vehicle : vehicles) {
			model.addRow(new Object[] { vehicle.getCarNumber(), vehicle.getYearOfManufacture(), vehicle.getCarCompany(),
					vehicle.getModel(), vehicle.getCategory(), vehicle.getGearbox(), vehicle.getPricePerday(),
					vehicle.getBranch().getAddress(), vehicle.isAvailableForRent(), startDate.getText(),
					endDate.getText() });
		}
		// Create a JTable with the table model
		JTable table = new JTable(model);

		return table;
	}

}
