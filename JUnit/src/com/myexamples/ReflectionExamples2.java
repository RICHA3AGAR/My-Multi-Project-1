package com.myexamples;

import java.lang.reflect.*;

public class ReflectionExamples2 {
	
	public static void main(String[] args) {
		//https://www.mkyong.com/java/how-to-use-reflection-to-call-java-method-at-runtime/
		
		System.out.println("START" );
		
		Class noparams[] = {};
		// String parameter
		Class[] paramString = new Class[1];
		paramString[0] = String.class;

		// int parameter
		Class[] paramInt = new Class[1];
		paramInt[0] = Integer.TYPE;

		try {
			// load the AppTest at runtime
			Class cls = Class.forName("model.classes.AppTest");
			Object obj = cls.newInstance();

			// call the printIt method
			Method method = cls.getDeclaredMethod("printIt", noparams);
			method.invoke(obj, null);

			// call the printItString method, pass a String param
			method = cls.getDeclaredMethod("printItString", paramString);
			method.invoke(obj, new String("mkyong"));

			// call the printItInt method, pass a int param
			method = cls.getDeclaredMethod("printItInt", paramInt);
			method.invoke(obj, 123);

			// call the setCounter method, pass a int param
			method = cls.getDeclaredMethod("setCounter", paramInt);
			method.invoke(obj, 999);

			// call the printCounter method
			method = cls.getDeclaredMethod("printCounter", noparams);
			method.invoke(obj, null);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
