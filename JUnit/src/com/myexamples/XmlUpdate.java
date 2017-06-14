package com.myexamples;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUpdate {

	public static void main(String[] args) throws Exception {
		//ReWriteXML6();
		// ReWriteXML1();
		// ReWriteXML2();
//		ReWriteXML3();
		// ReWriteXML4();
		
		String xmlContent = new String(Files.readAllBytes(Paths.get("resources/BinqRequest.xml")));
		String expression = "/soap:Envelope/soap:Body/a:Get/a:GetIdentifiers/a:PatientInfoList/a:PatientInfo[1]/b:PatientFirstName";
 		xmlContent = ReWriteXML5(xmlContent, expression, "DHANANAJAI");
 		
 		expression = "/soap:Envelope/soap:Body/a:Get/a:GetIdentifiers/a:MemberInfo/b:AccountNumber";
 		xmlContent = ReWriteXML5(xmlContent, expression, "5555555555");
	}

	public static String ReWriteXML6() throws Exception {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new File("C:/temp/book.xml"));
		// Document doc = builder.parse(new InputSource(new StringReader(xml)));

		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NamespaceContext() {
			@Override
			public Iterator<?> getPrefixes(String arg0) {
				return null;
			}

			@Override
			public String getPrefix(String arg0) {
				return null;
			}

			@Override
			public String getNamespaceURI(String prefix) {
				if (prefix == null) {
					throw new IllegalArgumentException("No prefix provided!");
				} else if (prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
					return "http://univNaSpResolver/book";
				} else if (prefix.equals("books")) {
					return "http://univNaSpResolver/booklist";
				} else if (prefix.equals("fiction")) {
					return "http://univNaSpResolver/fictionbook";
				} else if (prefix.equals("technical")) {
					return "http://univNaSpResolver/sciencebook";
				} else {
					return XMLConstants.NULL_NS_URI;
				}
			}
		});
		try {
			String expression = "/soap:Envelope/soap:Body/a:Get";
			NodeList result1 = (NodeList) xpath.evaluate("books:booklist/technical:book", doc, XPathConstants.NODESET);
	
	        NodeList result2 = (NodeList) xpath.evaluate("books:booklist/fiction:book", doc, XPathConstants.NODESET);
	
	        String result = xpath.evaluate("books:booklist/technical:book/:author", doc);
	
	        
	        
	        // expression =
			// "/soap:Envelope/soap:Body/a:Get/a:GetIdentifiers/a:MemberInfo/b:AccountNumber";
			// expression =
			// "/soapenv:Envelope/soapenv:Header/authInfo/password";
			//Node node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
			// Set the node content
			//node.setTextContent("SSS");

			// Write changes to a file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StringWriter sw = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(sw));
			String newXML = sw.toString();
			System.out.println("newXML:" + newXML);
			return newXML;

		} catch (Exception E) {
			System.out.println(E);
			return "";
		}
	}

	public static String ReWriteXML5(String xml, String expression, String newValue) throws Exception {
		//xml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.web.post.list.com\"><soapenv:Header><authInfo xsi:type=\"soap:authentication\" xmlns:soap=\"http://list.com/services/SoapRequestProcessor\"><!--You may enter the following 2 items in any order--><username xsi:type=\"xsd:string\">dfasf@google.com</username><password xsi:type=\"xsd:string\">PfasdfRem91</password></authInfo></soapenv:Header></soapenv:Envelope>";
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		//Document doc = builder.parse(new File("C:/temp/BinqRequest.xml"));
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NamespaceContext() {
			@Override
			public Iterator<?> getPrefixes(String arg0) {
				return null;
			}

			@Override
			public String getPrefix(String arg0) {
				return null;
			}

			@Override
			public String getNamespaceURI(String prefix) {
				if (prefix == null) {
					throw new IllegalArgumentException("No prefix provided!");
				} else if ("soap".equals(prefix)) {
					return "http://schemas.xmlsoap.org/soap/envelope/";
				}
				// xmlns:a="http://www.hcsc.com/ClientAgreement/inquiry/v4.0"
				// xmlns:b="http://www.hcsc.com/health/ClientAgreement/v4.0">
				else if ("a".equals(prefix)) {
					return "http://www.hcsc.com/ClientAgreement/inquiry/v4.0";
				} else if ("b".equals(prefix)) {
					return "http://www.hcsc.com/health/ClientAgreement/v4.0";
				} else {
					return XMLConstants.NULL_NS_URI;
				}
			}
			
		});
		try {
			//expression = "/soap:Envelope/soap:Body/a:Get/a:GetIdentifiers/a:MemberInfo/b:AccountNumber";
			Node node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
			// Set the node content
			node.setTextContent(newValue);

			// Write changes to a file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StringWriter sw = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(sw));
			String newXML = sw.toString();
			System.out.println("newXML:" + newXML);
			return newXML;

		} catch (Exception E) {
			System.out.println(E);
			return "";
		}
	}

	public static void ReWriteXML4() throws Exception {
		String xml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.web.post.list.com\"><soapenv:Header><authInfo xsi:type=\"soap:authentication\" xmlns:soap=\"http://list.com/services/SoapRequestProcessor\"><!--You may enter the following 2 items in any order--><username xsi:type=\"xsd:string\">dfasf@google.com</username><password xsi:type=\"xsd:string\">PfasdfRem91</password></authInfo></soapenv:Header></soapenv:Envelope>";
		System.out.println(xml);
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NamespaceContext() {
			@Override
			public Iterator<?> getPrefixes(String arg0) {
				return null;
			}

			@Override
			public String getPrefix(String arg0) {
				return null;
			}

			@Override
			public String getNamespaceURI(String arg0) {
				if ("soapenv".equals(arg0)) {
					return "http://schemas.xmlsoap.org/soap/envelope/";
				}
				return null;
			}
		});
		try {
			// String expression =
			// "/soap:Envelope/soap:Body/a:Get/a:GetIdentifiers/a:MemberInfo/b:AccountNumber";
			String expression = "/soapenv:Envelope/soapenv:Header/authInfo/password";
			XPathExpression expr = xpath.compile(expression);
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;

			for (int i = 0; i < nodes.getLength(); i++) {
				Node currentItem = nodes.item(i);
				System.out.println("found getNodeType -> " + currentItem.getNodeType() + " (namespace: "
						+ currentItem.getNodeName() + ")");
				System.out.println("found node -> " + currentItem.getNodeValue() + " (namespace: "
						+ currentItem.getTextContent() + ")");
			}

			// String expression = "/soap:Envelope/soap:Body";
			Node node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);

			// Set the node content
			node.setTextContent("KUMAR");

			// Write changes to a file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(doc), new StreamResult(new File("C:/temp/binq-updated.xml")));

			StringWriter sw = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(sw));
			String newXML = sw.toString();
			System.out.println("newXML:" + newXML);

		} catch (Exception E) {
			System.out.println(E);
		}
	}

	public static void ReWriteXML3() {
		String xml = "<urn:ResponseStatus version=\"1.0\" xmlns:urn=\"urn:camera-org\">\r\n" + //
				"\r\n" + //
				"<urn:requestURL>/CAMERA/Streaming/status</urn:requestURL>\r\n" + //
				"<urn:statusCode>4</urn:statusCode>\r\n" + //
				"<urn:statusString>Invalid Operation</urn:statusString>\r\n" + //
				"<urn:id>0</urn:id>\r\n" + //
				"\r\n" + //
				"</urn:ResponseStatus>";
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new java.io.ByteArrayInputStream(xml.getBytes()));

			XPath xpath = XPathFactory.newInstance().newXPath();
			xpath.setNamespaceContext(new NamespaceContext() {
				public String getNamespaceURI(String prefix) {
					return prefix.equals("urn") ? "urn:camera-org" : null;
				}

				public Iterator<?> getPrefixes(String val) {
					return null;
				}

				public String getPrefix(String uri) {
					return null;
				}
			});
			XPathExpression expr = xpath.compile("//urn:ResponseStatus");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
				Node currentItem = nodes.item(i);
				System.out.println("found node -> " + currentItem.getLocalName() + " (namespace: "
						+ currentItem.getNamespaceURI() + ")");
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ReWriteXML2() throws XMLStreamException {
		// Create a document by parsing a XML file
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document document = builder.parse(new File("C:/temp/BinqRequest.xml"));

			// Get a node using XPath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/soap:Envelope/soap:Body/a:Get/a:GetIdentifiers/a:MemberInfo/b:AccountNumber";
			// String expression = "/soap:Envelope/soap:Body";
			Node node = (Node) xPath.evaluate(expression, document, XPathConstants.NODE);

			// Set the node content
			node.setTextContent("Whatever I want to write");

			// Write changes to a file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(new File("C:/temp/binq-updated.xml")));

		} catch (ParserConfigurationException | TransformerException | SAXException | IOException
				| XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateValueInXmlFile(String fileIn, String fileOut, String xpathExpression, String newValue)
			throws IOException {

		// Set up the DOM evaluator
		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

		try {
			final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			final Document doc = docBuilder.parse(fileIn);

			final XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList) xpath.evaluate(xpathExpression, doc, XPathConstants.NODESET);

			// Update the nodes we found
			for (int i = 0, len = nodes.getLength(); i < len; i++) {
				Node node = nodes.item(i);
				node.setTextContent(newValue);
			}

			// Get file ready to write
			final Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			StreamResult result = new StreamResult(new FileWriter(fileOut));
			transformer.transform(new DOMSource(doc), result);

			// Write file out
			result.getWriter().flush();
			result = null;

		} catch (XPathExpressionException xpee) {
			// LOGGER.error(xpee);
			throw new IOException("Cannot parse XPath.", xpee);
		} catch (DOMException dome) {
			// LOGGER.error(dome);
			throw new IOException("Cannot create DOM tree", dome);
		} catch (TransformerConfigurationException tce) {
			// LOGGER.error(tce);
			throw new IOException("Cannot create transformer.", tce);
		} catch (IllegalArgumentException iae) {
			// LOGGER.error(iae);
			throw new IOException("Illegal Argument.", iae);
		} catch (ParserConfigurationException pce) {
			// LOGGER.error(pce);
			throw new IOException("Cannot create parser.", pce);
		} catch (SAXException saxe) {
			// LOGGER.error(saxe);
			throw new IOException("Error reading XML document.", saxe);
		} catch (TransformerFactoryConfigurationError tfce) {
			// LOGGER.error(tfce);
			throw new IOException("Cannot create transformer factory.", tfce);
		} catch (TransformerException te) {
			// LOGGER.error(te);
			throw new IOException("Cannot write values.", te);
		}
	}

	public static void ReWriteXML1() throws XMLStreamException {
		// Create a document by parsing a XML file
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = builderFactory.newDocumentBuilder();

			Document document = builder.parse(new File("C:/temp/test.xml"));

			// Get a node using XPath
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/project/version";
			Node node = (Node) xPath.evaluate(expression, document, XPathConstants.NODE);

			// Set the node content
			node.setTextContent("Whatever I want to write");

			// Write changes to a file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(new File("C:/temp/test-updated.xml")));

		} catch (ParserConfigurationException | TransformerException | SAXException | IOException
				| XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
