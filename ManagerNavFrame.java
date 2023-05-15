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
 * The ManagerNavFrame class represents the main navigation frame for a manager
 * in the car rental system.
 * 
 * The frame displays options for the manager to add a new car or branch to the
 * system, and also includes
 * 
 * a button for exiting the system. The class also includes methods for opening
 * the AddCarFrame and
 * 
 * AddBranchFrame, as well as a method for closing the current frame.
 * 
 * @author Barak Katzir & Ben Zion
 */
public class ManagerNavFrame extends JFrame {
	private Data_Mangement data;
	private User user;
	private JLabel headline;
	private JButton addcar;
	private JButton addbranch;
	private JButton exit;

	/**
	 * 
	 * Constructor for creating a new ManagerNavFrame.
	 * 
	 * @param data An instance of the Data_Management class containing all relevant
	 *             data for the car rental system.
	 * @param user The user (manager) currently using the system.
	 */
	public ManagerNavFrame(Data_Mangement data, User user) {
		super("Manager Navigation");
		this.data = data;
		this.user = user;
		addcar = new JButton("Add Car");
		addbranch = new JButton("Add Branch");
		exit = new JButton("Exit");
		headline = new JLabel("Welcome" + " " + user.getName() + " " + user.getLastName() + "!");

		addcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCarFrame();
			}
		});

		addbranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBranchFrame();
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});

		initialize();
	}

	/**
	 * 
	 * Initializes the layout and components of the ManagerNavFrame.
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
	 * Creates a panel containing buttons for adding a car or branch to the system,
	 * and for exiting the system.
	 * 
	 * @return The panel containing the buttons.
	 */
	private JPanel addButton() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		p.add(addcar);
		p.add(addbranch);
		p.add(exit);
		return p;
	}

	/**
	 * 
	 * Creates a JPanel and sets the layout to a FlowLayout. Adds a JLabel with the
	 * headline to the panel and returns it.
	 * 
	 * @return a JPanel containing the headline JLabel.
	 */
	private JPanel addLabel() {
		JPanel p = new JPanel(new FlowLayout());
		p.add(headline);
		return p;
	}

	/**
	 * 
	 * Creates an instance of the AddCarFrame class and makes it visible.
	 * 
	 * @param data the data management object passed down from the ManagerNavFrame
	 * @param user the user object passed down from the ManagerNavFrame
	 */
	public void addCarFrame() {
		AddCarFrame car_frame = new AddCarFrame(data, user);
		car_frame.setVisible(true);
	}

	/**
	 * 
	 * Creates an instance of the AddBranchFrame class and makes it visible.
	 * 
	 * @param data the data management object passed down from the ManagerNavFrame
	 * @param user the user object passed down from the ManagerNavFrame
	 */
	public void addBranchFrame() {
		AddBranchFrame branch_frame = new AddBranchFrame(data, user);
		branch_frame.setVisible(true);
	}

	/**
	 * 
	 * Hides the current frame and disposes of it, then exits the application.
	 */
	public void closeFrame() {
		this.setVisible(false);
		this.dispose();
		System.exit(0);
	}
}
