package com.model.classes;

public class Person1 {

	// private Address address;
	public Person1(String firstName, String lastName, int age, Boolean isAlive,PhoneNumbers phoneNumbers) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.isAlive = isAlive;
		this.phoneNumbers = phoneNumbers;
	}

	private String lastName;
	private int age;

	private String children;

	private PhoneNumbers phoneNumbers;

	private String firstName;

	private Boolean isAlive;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public PhoneNumbers getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Boolean getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(Boolean isAlive) {
		this.isAlive = isAlive;
	}

	
}
