package ex4;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * This class represents the initial frame that the user sees when the program
 * starts. It allows the user to either sign in or sign up.
 * 
 * @author Barak Katzir & Ben Zion
 * 
 */
public class IntroFrame extends JFrame {

	private Data_Mangement data;
	private User user;
	private JButton signUp;
	private JButton signIn;
	private JPasswordField password;
	private JTextField email;
	private JLabel label;
	private JLabel signInValid;

	/**
	 * Constructor for the IntroFrame class. Creates the initial frame and its
	 * components.
	 * 
	 * @param data the data management object that contains all the necessary data
	 *             for the program
	 */
	public IntroFrame(Data_Mangement data) {
		super("Home page");
		this.data = data;
		this.user = user;
		signUp = new JButton("Sign up");
		signIn = new JButton("Sign in");
		email = new JTextField(20);
		password = new JPasswordField(20);
		label = new JLabel("Wellcome 2 Ruppin Rent");
		signInValid = new JLabel();

		initialize();
	}

	/**
	 * Initializes the frame and its components.
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel view = new JPanel();
		view.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(view);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		view.add(addUpper());
		view.add(addp());
		view.add(errorLabel());
		view.add(addb());
		signInValid.setForeground(Color.RED);
		pack();

		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpFrame();
			}
		});
		signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signInUser();
			}
		});

	}

	/**
	 * 
	 * Creates a panel to hold the email and password fields for the user to enter
	 * their credentials
	 * 
	 * @return JPanel containing the email and password fields
	 */
	private JPanel addp() {
		JPanel p = new JPanel(new GridLayout(0, 2));
		p.add(new JLabel("email: "));
		p.add(email);
		p.add(new JLabel("password: "));
		p.add(password);
		return p;
	}

	/**
	 * 
	 * Creates a panel to hold the Sign Up and Sign In buttons
	 * 
	 * @return JPanel containing the Sign Up and Sign In buttons
	 */
	private JPanel addb() {

		JPanel b = new JPanel(new FlowLayout());
		b.add(signUp);
		b.add(signIn);
		return b;
	}

	/**
	 * 
	 * Creates a panel to hold the welcome label
	 * 
	 * @return JPanel containing the welcome label
	 */
	private JPanel addUpper() {

		JPanel b1 = new JPanel(new FlowLayout());
		b1.add(label);
		return b1;
	}

	/**
	 * 
	 * Creates a panel to hold the error message label
	 * 
	 * @return JPanel containing the error message label
	 */
	private JPanel errorLabel() {
		JPanel Valid = new JPanel(new FlowLayout());
		Valid.add(signInValid);
		return Valid;
	}

	/**
	 * 
	 * Opens the Sign Up frame when the Sign Up button is clicked
	 */
	public void signUpFrame() {
		SignUpFrame signup_frame = new SignUpFrame(this.data);
		signup_frame.setVisible(true);
		this.dispose();
	}

	/**
	 * 
	 * Attempts to sign in the user when the Sign In button is clicked
	 */
	public void signInUser() {
		User user = this.data.getUser(email.getText());

		if (!isValidCredentials(user)) {
			return;
		}
		showAppropriateFrame(user);
		closeCurrentFrame();
	}

	private boolean isValidCredentials(User user) {
		if (user == null) {
			signInValid.setText("Failed To Sign In!");
			return false;
		}
		String enteredPassword = String.valueOf(password.getPassword());
		if (!user.getPassword().equals(enteredPassword)) {
			signInValid.setText("Failed To Sign In!");
			return false;
		}
		return true;
	}

	private void showAppropriateFrame(User user) {
		if (user.isManger()) {
			ManagerNavFrame managerFrame = new ManagerNavFrame(this.data, user);
			managerFrame.setVisible(true);
		} else {
			CustomersNavFrame Customers_signin_Frame = new CustomersNavFrame(this.data, user);
			Customers_signin_Frame.setVisible(true);
		}
	}

	private void closeCurrentFrame() {
		this.setVisible(false);
		this.dispose();
	}

}
