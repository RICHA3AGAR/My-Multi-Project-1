package com.myexamples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParserByOrg{

	public static void main(String[] args) {
		// http://www.json.org/
		// https://www.mkyong.com/java/json-simple-example-read-and-write-json/
		JsonDecodeDemo();

		JsonSimpleWriteExample();
		JsonSimpleReadExample();
	}

	public static void JsonDecodeDemo() {

		JSONParser parser = new JSONParser();
		String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";

		try {
			Object obj = parser.parse(s);
			JSONArray array = (JSONArray) obj;

			System.out.println("The 2nd element of array");
			System.out.println(array.get(1));
			System.out.println();

			JSONObject obj2 = (JSONObject) array.get(1);
			System.out.println("Field \"1\"");
			System.out.println(obj2.get("1"));

			s = "{}";
			obj = parser.parse(s);
			System.out.println(obj);

			s = "[5,]";
			obj = parser.parse(s);
			System.out.println(obj);

			s = "[5,,2]";
			obj = parser.parse(s);
			System.out.println(obj);
		} catch (ParseException pe) {

			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}

	public static void JsonSimpleWriteExample() {

		JSONObject obj = new JSONObject();
		obj.put("name", "mkyong.com");
		obj.put("age", new Integer(100));

		JSONArray list = new JSONArray();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");

		obj.put("messages", list);

		try (FileWriter file = new FileWriter("C:\\Java_Projects\\test2.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print(obj);

	}

	public static void JsonSimpleReadExample() {

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("C:\\Java_Projects\\test2.json"));

			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);

			String name = (String) jsonObject.get("name");
			System.out.println(name);

			long age = (Long) jsonObject.get("age");
			System.out.println(age);

			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("messages");
			Iterator<String> iterator = msg.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
