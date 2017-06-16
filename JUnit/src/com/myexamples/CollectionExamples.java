package com.myexamples;

import java.io.*;
import java.util.*;

import com.model.classes.*;

public class CollectionExamples {

	// https://www.javatpoint.com/collections-in-java
	// http://www.journaldev.com/1260/collections-in-java-tutorial
	// https://www.tutorialspoint.com/java/util/index.htm
	// Java Collection simply means a single unit of objects.
	// Java Collection framework provides many interfaces (Set, List, Queue,
	// Deque etc.)
	// and classes (ArrayList, Vector, LinkedList, PriorityQueue, HashSet,
	// LinkedHashSet, TreeSet etc).
	
	public static void main(String[] args) {
		try {
			//Commited by dhannajai
			
			SortByKeyExample1();
			//SortByValueExample1();
			//SortByKeyExample2();
			
//			HashAndVector();
//			ArrayListExample();
//			LinkedListExample();
//			HashSetExample();
//			LinkedHashSetExample();
//			TreeSetExample();
//			PriorityQueueExample();
//			MapExample();
//			HashMapExample1();
//			HashMapExample2();
//			LinkedHashMap1();
//			LinkedHashMap2();
//			TreeMap1();
//			TreeMap1();
//			Hashtable1();
//			Hashtable2();
//			ArrayListSorting1();
//			ArrayListSorting2();
//			Comparable1();
//			SortingByComparator();
//			Properties1();
//			SystemProperties();
//			Properties2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void HashAndVector() {
		// Creating instances of array, vector and hashtable
		int arr[] = new int[] { 1, 2, 3, 4 };
		Vector<Integer> v = new Vector<Integer>();
		Hashtable<Integer, String> h = new Hashtable<Integer, String>();
		v.addElement(1);
		v.addElement(2);
		h.put(1, "1geeks");
		h.put(3, "3geeks");
		h.put(2, "2geeks");

		// Array instance creation requires [], while Vector
		// and hastable require ()
		// Vector element insertion requires addElement(), but
		// hashtable element insertion requires put()

		// Accessing first element of array, vector and hashtable
		System.out.println(arr[0]);
		System.out.println(v.elementAt(0));
		System.out.println(h.get(1));

		// Array elements are accessed using [], vector elements
		// using elementAt() and hashtable elements using get()
	}

	public static void ArrayListExample() {
		ArrayList<String> list = new ArrayList<String>();// Creating arraylist
		list.add("Ravi");// Adding object in arraylist
		list.add("Vijay");
		list.add("Ravi");
		list.add("Ajay");
		// Traversing list through Iterator
		Iterator itr1 = list.iterator();
		while (itr1.hasNext()) {
			System.out.println(itr1.next());
		}

		ListIterator<String> itr = list.listIterator();
		System.out.println("traversing elements in forward direction...");
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println("traversing elements in backward direction...");
		while (itr.hasPrevious()) {
			System.out.println(itr.previous());
		}

	}

	public static void LinkedListExample() {
		LinkedList<String> al = new LinkedList<String>();
		al.add("Ravi");
		al.add("Vijay");
		al.add("Ravi");
		al.add("Ajay");

		Iterator<String> itr = al.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void HashSetExample() {
		// Creating HashSet and adding elements
		HashSet<String> set = new HashSet<String>();
		set.add("Ravi");
		set.add("Vijay");
		set.add("Ravi");
		set.add("Ajay");
		// Traversing elements
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void LinkedHashSetExample() {
		LinkedHashSet<String> al = new LinkedHashSet<String>();
		al.add("Ravi");
		al.add("Vijay");
		al.add("Ravi");
		al.add("Ajay");
		Iterator<String> itr = al.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void TreeSetExample() {
		// Creating and adding elements
		TreeSet<String> al = new TreeSet<String>();
		al.add("Ravi");
		al.add("Vijay");
		al.add("Ravi");
		al.add("Ajay");
		// Traversing elements
		Iterator<String> itr = al.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void PriorityQueueExample() {
		PriorityQueue<String> queue = new PriorityQueue<String>();
		queue.add("Amit");
		queue.add("Vijay");
		queue.add("Karan");
		queue.add("Jai");
		queue.add("Rahul");
		System.out.println("head:" + queue.element());
		System.out.println("head:" + queue.peek());
		System.out.println("iterating the queue elements:");
		Iterator itr = queue.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		queue.remove();
		queue.poll();
		System.out.println("after removing two elements:");
		Iterator<String> itr2 = queue.iterator();
		while (itr2.hasNext()) {
			System.out.println(itr2.next());
		}
	}

	public static void MapExample() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(100, "Amit");
		map.put(102, "Rahul");
		map.put(101, "Vijay");
		for (Map.Entry m : map.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}

	public static void HashMapExample1() {
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(100, "Amit");
		hm.put(102, "Rahul");
		hm.put(101, "Vijay");
		
		for (Map.Entry m : hm.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}

	public static void HashMapExample2() {
		// create and populate hash map
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(101, "Let us C");
		map.put(103, "Data Communication and Networking");
		map.put(102, "Operating System");
		System.out.println("Values before remove: " + map);
		// Remove value for key 102
		map.remove(102);
		System.out.println("Values after remove: " + map);
	}

	public static void LinkedHashMap1() {
		//Ordered collection
		LinkedHashMap<Integer, String> hm = new LinkedHashMap<Integer, String>();
		hm.put(102, "Rahul");
		hm.put(100, "Amit");
		hm.put(101, "Vijay");
		
		for (Map.Entry m : hm.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}

	public static void LinkedHashMap2() {
		// Create and populate linked hash map
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(103, "Data Communication and Networking");
		map.put(101, "Let us C");
		map.put(102, "Operating System");
		System.out.println("Values before remove: " + map);
		// Remove value for key 102
		map.remove(102);
		System.out.println("Values after remove: " + map);
	}

	public static void TreeMap1() {
		TreeMap<Integer, String> hm = new TreeMap<Integer, String>();
		hm.put(100, "Amit");
		hm.put(103, "Rahul");
		hm.put(102, "Ravi");
		hm.put(101, "Vijay");
		for (Map.Entry m : hm.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}

	public static void TreeMap2() {
		// Create and populate tree map
		Map<Integer, String> map = new TreeMap<Integer, String>();
		map.put(102, "Let us C");
		map.put(103, "Operating System");
		map.put(101, "Data Communication and Networking");
		System.out.println("Values before remove: " + map);
		// Remove value for key 102
		map.remove(102);
		System.out.println("Values after remove: " + map);
	}

	public static void Hashtable1() {
		Hashtable<Integer, String> hm = new Hashtable<Integer, String>();
		hm.put(100, "Amit");
		hm.put(102, "Ravi");
		hm.put(101, "Vijay");
		hm.put(103, "Rahul");

		for (Map.Entry m : hm.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}

	public static void Hashtable2() {
		// create and populate hash table
		Hashtable<Integer, String> map = new Hashtable<Integer, String>();
		map.put(102, "Let us C");
		map.put(103, "Operating System");
		map.put(101, "Data Communication and Networking");
		System.out.println("Values before remove: " + map);
		// Remove value for key 102
		map.remove(102);
		System.out.println("Values after remove: " + map);
	}

	public static void ArrayListSorting1() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("Viru");
		al.add("Saurav");
		al.add("Mukesh");
		al.add("Tahir");

		Collections.sort(al);
		Iterator itr = al.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void ArrayListSorting2() {
		ArrayList al = new ArrayList();
		al.add(Integer.valueOf(201));
		al.add(Integer.valueOf(101));
		al.add(230);// internally will be converted into objects as
					// Integer.valueOf(230)

		Collections.sort(al);
		Iterator itr = al.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void Comparable1() {
		ArrayList<Student> al = new ArrayList<Student>();
		al.add(new Student(101, "Vijay", 23));
		al.add(new Student(106, "Ajay", 27));
		al.add(new Student(105, "Jai", 21));

		Collections.sort(al);
		for (Student st : al) {
			System.out.println(st.rollno + " " + st.name + " " + st.age);
		}
	}

	public static void SortingByComparator() {
		ArrayList<Student> al = new ArrayList<Student>();
		al.add(new Student(101, "Vijay", 23));
		al.add(new Student(106, "Ajay", 27));
		al.add(new Student(105, "Jai", 21));

		System.out.println("Sorting by Name...");
		Collections.sort(al, new NameComparator());
		Iterator itr = al.iterator();
		while (itr.hasNext()) {
			Student st = (Student) itr.next();
			System.out.println(st.rollno + " " + st.name + " " + st.age);
		}

		System.out.println("sorting by age...");
		Collections.sort(al, new AgeComparator());
		Iterator itr2 = al.iterator();
		while (itr2.hasNext()) {
			Student st = (Student) itr2.next();
			System.out.println(st.rollno + " " + st.name + " " + st.age);
		}

	}

	public static void SortByKeyExample1() {
        Map<String, String> unsortMap = new HashMap<String, String>();
        unsortMap.put("Z", "z");
        unsortMap.put("D", "d");
        unsortMap.put("E", "e");
        unsortMap.put("B", "b");
        unsortMap.put("A", "a");
        unsortMap.put("C", "c");
       
        System.out.println("Unsort Map......");
        printMap(unsortMap);

        System.out.println("\nSorted Map......By Key");
        Map<String, String> treeMap = new TreeMap<String, String>(unsortMap);
        printMap(treeMap);
        
        Map<Integer, Integer> unsortMap2 = new HashMap<Integer, Integer>();
        unsortMap2.put(1, 11);
        unsortMap2.put(2, 22);
        System.out.println("Unsort Map2......");
        printMap(unsortMap2);
        
       
    }

	public static void SortByKeyExample2() {

        Map<Integer, String> unsortMap = new HashMap<Integer, String>();
        unsortMap.put(10, "z");
        unsortMap.put(5, "b");
        unsortMap.put(6, "a");
        unsortMap.put(20, "c");
        unsortMap.put(1, "d");
        unsortMap.put(7, "e");
        unsortMap.put(8, "y");
        unsortMap.put(99, "n");
        unsortMap.put(50, "j");
        unsortMap.put(2, "m");
        unsortMap.put(9, "f");

        System.out.println("Unsort Map......");
        printMap(unsortMap);

        System.out.println("\nSorted Map......By Key");
        Map<Integer, String> treeMap = new TreeMap<Integer, String>(new Comparator<Integer>() {

                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }

                });

	    /* For Java 8, try this lambda
		Map<Integer, String> treeMap = new TreeMap<>(
		                (Comparator<Integer>) (o1, o2) -> o2.compareTo(o1)
		        );
		*/
        treeMap.putAll(unsortMap);

        printMap(treeMap);

    }
	
	public static void SortByValueExample1() {

        Map<String, Integer> unsortMap = new HashMap<String, Integer>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("j", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Unsort Map......");
        printMap(unsortMap);

        System.out.println("\nSorted Map......By Value");
        Map<String, Integer> sortedMap = sortByValue(unsortMap);
        printMap(sortedMap);

    }

	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }
	
	
    //pretty print a map
    public static < K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
				+ " Value : " + entry.getValue());
        }
    }
    
	public static void Properties1() throws Exception {
		// user=system
		// password=oracle

		FileReader reader = new FileReader("db.properties");
		Properties p = new Properties();
		p.load(reader);
		System.out.println(p.getProperty("user"));
		System.out.println(p.getProperty("password"));
	}

	public static void SystemProperties() throws Exception {
		Properties p = System.getProperties();
		Set set = p.entrySet();
		Iterator itr = set.iterator();
		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry) itr.next();
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}

	public static void Properties2() throws Exception {
		Properties p = new Properties();
		p.setProperty("name", "Sonoo Jaiswal");
		p.setProperty("email", "sonoojaiswal@javatpoint.com");
		p.store(new FileWriter("info.properties"), "Javatpoint Properties Example");
	}

}
