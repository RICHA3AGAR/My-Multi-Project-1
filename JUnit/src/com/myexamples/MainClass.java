package com.myexamples;

public class MainClass {

	private boolean StringEmptyOrNull(String str) {
		if (str.equals(null) || str.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print("DHANNAJAI");
		SerializeDeserializeToFileDemo aa = new SerializeDeserializeToFileDemo();
		
		//log4jExamples log = new log4jExamples();
		//ConigfileExamples cfg = new ConigfileExamples();

		XMLToJson xj = new XMLToJson();
		StearmExamples se = new StearmExamples();
		Java8Stearm js = new Java8Stearm();

		SerializeDeserializeToFileDemo sdf = new SerializeDeserializeToFileDemo();
		XMLParser xp = new XMLParser();
		JSONParserByGson jg = new JSONParserByGson();
		JSONParserByOrg jo = new JSONParserByOrg();

		GenericMethodTests gm = new GenericMethodTests();
		FileReadWrite frw = new FileReadWrite();
		CollectionExamples ce = new CollectionExamples();
		// TO DO
		JDBCExamples jdbc = new JDBCExamples();
		
	    // TO DO
		MockExamples me = new MockExamples();
		
		// TODO
		ORMExamples oe = new ORMExamples();
		

	}

}
