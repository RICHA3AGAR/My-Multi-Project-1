package junit.samples;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.myexamples.CollectionExamples;
import com.myexamples.FileReadWrite;
import com.myexamples.GenericMethodTests;
import com.myexamples.JDBCExamples;
import com.myexamples.JSONParserByGson;
import com.myexamples.JSONParserByOrg;
import com.myexamples.Java8Stearm;
import com.myexamples.MockExamples;
import com.myexamples.ORMExamples;
import com.myexamples.SerializeDeserializeToFileDemo;
import com.myexamples.StearmExamples;
import com.myexamples.XMLParser;
import com.myexamples.XMLToJson;

import junit.framework.*;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

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

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SimpleTest extends TestCase {
	protected int fValue1;
	protected int fValue2;

	protected void setUp() throws IOException {
		fValue1 = 2;
		fValue2 = 3;
	}

	public static Test suite() {

		/*
		 * the type safe way
		 *
		 * TestSuite suite= new TestSuite(); suite.addTest( new
		 * SimpleTest("add") { protected void runTest() { testAdd(); } } );
		 * 
		 * suite.addTest( new SimpleTest("testDivideByZero") { protected void
		 * runTest() { testDivideByZero(); } } ); return suite;
		 */

		/*
		 * the dynamic way
		 */
		return new TestSuite(SimpleTest.class);
	}

	public void testAdd() {
		double result = fValue1 + fValue2;
		// forced failure result == 5
		assertTrue(result == 6);
	}

	public void testDivideByZero() {
		int zero = 0;
		int result = 8 / zero;
	}

	public void testEquals() {
		assertEquals(12, 12);
		assertEquals(12L, 12L);
		assertEquals(new Long(12), new Long(12));

		assertEquals("Size", 12, 13);
		assertEquals("Capacity", 12.0, 11.99, 0.0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}