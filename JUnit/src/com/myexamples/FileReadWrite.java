package com.myexamples;

import java.io.*;

public class FileReadWrite {

	public static void main(String[] args) {
		try {
			CopyFile1();
			CopyFile2();
			ReadConsole();
			FileStreamTest();
			ReadDir();
			InputStreamToString(null);		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static void CopyFile1() throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream("input.txt");
			out = new FileOutputStream("output.txt");

			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static void CopyFile2() throws IOException {
		FileReader in = null;
		FileWriter out = null;

		try {
			in = new FileReader("input.txt");
			out = new FileWriter("output.txt");

			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static void ReadConsole() throws IOException {
		InputStreamReader cin = null;

		try {
			cin = new InputStreamReader(System.in);
			System.out.println("Enter characters, 'q' to quit.");
			char c;
			do {
				c = (char) cin.read();
				System.out.print(c);
			} while (c != 'q');
		} finally {
			if (cin != null) {
				cin.close();
			}
		}
	}

	public static void FileStreamTest() {

		try {
			byte bWrite[] = { 11, 21, 3, 40, 5 };
			OutputStream os = new FileOutputStream("test.txt");
			for (int x = 0; x < bWrite.length; x++) {
				os.write(bWrite[x]); // writes the bytes
			}
			os.close();

			InputStream is = new FileInputStream("test.txt");
			int size = is.available();

			for (int i = 0; i < size; i++) {
				System.out.print((char) is.read() + "  ");
			}
			is.close();
		} catch (IOException e) {
			System.out.print("Exception");
			e.printStackTrace();
		}
	}

	public static void ReadDir() {
		File file = null;
		String[] paths;

		try {
			// create new file object
			file = new File("/tmp");

			// array of files and directory
			paths = file.list();

			// for each name in the path array
			for (String path : paths) {
				// prints filename and directory name
				System.out.println(path);
			}
		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}
	}
	

	public static String InputStreamToString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}

	
}
