package com.model.classes;

public class Employee implements java.io.Serializable {
	public String name;
	public String Address;
	public transient int SSN;
	public int number;

	public void mailCheck() {
		System.out.println("Mailing a check to " + name + " " + Address);
	}
}