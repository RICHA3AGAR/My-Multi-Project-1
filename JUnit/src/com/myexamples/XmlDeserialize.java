package com.myexamples;

import com.model.classes.Person3;
import com.model.classes.PhoneNumber;
import com.thoughtworks.xstream.XStream;

public class XmlDeserialize {

	public static void main(String[] args) {
		serilizeAndDesrialize();

	}

	public static void serilizeAndDesrialize() {
		//Person3 joe = new Person3("Joe", "Walnes", new PhoneNumber(123, "1234-456"), new PhoneNumber(123, "9999-999"));
		Person3 joe = new Person3("Joe", "Walnes");
		joe.setPhone(new PhoneNumber(123, "1234-456"));
		joe.setFax(new PhoneNumber(123, "9999-999"));
		
		XStream xstream = new XStream();
		xstream.alias("person", Person3.class);
		xstream.alias("phonenumber", PhoneNumber.class);
		
		String xml = xstream.toXML(joe);
		System.out.println("xml::" + xml);
		
		Person3 newJoe = (Person3)xstream.fromXML(xml);
		System.out.println("AAAA" + newJoe.getLastname());
		
		
	}
	
}
