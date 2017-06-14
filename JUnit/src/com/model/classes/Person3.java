package com.model.classes;

public class Person3 {
	private String firstname;
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	public PhoneNumber getFax() {
		return fax;
	}

	public void setFax(PhoneNumber fax) {
		this.fax = fax;
	}

	private String lastname;
	private PhoneNumber phone;
	private PhoneNumber fax;

	public Person3(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
		
	}
	
}
