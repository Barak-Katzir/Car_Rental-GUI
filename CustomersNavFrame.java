/**
 * 
 */
package ex4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * 
 * This class represents the main navigation frame for customers. It is a JFrame
 * that displays the options for customers to view branch opening hours and rent
 * a car.
 * 
 * @author Barak Katzir & Ben Zion
 * 
 */
public class CustomersNavFrame extends JFrame {
	private Data_Mangement data;
	private User user;
	private JLabel headline;
	private JButton branchHours;
	private JButton rentCar;

	/**
	 * Constructor for the class. It creates the frame, sets the data management
	 * object and the current user, creates buttons and labels and sets their action
	 * listeners.
	 * 
	 * @param data - the data management object
	 * @param user - the current user
	 */
	public CustomersNavFrame(Data_Mangement data, User user) {
		super("Customers");
		this.data = data;
		this.user = user;
		branchHours = new JButton("Branch Opening Hours");
		rentCar = new JButton("Rent A Car");
		headline = new JLabel("Welcome" + " " + user.getName() + " " + user.getLastName() + "!");

		branchHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				branchHoursFrame();
			}
		});

		rentCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentCarFrame();
			}
		});

		initialize();
	}

	/**
	 * 
	 * Initializes the CustomersNavFrame by setting the default close operation,
	 * location, and layout. Also adds the headline label and buttons to the view
	 * panel.
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel view = new JPanel();
		view.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(view);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		view.add(addLabel());
		view.add(addButton());
		pack();
	}

	/**
	 * 
	 * Creates a JPanel containing the "Branch Opening Hours" and "Rent A Car"
	 * buttons.
	 * 
	 * @return the JPanel containing the buttons
	 */
	private JPanel addButton() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		p.add(branchHours);
		p.add(rentCar);
		return p;
	}

	/**
	 * 
	 * Creates a JPanel containing the headline label.
	 * 
	 * @return the JPanel containing the headline label
	 */
	private JPanel addLabel() {
		JPanel p = new JPanel(new FlowLayout());
		p.add(headline);
		return p;
	}

	/**
	 * 
	 * Creates and displays a new BranchHoursFrame and disposes of the current
	 * frame.
	 */
	public void branchHoursFrame() {
		BranchHoursFrame branch_info_frame = new BranchHoursFrame(data, user);
		branch_info_frame.setVisible(true);
		this.dispose();
	}

	/**
	 * 
	 * Creates and displays a new RentCarFrame.
	 */
	public void rentCarFrame() {
		RentCarFrame rent_car_frame = new RentCarFrame(data, user);
		rent_car_frame.setVisible(true);
	}
}
