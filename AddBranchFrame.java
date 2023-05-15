/**
 * 
 */
package ex4;

import java.awt.BorderLayout;
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
 * It initializes the brunch's ID, branch location, opening hours, and labels
 * 
 * It also has a "Add Branch" button and labels for displaying errors and
 * success messages.
 */
public class AddBranchFrame extends JFrame {
	private Data_Mangement data;
	private User user;
	private JTextField branchID;
	private JTextField location;
	private JTextField opening_hours;
	private JButton addbranch;
	private JLabel errorLabel;
	private JLabel successLabel;

	/**
	 * 
	 * Creates a new AddBranchFrame object
	 * 
	 * @param data The Data_Mangement object for the system
	 * 
	 * @param user The User object of the logged in user
	 */
	public AddBranchFrame(Data_Mangement data, User user) {
		super("Add Branch");
		this.data = data;
		this.user = user;
		branchID = new JTextField(20);
		location = new JTextField(20);
		opening_hours = new JTextField(10);
		addbranch = new JButton("Add Branch");
		errorLabel = new JLabel();
		successLabel = new JLabel("");

		addbranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewBranch();
			}

		});

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
	}

	/**
	 * The addP method creates a JPanel with a GridLayout and adds labels and text
	 * fields for the Branch ID, branch location, opening hours.
	 * 
	 * @return the panel for the form
	 */
	private JPanel addP() {
		JPanel p = new JPanel(new GridLayout(0, 2));
		p.add(new JLabel("Branch ID:"));
		p.add(branchID);
		p.add(new JLabel("Location:"));
		p.add(location);
		p.add(new JLabel("Opening Hours:"));
		p.add(opening_hours);
		return p;
	}

	/**
	 * The addButton method creates a JPanel with a FlowLayout and adds the
	 * addbranch button to the panel.
	 * 
	 * @return the panel for the button
	 */
	private JPanel addButton() {
		JPanel p = new JPanel(new FlowLayout());
		p.add(addbranch);
		return p;
	}

	/**
	 * 
	 * The method creates a new panel that contains the error label, which will be
	 * displayed if there is an error while adding a new branch.
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
	 * The method is used to add a new branch to the system. it will check if the
	 * input fields are empty and if the branch id is already exist, If the input is
	 * valid, it will create a new Branch object and add it to the system, and
	 * update the error and success labels accordingly.
	 */
	public void addNewBranch() {
		if (branchID.getText().isEmpty() || location.getText().isEmpty() || opening_hours.getText().isEmpty()) {
			successLabel.setText("");
			errorLabel.setText("Empty Fields! Please Fill Out All The Information.");
			return;
		}
		int id = Integer.valueOf(branchID.getText());
		if (data.getBranches().containsKey(id)) {
			successLabel.setText("");
			errorLabel.setText("Branch ID Already Exist, Please Provide A New One.");
			return;
		}
		Branch newBranch = new Branch(id, location.getText(), opening_hours.getText());
		data.addBranch(newBranch);
		errorLabel.setText("");
		successLabel.setText("The Branch Added Successfully!");
		branchID.setText("");
		location.setText("");
		opening_hours.setText("");
	}
}
