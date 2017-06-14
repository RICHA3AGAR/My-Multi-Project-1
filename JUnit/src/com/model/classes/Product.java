package com.model.classes;

public class Product {
	String name;
	int price;


	public Product(int price, String name) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	

}
