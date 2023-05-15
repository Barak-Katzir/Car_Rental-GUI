/**
 * 
 */
package ex4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author BaraK Katzir & Ben Zion
 *
 */
public class Main {
	public static final User Manger = new User("Mr.", "Manger", "123456789", "admin@gmail.com", "1.1.1993", "2010",
			"1111", true);

	public static void main(String[] args) {
		Data_Mangement data = load();
		Manger.setManger(true);
		if (data == null) {
			data = new Data_Mangement();
			Manger.setManger(true);
			Branch firstBranch = new Branch(1, "Hgolan 10, Tel-Aviv", "08:00 - 20:00");
			Branch secondBranch = new Branch(2, "Hbima 8, Tel-Aviv", "10:00 - 18:00");
			Branch thirdBranch = new Branch(3, "Gezar 27, Tel-Aviv", "09:00 - 22:00");
			Branch fourBranch = new Branch(8, "Pinskr 4, Tel-Aviv", "07:00 - 23:00");
			data.addBranch(firstBranch);
			data.addBranch(secondBranch);
			data.addBranch(thirdBranch);
			data.addBranch(fourBranch);
			Vehicle car1 = new Vehicle(8888, 2000, "KIA", "NIRO", "Mini", "Manual transmission", 600, fourBranch, "",
					"");
			Vehicle car2 = new Vehicle(2222, 1993, "Honda", "Civic", "Mini", "Manual transmission", 1000, firstBranch,
					"", "");
			Vehicle car3 = new Vehicle(1111, 1999, "Honda", "Accord", "Mini", "Manual transmission", 1000, firstBranch,
					"", "");
			data.addVehicle(car1);
			data.addVehicle(car2);
			data.addVehicle(car3);
			data.addUser(Manger);
		}
		IntroFrame intro = new IntroFrame(data);
		intro.setVisible(true);
	}

	private static Data_Mangement load() {
		String filename = "Data_Mangement.ser";
		FileInputStream fis = null;
		ObjectInputStream in = null;
		Data_Mangement data = null;

		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			data = (Data_Mangement) in.readObject();
			in.close();
		} catch (IOException ex) {
			// ex.printStackTrace();
			System.out.println("Creating File...");

		} catch (ClassNotFoundException ex) {
			// ex.printStackTrace();
		}
		return data;

	}
}
