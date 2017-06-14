package com.myexamples;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.model.classes.Foo1;
import com.model.classes.SomeObject;
import com.model.classes.Staff;
import com.model.classes.VersionedClass;

public class JSONParserByGson {

	public static void main(String[] args) {
		// https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
		ConvertJavaobjecttoJSON();
		ConvertJSONtoJavaobject();
		JSONPath1();
	}

	private static Staff CreateDummyObject() {
		// TODO Auto-generated method stub
		Staff staff = new Staff();
		staff.name = "DHANANJAI";
		staff.age = 1220;
		staff.position = "Employee";
		staff.salary = BigDecimal.valueOf(150000);
		staff.skills = Arrays.asList("foo", "bar");

		return staff;
	}

	public static void ConvertJavaobjecttoJSON() {
		Gson gson = new Gson();
		Staff obj = CreateDummyObject();

		// 1. Java object to JSON, and save into a file

		try (FileWriter file = new FileWriter("C:\\Java_Projects\\file1.json")) {
			gson.toJson(obj, file);
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2. Java object to JSON, and assign to a String
		String jsonInString = gson.toJson(obj);

	}

	public static void ConvertJSONtoJavaobject() {
		Gson gson = new Gson();
		try {
			String paymentMethod = "[{\"paymentType\":\"google_iap\",\"msisdn\":1486890084928,\"operator\":\"maroc_ma\",\"paymentMethod\":\"maroc_ma\",\"reactivationEnabled\":true }]";

			// 1. JSON to Java object, read it from a file.
			Staff staff1 = gson.fromJson(new FileReader("C:\\Java_Projects\\file1.json"), Staff.class);

			// 2. JSON to Java object, read it from a Json String.
			String jsonInString = "{'name' : 'mkyong'}";
			Staff staff2 = gson.fromJson(jsonInString, Staff.class);

			// JSON to JsonElement, convert to String later.
			JsonElement json = gson.fromJson(new FileReader("C:\\Java_Projects\\file1.json"), JsonElement.class);
			String result = gson.toJson(json);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void JSONPath1() {
		Gson gson = new Gson();
		int one1 = gson.fromJson("1", int.class);
		Integer one2 = gson.fromJson("1", Integer.class);
		Long one3 = gson.fromJson("1", Long.class);
		Boolean false1 = gson.fromJson("false", Boolean.class);
		String str = gson.fromJson("\"abc\"", String.class);
		// String anotherStr = gson.fromJson("[\"abc\"]", String.class);

		int[] ints = { 1, 2, 3, 4, 5 };
		String[] strings = { "abc", "def", "ghi" };
		gson.toJson(ints); // ==> prints [1,2,3,4,5]
		gson.toJson(strings); // ==> prints ["abc", "def", "ghi"]
		int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);

		Collection<Integer> ints3 = Arrays.asList(1, 2, 3, 4, 5);
		String json1 = gson.toJson(ints3); // ==> json is [1,2,3,4,5]
		Type collectionType = new TypeToken<Collection<Integer>>() {
		}.getType();
		Collection<Integer> ints4 = gson.fromJson(json1, collectionType);

		String json2 = "[{\"name\":\"mkyong\"}, {\"name\":\"laplap\"}]";
		List<Staff> list = gson.fromJson(json2, new TypeToken<List<Staff>>() {
		}.getType());
		list.forEach(x -> System.out.println(x));

		String json3 = "{\"name\":\"mkyong\", \"age\":33}";
		Map<String, Object> map = gson.fromJson(json3, new TypeToken<Map<String, Object>>() {
		}.getType());
		map.forEach((x, y) -> System.out.println("key : " + x + " , value : " + y));

		SomeObject someObject = new SomeObject("first", "second");
		Gson gson1 = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		String jsonRepresentation = gson1.toJson(someObject);
		System.out.println(jsonRepresentation);

		Gson gson2 = new GsonBuilder().serializeNulls().create();
		Foo1 foo = new Foo1();
		String json4 = gson2.toJson(foo);
		System.out.println(json4);
		json4 = gson2.toJson(null);
		System.out.println(json4);

		VersionedClass versionedObject = new VersionedClass();
		Gson gson3 = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson3.toJson(someObject);
		System.out.println(jsonOutput);
		System.out.println();
		gson3 = new Gson();
		jsonOutput = gson3.toJson(someObject);
		System.out.println(jsonOutput);

		String jsonLine = "	{ 'data': { 'translations': [  {  'translatedText': 'Hello world'  }  ] } }			";
		Map<String, Object> javaRootMapObject1 = new Gson().fromJson(jsonLine, Map.class);

		System.out.println(((Map) ((List) ((Map) (javaRootMapObject1.get("data"))).get("translations")).get(0))
				.get("translatedText"));

	}

}
