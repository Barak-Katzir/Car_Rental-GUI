/**
 * 
 */
package ex4;

import java.util.regex.Pattern;

/**
 * 
 * The Validation class is responsible for validating user input. It contains
 * several methods that check if a given input is valid according to specific
 * criteria.
 * 
 * @author Barak Katzir & Ben Zion
 * 
 */

public class Validation {

	/**
	 * This method checks if a given string is empty
	 * 
	 * @param myString - The string to check
	 * @return true if the string is empty, false otherwise
	 */
	public static boolean isEmpty(String myString) {
		return myString.length() == 0;
	}

	/**
	 * This method checks if a given string is a valid name
	 * 
	 * @param myString - The string to check
	 * @return true if the string is a valid name, false otherwise
	 */
	public static boolean isValidName(String myString) {
		return myString.matches("[a-zA-Z]+");
	}

	/**
	 * This method checks if a given string is a valid ID
	 * 
	 * @param myString - The string to check
	 * @return true if the string is a valid ID, false otherwise
	 */
	public static boolean isValidID(String myString) {
		return myString.matches("[0-9]+") && myString.length() == 9;
	}

	/**
	 * This method checks if a given string is a valid email address
	 * 
	 * @param myString - The string to check
	 * @return true if the string is a valid email address, false otherwise
	 */
	public static boolean isValidEmail(String myString) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern pat = Pattern.compile(regexPattern);
		return pat.matcher(myString).matches();
	}

	/**
	 * This method checks if a given string is a valid date
	 * 
	 * @param myString - The string to check
	 * @return true if the string is a valid date, false otherwise
	 */
	public static boolean isValidDate(String myString) {
		String[] splits = myString.split("[-./]");
		if (splits.length != 3) {
			return false;
		}
		int day = Integer.parseInt(splits[0]);
		int month = Integer.parseInt(splits[1]);
		int year = Integer.parseInt(splits[2]);
		if (day <= 0 || day > 31 || month <= 0 || month > 12 || year < 1 || year > 2023) {
			return false;
		}
		return true;
	}

	/**
	 * Check if the given string is a valid password length
	 * 
	 * @param myString the string to be checked
	 * @return true if the string is a valid length, false otherwise
	 */
	public static boolean isValidLengthPassword(String myString) {
		return (myString.length() > 3);
	}

	/**
	 * Check if the given string is a valid password authentication
	 * 
	 * @param myString, myString2 the strings to be checked
	 * @return true if the strings match, false otherwise
	 */
	public static boolean isValidPasswordAuthentication(String myString, String myString2) {
		return (myString.equals(myString2));
	}
}