package com.myexamples;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
//import org.junit.Test;

public class XMLToJson {
    private static final String XML_TEXT = "<note>\n" +
            "<to>Tove</to>\n" +
            "<from>Jani</from>\n" +
            "<heading>Reminder</heading>\n" +
            "<body>Don't forget me this weekend!</body>\n" +
            "</note>";
    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;

    public static void main(String[] args) {
    	XMLToJsonExample1();
    	XMLToJsonExample2();
    	JSONToXML1();
    }
    
    public static void XMLToJsonExample1() {
        JSONObject xmlJSONObj = XML.toJSONObject(XML_TEXT);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        System.out.println(jsonPrettyPrintString);
    }
    
    //http://www.thejavaprogrammer.com/convert-json-to-xml-or-xml-to-json-in-java/
	public static void JSONToXML1(){
		String json_data = "{\"student\":{\"name\":\"Neeraj Mishra\", \"age\":\"22\"}}";
		JSONObject obj = new JSONObject(json_data);
		//converting json to xml
		String xml_data = XML.toString(obj);
		System.out.println(xml_data);
	}

    
    
    public static void XMLToJsonExample2() {
        
    }
    
    
    
    
}