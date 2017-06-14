package com.myexamples;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.model.classes.DVD;
import com.model.classes.Movie;

//http://viralpatel.net/blogs/java-xml-xpath-tutorial-parse-xml/
//https://www.tutorialspoint.com/java_xml/java_xpath_parse_document.htm
public class XMLParser {

	public static void main(String[] args) {
		SerializeToXML();
		DeserializeFromXML();
		XPathQueryExample();
	}

	public void ObjectToString() throws Exception {

		String SERIALIZED_FILE_NAME = "C:\\Java_Projects\\dvd.xml";
		Movie bourneIndentity = new Movie("The Bourne Identity", 119, "Doug Liman", 2002, "Matt Damon, Franka Potente");
		Movie bourneSupermacy = new Movie("The Bourne Supremacy", 108, "Paul Greengrass", 2004,
				"Matt Damon, Franka Potente, Joan Allen");
		Movie bourneUltimatum = new Movie("The Bourne Ultimatum", 115, "Paul Greengrass", 2007,
				"Matt Damon, Edgar Ramirez, Joan Allen");
		Movie bourneLegacy = new Movie("The Bourne Legacy", 135, "Tony Gilroy", 2012,
				"Jeremy Renner, Rachel Weisz, Edward Norton");

		List<Movie> moviesList = new ArrayList();
		moviesList.add(bourneIndentity);
		moviesList.add(bourneSupermacy);
		moviesList.add(bourneUltimatum);
		moviesList.add(bourneLegacy);

		DVD bourneSeries = new DVD();
		bourneSeries.setMovies(moviesList);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder xmlEncoder = new XMLEncoder(baos);
		xmlEncoder.writeObject(moviesList);
		xmlEncoder.close();

		String xml = baos.toString();
		System.out.println(xml);

	}
	
	public static void SerializeToXML() {
		// https://www.edureka.co/blog/serialization-of-java-objects-to-xml-using-xmlencoder-decoder/
		String SERIALIZED_FILE_NAME = "C:\\Java_Projects\\dvd.xml";
		Movie bourneIndentity = new Movie("The Bourne Identity", 119, "Doug Liman", 2002, "Matt Damon, Franka Potente");
		Movie bourneSupermacy = new Movie("The Bourne Supremacy", 108, "Paul Greengrass", 2004,
				"Matt Damon, Franka Potente, Joan Allen");
		Movie bourneUltimatum = new Movie("The Bourne Ultimatum", 115, "Paul Greengrass", 2007,
				"Matt Damon, Edgar Ramirez, Joan Allen");
		Movie bourneLegacy = new Movie("The Bourne Legacy", 135, "Tony Gilroy", 2012,
				"Jeremy Renner, Rachel Weisz, Edward Norton");

		List<Movie> moviesList = new ArrayList();
		moviesList.add(bourneIndentity);
		moviesList.add(bourneSupermacy);
		moviesList.add(bourneUltimatum);
		moviesList.add(bourneLegacy);

		DVD bourneSeries = new DVD();
		bourneSeries.setMovies(moviesList);

		XMLEncoder encoder = null;
		try {
			FileOutputStream fo = new FileOutputStream(SERIALIZED_FILE_NAME);
			encoder = new XMLEncoder(fo);
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(bourneSeries);
		encoder.close();
	}

	public static void DeserializeFromXML() {
		String SERIALIZED_FILE_NAME = "C:\\Java_Projects\\dvd.xml";
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		DVD bourneSeries = (DVD) decoder.readObject();
		System.out.println(bourneSeries);

	}

	public static void XPathQueryExample() {
		// http://www.journaldev.com/1194/java-xpath-example-tutorial
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse("C:/Java_Projects/employees.xml");

			// Create XPathFactory object
			XPathFactory xpathFactory = XPathFactory.newInstance();

			// Create XPath object
			XPath xpath = xpathFactory.newXPath();

			String name = getEmployeeNameById(doc, xpath, 4);
			System.out.println("Employee Name with ID 4: " + name);

			List<String> names = getEmployeeNameWithAge(doc, xpath, 30);
			System.out.println("Employees with 'age>30' are:" + Arrays.toString(names.toArray()));

			List<String> femaleEmps = getFemaleEmployeesName(doc, xpath);
			System.out.println("Female Employees names are:" + Arrays.toString(femaleEmps.toArray()));

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> getFemaleEmployeesName(Document doc, XPath xpath) {
		List<String> list = new ArrayList<>();
		try {
			// create XPathExpression object
			XPathExpression expr = xpath.compile("/Employees/Employee[gender='Female']/name/text()");
			// evaluate expression result on XML document
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++)
				list.add(nodes.item(i).getNodeValue());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static List<String> getEmployeeNameWithAge(Document doc, XPath xpath, int age) {
		List<String> list = new ArrayList<>();
		try {
			XPathExpression expr = xpath.compile("/Employees/Employee[age>" + age + "]/name/text()");
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++)
				list.add(nodes.item(i).getNodeValue());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static String getEmployeeNameById(Document doc, XPath xpath, int id) {
		String name = null;
		try {
			XPathExpression expr = xpath.compile("/Employees/Employee[@id='" + id + "']/name/text()");
			name = (String) expr.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return name;
	}

}
