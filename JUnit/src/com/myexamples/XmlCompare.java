package com.myexamples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.custommonkey.xmlunit.*;
import org.xml.sax.SAXException;

import junit.framework.*;

public class XmlCompare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = "<abc             attr=\"value1\"                title=\"something\">            </abc>";
		assertXMLEquals("<abc attr=\"value1\" title=\"something\"></abc>", result);
		Test2();
	}

	//https://docs.oracle.com/database/121/ADXDK/adx_j_diff.htm#ADXDK204
	//http://stackoverflow.com/questions/34410803/how-to-compare-and-get-difference-between-two-xml-files-using-java
	//http://www.adeveloperdiary.com/java/xml/compare-2-xml-in-java-part-1/
	public static void Test2() {
//		if (logger.isDebugEnabled()) {
//			logger.debug("This is debug : " + parameter);
//		}
//
//		if (logger.isInfoEnabled()) {
//			logger.info("This is info : " + parameter);
//		}
//
//		logger.warn("This is warn : " + parameter);
//		logger.error("This is error : " + parameter);
//		logger.fatal("This is fatal : " + parameter);
		
		File f1 = new File("resources/test.xml");
		File f2 = new File("resources/test2.xml");
		File f3 = new File("resources/xmlDifference.log");

		try {
			// create a new file if it doesn't
			f3.createNewFile();

		} catch (IOException e) {

			e.printStackTrace();
		}
		FileReader fr1 = null;
		FileReader fr2 = null;
		try {
			fr1 = new FileReader(f1);
			fr2 = new FileReader(f2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Diff diff = new Diff(fr1, fr2);
			System.out.println("Similar? " + diff.similar());
			System.out.println("Identical? " + diff.identical());

			DetailedDiff detDiff = new DetailedDiff(diff);
			 java.util.List  differences = detDiff.getAllDifferences();
			for (Object object : differences) {
				Difference difference = (Difference) object;
				System.out.println("***********************");
				System.out.println(difference);
				System.out.println("***********************");
			}

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}


	// public class void test() {
	// String result = "<root></root>";
	// String expected = "<root> </root>";
	//
	// // ignore whitespace differences
	// //
	// https://github.com/xmlunit/user-guide/wiki/Providing-Input-to-XMLUnit#whitespacestrippedsource
	// assertThat(result, isIdenticalTo(new
	// WhitespaceStrippedSource(Input.from(expected).build())));
	//
	// assertThat(result, isIdenticalTo(Input.from(expected).build())); // will
	// fail due to whitespace differences
	// }
	//
	public static void assertXMLEquals(String expectedXML, String actualXML) {
		try {
			XMLUnit.setIgnoreWhitespace(true);
			XMLUnit.setIgnoreAttributeOrder(true);

			DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML));

			List<?> allDifferences = diff.getAllDifferences();
			Assert.assertEquals("Differences found: " + diff.toString(), 0, allDifferences.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
