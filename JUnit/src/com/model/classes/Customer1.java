package com.model.classes;

public class Customer1  {
	int rollno;
	String name;
	int age;

	Customer1(int rollno, String name, int age) {
		this.rollno = rollno;
		this.name = name;
		this.age = age;
	}

	public int getRollNo() {
		return rollno;
	}

	public void setRollNo(int rollno) {
		this.rollno = rollno;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}