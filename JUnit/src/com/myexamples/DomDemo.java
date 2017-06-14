package com.myexamples;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.xml.bind.*;
import javax.xml.parsers.*;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import com.model.classes.Farm;
import com.model.classes.Response;

public class DomDemo {

	// http://stackoverflow.com/questions/11465653/how-to-unmarshall-soap-response-using-jaxb-if-namespace-declaration-is-on-soap-e
	public static void main(String[] args) throws Exception {
		SOAPMessageParse();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = db.parse(new File("resources/input.xml"));
		Node getNumberResponseElt = d.getElementsByTagNameNS("http://example.com/", "getNumberResponse").item(0);

		JAXBContext jc = JAXBContext.newInstance(Response.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		JAXBElement<Response> je = unmarshaller.unmarshal(new DOMSource(getNumberResponseElt), Response.class);
		System.out.println(je.getName());
		System.out.println(je.getValue());
		System.out.println(je.getValue().getNumber());
		System.out.println(je.getValue().getMyName());

		String example = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header /><soapenv:Body><ns2:farm xmlns:ns2=\"http://adamish.com/example/farm\"><horse height=\"123\" name=\"glue factory\"/></ns2:farm></soapenv:Body></soapenv:Envelope>";
		SOAPMessage message = MessageFactory.newInstance().createMessage(null,
				new ByteArrayInputStream(example.getBytes()));
		Unmarshaller unmarshaller1 = JAXBContext.newInstance(Farm.class).createUnmarshaller();
		Farm farm = (Farm) unmarshaller1.unmarshal(message.getSOAPBody().extractContentAsDocument());

	}

	private static String APPLICATION_XML = "application/xml";

	public static void SOAPMessageParse() throws Exception {
		String data = new String(Files.readAllBytes(Paths.get("resources/input.xml")));

		MimeHeaders headers = new MimeHeaders();
		// headers.addHeader("Content-Type", request.getContentType());
		headers.addHeader("Content-Type", "text/xml");
		InputStream xmlStream = new ByteArrayInputStream(data.getBytes());
		// Construct SOAPMessage from xml
		SOAPMessage message = null;
		try {
			message = MessageFactory.newInstance(SOAPConstants.DYNAMIC_SOAP_PROTOCOL).createMessage(headers, xmlStream);
		} catch (SOAPException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
		// get Body of SOAP message
		SOAPBody body = null;
		try {
			body = message.getSOAPBody();
		} catch (SOAPException e) {
			e.printStackTrace();

		}
		buildMessageRoot(body);
		// return msg;
		// End of user code
	}

	private static void buildMessageRoot(SOAPBody body) throws Exception {
		Iterator<Node> elemIter = body.getChildElements();
		if (elemIter.hasNext()) {
			Node next = elemIter.next();
			System.out.println(nodeToString(next));

		}
	}

	private static String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			System.out.println("nodeToString Transformer Exception");
		}
		return sw.toString();
	}

}
