package ex4;

import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
 * The SignUpFrame class creates a frame for signing up new clients. It includes
 * fields for inputting first name, last name, ID, email, birth date, year of
 * license issuance, and password.
 * 
 * It also includes a "Sign Up" button to submit the information and a "Back"
 * button to return to the previous frame.
 * 
 * @author Barak Katzir & Ben Zion
 */
public class SignUpFrame extends JFrame {
	private Data_Mangement data;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField ID;
	private JTextField email;
	private JTextField birthDate;
	private Choice licenseYear;
	private JPasswordField password;
	private JPasswordField password_authentication;
	private JLabel errorLabel;
	private JButton signup;
	private JButton back;

	/**
	 * 
	 * The constructor for the SignUpFrame class. It creates a new frame for signing
	 * up clients, and initializes the input fields, buttons, and choice menu.
	 * 
	 * @param data A Data_Mangement object that contains data management methods
	 */
	public SignUpFrame(Data_Mangement data) {
		super("Sign Up");
		this.data = data;
		firstName = new JTextField(20);
		lastName = new JTextField(20);
		ID = new JTextField(9);
		email = new JTextField(40);
		birthDate = new JTextField(20);
		licenseYear = new Choice();
		password = new JPasswordField(20);
		password_authentication = new JPasswordField(20);
		errorLabel = new JLabel();
		signup = new JButton("Sign Up");
		back = new JButton("Back");

		initializeChoice();
		initialize();
	}

	/**
	 * 
	 * Initialize the SignUpFrame.
	 * 
	 * This method sets the default close operation, location, layout,
	 * 
	 * and adds the necessary panels and buttons. It also sets the
	 * 
	 * ActionListeners for the signup and back buttons.
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel view = new JPanel();
		view.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(view);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		view.add(addP());
		view.add(addButton());
		errorLabel.setForeground(Color.RED);
		pack();

		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClient();
			}
		});

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
	}

	/**
	 * 
	 * Add a panel to the frame containing input fields for the user to fill out.
	 * 
	 * @return a JPanel containing input fields for first name, last name, ID,
	 *         email, birth date, license year, and password.
	 */
	private JPanel addP() {
		JPanel p = new JPanel(new GridLayout(0, 2));
		p.add(new JLabel("First Name:"));
		p.add(firstName);
		p.add(new JLabel("lastName:"));
		p.add(lastName);
		p.add(new JLabel("ID:"));
		p.add(ID);
		p.add(new JLabel("Email:"));
		p.add(email);
		p.add(new JLabel("Date Of Birth (DD./MM./YYYY):"));
		p.add(birthDate);
		p.add(new JLabel("In Which Year Your license Was Issued:"));
		p.add(licenseYear);
		p.add(new JLabel("Password:"));
		p.add(password);
		p.add(new JLabel("Re-Enter:"));
		p.add(password_authentication);
		return p;
	}

	/**
	 * 
	 * Add a panel to the frame containing buttons back, sign up.
	 * 
	 * @return a JPanel containing the buttons
	 */
	private JPanel addButton() {
		JPanel p = new JPanel(new GridLayout(2, 1));
		JPanel flowButton = new JPanel(new FlowLayout());
		JPanel flowLabel = new JPanel(new FlowLayout());
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		flowButton.add(signup);
		flowButton.add(back);
		flowLabel.add(errorLabel);
		p.add(flowLabel);
		p.add(flowButton);
		return p;
	}

	/**
	 * initialize the Choice for the year of getting the license
	 */
	private void initializeChoice() {
		int currentYear = 2023;
		for (int i = 0; i < 120; i++) {
			licenseYear.addItem(Integer.toString(currentYear - i));
		}
	}

	/**
	 * Adding a new user to the system + making validations in the form
	 */
	public void addClient() {
		ArrayList<String> errorList = new ArrayList<String>();
		if (Validation.isEmpty(firstName.getText())) {
			errorList.add("Please Fill In The First Name!");
		}

		if (!Validation.isValidName(firstName.getText())) {
			errorList.add("First Name Can Be With Letters Only!");
		}

		if (Validation.isEmpty(lastName.getText())) {
			errorList.add("Please Fill In The Last Name!");
		}

		if (!Validation.isValidName(lastName.getText())) {
			errorList.add("Last Name Can Be With Letters Only!");
		}

		if (Validation.isEmpty(ID.getText())) {
			errorList.add("Please Fill In The ID!");
		}

		if (!Validation.isValidID(ID.getText())) {
			errorList.add("ID Must Be Numbers From 0-9 Only! (9 Digits)!");
		}

		if (Validation.isEmpty(email.getText())) {
			errorList.add("Please Fill In The Email!");
		}

		if (!Validation.isValidEmail(email.getText())) {
			errorList.add("Email Is Not Valid! Please Enter A Valid Email!");
		}

		if (Validation.isEmpty(birthDate.getText())) {
			errorList.add("Please Fill In Yout Birth Date!");
		}

		if (!Validation.isValidDate(birthDate.getText())) {
			errorList.add("Birth Date Is Not Valid! Please Enter A Valid Date!");
		}

		if (Validation.isEmpty(String.valueOf(password.getPassword()))) {
			errorList.add("Please Fill In A Password!");
		}

		if (!Validation.isValidLengthPassword(String.valueOf(password.getPassword()))) {
			errorList.add("Password Must Be At Least 4 Letters");
		}

		if (!Validation.isValidPasswordAuthentication(String.valueOf(password.getPassword()),
				String.valueOf(password_authentication.getPassword()))) {
			errorList.add("Passwords Does Not Match!");
		}

		if (errorList.size() > 0) {
			errorLabel.setText(errorList.get(0));
			return;
		}
		User c = new User(firstName.getText(), lastName.getText(), ID.getText(), email.getText(), birthDate.getText(),
				licenseYear.getSelectedItem(), String.valueOf(password.getPassword()), false);
		data.addUser(c);
		IntroFrame intro_frame = new IntroFrame(this.data);
		intro_frame.setVisible(true);
		this.dispose();

	}

	/**
	 * back button
	 */
	public void goBack() {
		IntroFrame intro_frame = new IntroFrame(this.data);
		intro_frame.setVisible(true);
		this.dispose();
	}

}
