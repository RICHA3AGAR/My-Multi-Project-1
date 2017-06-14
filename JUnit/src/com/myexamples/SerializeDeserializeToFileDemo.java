package com.myexamples;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import com.model.classes.Customer2;
import com.model.classes.Employee;

public class SerializeDeserializeToFileDemo {

	public static void main(String[] args) {
		SerializeDemo2(); 
		
//		SerializeDemo();
//		DeserializeDemo();
		//CaseSensitiveSerializeDemo1();
		//CaseSensitiveSerializeDemo2();
	}

	public static void SerializeDemo2() 
    {
        try {
			ArrayList<String> list = new ArrayList<String>();
			list.add("1");
			list.add("2");
			list.add("3");
			list.add("4");
			Customer2 c = new Customer2();
			c.setAge(45);
			c.setDesc("some desc ");
			c.setId(23);
			c.setList(list);
			c.setName("name");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer2.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(c, sw);
			String xmlString = sw.toString();
			System.out.println(xmlString);
			
			
			
			
			
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
    }
	
	public static void CaseSensitiveSerializeDemo2(){
		//Customer cust = new Customer(40, "JOHN MARTIS", 50);
		Employee emp = new Employee();
		emp.name = "Reyan ";
		emp.Address = "Phokka Kuan, Ambehta Peer";
		emp.SSN = 11122333;
		emp.number = 101;
		
		String filename = "OutPutData\\custmer1.xml";
		try {
			
			FileOutputStream fos1 = new FileOutputStream(filename);
			java.beans.XMLEncoder xe1 = new java.beans.XMLEncoder(fos1);
			xe1.writeObject(emp);
			xe1.flush();
			xe1.close();
		        
	        XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename));
	        Employee emp1 = (Employee)decoder.readObject();
	        decoder.close();
	        System.out.println(emp1.name);
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void CaseSensitiveSerializeDemo1() {
		Employee emp = new Employee();
		emp.name = "Reyan ";
		emp.Address = "Phokka Kuan, Ambehta Peer";
		emp.SSN = 11122333;
		emp.number = 101;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder xmlEncoder = new XMLEncoder(baos);
		xmlEncoder.writeObject(emp);
		xmlEncoder.close();
		String xml = baos.toString();
		System.out.println(xml);

		XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xml.getBytes()));
		Employee emp1 = (Employee) decoder.readObject();
		decoder.close();
		System.out.println(emp1.name);

	}

	public static void SerializeDemo() {
		Employee e = new Employee();
		e.name = "Reyan ";
		e.Address = "Phokka Kuan, Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;

		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Temp\\employee.xml");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /tmp/employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void DeserializeDemo() {
		Employee e = null;
		try {
			FileInputStream fileIn = new FileInputStream("C:\\Temp\\employee.xml");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Employee) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}

		System.out.println("Deserialized Employee...");
		System.out.println("Name: " + e.name);
		System.out.println("Address: " + e.Address);
		System.out.println("SSN: " + e.SSN);
		System.out.println("Number: " + e.number);
	}

}
