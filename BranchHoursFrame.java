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
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Barak Katzir & Ben Zion
 *
 */
public class BranchHoursFrame extends JFrame {
	private Data_Mangement data;
	private User user;
	private Choice branch;
	private JLabel branchLabel;
	private JButton displayHours;
	private JLabel openingHours;
	private JButton back;

	/**
	 * 
	 * Creates a new BranchHoursFrame object
	 * 
	 * @param data The Data_Mangement object for the system
	 * 
	 * @param user The User object of the logged in user
	 */
	public BranchHoursFrame(Data_Mangement data, User user) {
		super("Opening Hours");
		this.data = data;
		this.user = user;
		branch = new Choice();
		branchLabel = new JLabel("Choose A Branch:");
		displayHours = new JButton("Press To Show Opening Hours");
		back = new JButton("Back");
		openingHours = new JLabel();

		initializeChoice();
		initialize();
	}

	/**
	 * The initialize method sets the default close operation of the JFrame, sets
	 * the location of the frame to the center of the screen, creates a JPanel for
	 * the view, sets the border for the view, adds the view to the frame, sets the
	 * layout for the view to a BoxLayout with the Y_AXIS, adds the
	 * addP,addDispLabel and addButton methods to the view, sets the color of the
	 * errorLabel to red, and packs the frame.
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel view = new JPanel();
		view.setBorder(new EmptyBorder(10, 10, 20, 10));
		add(view);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		view.add(addP());
		view.add(addDispLabel());
		view.add(addButton());
		openingHours.setForeground(Color.BLUE);
		pack();

		displayHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayHours();
			}
		});

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
	}

	/**
	 * The addP method creates a JPanel with a GridLayout and adds labels and Choice
	 * for the branch to the panel.
	 * 
	 * @return the panel for the form
	 */
	private JPanel addP() {
		JPanel p = new JPanel(new GridLayout(0, 2));
		p.add(branchLabel);
		p.add(branch);
		return p;
	}

	/**
	 * The addButton method creates a JPanel with a GridLayout and adds the back
	 * button and displayHours to the panel.
	 * 
	 * @return the panel for the button
	 */
	private JPanel addButton() {
		JPanel disp_button = new JPanel(new GridLayout(0, 2));
		disp_button.add(back);
		disp_button.add(displayHours);
		return disp_button;
	}

	/**
	 * The addDispLabel method creates a JPanel with a FlowLayout and adds the
	 * JLabel to the panel.
	 * 
	 * @return the panel for the Label
	 */
	private JPanel addDispLabel() {
		JPanel disp_label = new JPanel(new FlowLayout());
		disp_label.add(openingHours);
		return disp_label;
	}

	/**
	 * 
	 * The method initializes the choices for branch
	 * 
	 * it will fill the branch choices with the branches already in the system.
	 */
	private void initializeChoice() {
		for (Branch b : data.getBranches().values()) {
			branch.add(b.getAddress());
		}
	}

	/**
	 * 
	 * The method is used to display the label with every branch opening hours
	 */
	public void displayHours() {
		String selectedBranch = branch.getSelectedItem();
		for (Branch b : data.getBranches().values()) {
			if (b.getAddress().equals(selectedBranch)) {
				openingHours.setText("Opening hours are at: " + b.getOpeningHours());
				break;
			}
		}
	}

	/**
	 * 
	 * The method is closing the currant window and going back
	 */
	public void goBack() {
		CustomersNavFrame client_frame = new CustomersNavFrame(this.data, this.user);
		client_frame.setVisible(true);
		this.dispose();
	}
}
