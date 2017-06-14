package com.myexamples;

import java.lang.reflect.*;
import java.util.*;

import com.model.classes.Person2;
import com.model.classes.PhoneNumbers;

public class ObjectToCSV {

	public static void main(String[] args) {

		try {

			List<PhoneNumbers> lstPhoneNo = new ArrayList<PhoneNumbers>();
			lstPhoneNo.add(new PhoneNumbers("111-222-3333", "Home"));
			lstPhoneNo.add(new PhoneNumbers("999-888-7777", "Work"));

			Person2 person = new Person2("John", "Smith", 25, true, lstPhoneNo);
			StringBuilder sb = new StringBuilder();
			appendAllDeclaredFields(Person2.class, Person2.class.getPackage().getName(), "", sb);
			sb.append("\r\n");
			appendDeclaredFieldValues(Person2.class, Person2.class.getPackage().getName(), person, sb);
			String csvString = sb.toString();
			System.out.println(csvString);
			// Write csvString into file

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void appendAllDeclaredFields(Class c, String rootPackage, String parentName, StringBuilder sb) {
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			Class filedClass = field.getType();
			String fieldName = field.getName();
			// if we have declaredfileds and the filed is in in the same package as root and filed is not Enum, we continue search
			if (filedClass.getDeclaredFields().length > 0 && filedClass.getPackage().getName().contains(rootPackage)
					&& !filedClass.isEnum()) {
				appendAllDeclaredFields(filedClass, rootPackage, getCombinedName(parentName, fieldName), sb);
			} else if (filedClass.getName().contains("java.util.List")) {
				System.out.println(filedClass.getName()); 
				Type type = field.getGenericType();
				ParameterizedType pType = (ParameterizedType) type;
				Class<?> clazz = (Class<?>) pType.getActualTypeArguments()[0];
				System.out.println(clazz); //prints out java.lang.Integer
				appendAllDeclaredFields(clazz, rootPackage, getCombinedName(parentName, fieldName), sb);
				
			}
			// If it is plain fields like String/int/bigDecimal, we append the
			// filed name.
			else {
				sb.append(",").append(getCombinedName(parentName, fieldName));
			}
		}
	}

	static void appendDeclaredFieldValues(Class c, String rootPackage, Object target, StringBuilder sb)
			throws IllegalAccessException {
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			Class filedClass = field.getType();
			field.setAccessible(true);
			Object childObject = null;
			try {
				// try to get the object value from the 'target' Object
				childObject = field.get(target);
			} catch (Exception e) {
				// do nothing, just a try to get value, exception is expected
				// with empty columns
			}
			// if we have declaredfileds and the filed is in in the same package as root and filed is not Enum, we continue search
			if (filedClass.getDeclaredFields().length > 0 && filedClass.getPackage().getName().contains(rootPackage)
					&& !filedClass.isEnum()) {
				appendDeclaredFieldValues(filedClass, rootPackage, childObject, sb);
			}
			else if (filedClass.getName().contains("java.util.List")) {
				System.out.println(filedClass.getName()); 
				Type type = field.getGenericType();
				ParameterizedType pType = (ParameterizedType) type;
				Class<?> clazz = (Class<?>) pType.getActualTypeArguments()[0];
				System.out.println(clazz); //prints out java.lang.Integer
				ArrayList<Object> lstPhoneNo = (ArrayList<Object>)childObject;
				for (Object item : lstPhoneNo) {
					appendDeclaredFieldValues(clazz, rootPackage, item, sb);
				}
				
			}
			// If it is plain fields like String/int/bigDecimal, we append the
			// filed value.
			else {
				// Since this is served as CSV, we do not want the object value
				// contains comma which would break the formatting.
				sb.append(",").append(String.valueOf(childObject).replaceAll(",", "").replaceAll("(\r\n|\n)", ""));
			}

		}
	}

	private static String getCombinedName(String parentName, String fieldName) {
		return "".equals(parentName) ? fieldName : parentName + "_" + fieldName;
	}

}
