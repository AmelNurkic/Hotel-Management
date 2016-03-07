package Hotel;

import java.util.*;

public class User {

	// attributes
	String name;
	String surname;
	String gender;
	String IDNumber;
	int age;
	Date checkInTime;
	Date checkOutTime;
	String username;
	String password;

	// empty constructor
	public User() {

	}

	// constructor with attributes
	public User(String name, String surname, String gender, String IDNumber, int age, Date checkInTime,
			Date checkOutTime, String username, String password) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.IDNumber = IDNumber;
		this.age = age;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.username = username;
		this.password = password;
	}

}
