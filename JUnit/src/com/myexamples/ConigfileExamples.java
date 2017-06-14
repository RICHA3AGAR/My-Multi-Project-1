package com.myexamples;

import java.io.*;
import java.util.Properties;

public class ConigfileExamples {
	// Q:\Users\D.Kumar1\Google
	// Drive\Java_Program\workspace\JUnit\config.properties
	private static File configFile = new File("config.properties");

	public static void main(String[] args) {
		WriteProperties();
		GetSetMethod1();
		//ReadConfigFile();
	}

	private static void GetSetMethod1() {
		try {
			saveProperties();
			loadProperties();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private static void loadProperties() throws IOException {
		Properties configProps = new Properties();

		// loads properties from file
		InputStream inputStream = new FileInputStream(configFile);
		configProps.load(inputStream);
		inputStream.close();

		System.out.println(configProps.getProperty("host"));
		System.out.println(configProps.getProperty("port"));
		System.out.println(configProps.getProperty("user"));
		System.out.println(configProps.getProperty("pass"));
	}

	private static void saveProperties() throws IOException {
		Properties configProps = new Properties();
		configProps.setProperty("host", "www.codejava.net");
		configProps.setProperty("port", "3306");
		configProps.setProperty("user", "root");
		configProps.setProperty("pass", "secret");

		OutputStream outputStream = new FileOutputStream(configFile);
		configProps.store(outputStream, "host setttings");
		outputStream.close();
	}

	private static void WriteProperties() {
		try {
			Properties configProps = new Properties();
			configProps.setProperty("host", "www.codejava.net");
			configProps.setProperty("port", "3306");
			configProps.setProperty("user", "root");
			configProps.setProperty("pass", "secret");

			// Q:\Users\D.Kumar1\Google
			// Drive\Java_Program\workspace\JUnit\conf1.properties
			FileWriter fw = new FileWriter("conf1.properties");
			configProps.store(fw, "Auther: DHANNAJAI KUMAR as comment");
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private static void ReadConfigFile() {
//		Properties prop = new Properties();
//		InputStream input = null;
//
//		try {
//
//			input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");
//
//			// load a properties file
//			prop.load(input);
//
//			// get the property value and print it out
//			System.out.println(prop.getProperty("database"));
//			System.out.println(prop.getProperty("dbuser"));
//			System.out.println(prop.getProperty("dbpassword"));
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
}
