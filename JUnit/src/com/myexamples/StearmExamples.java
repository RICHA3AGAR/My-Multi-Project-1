package com.myexamples;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.model.classes.Bar;
import com.model.classes.Foo;
import com.model.classes.Outer;
import com.model.classes.Person;
import com.model.classes.Product;

public class StearmExamples {

	public static void main(String[] args) {
		System.out.print("in StearmExamples");
		Stearm1();
		Stearm2();
		Filter1();
		Filter2();
		Filter3();
		Filter4();
		Filter5();
		Filter6();
		Filter7();

	}

	public static boolean Stearm1() {
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return true;
		});
		return false;
	}

	public static boolean Stearm2() {
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return true;
		}).forEach(s -> System.out.println("forEach: " + s));
		return false;
		/*
		 * filter: d2 forEach: d2 filter: a2 forEach: a2 filter: b1 forEach: b1
		 * filter: b3 forEach: b3 filter: c forEach: c
		 */
	}

	public static void Filter1() {
		// http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/

		List<String> lines = Arrays.asList("spring", "node", "mkyong");
		List<String> result = lines.stream() // convert list to stream
				.filter(line -> !"mkyong".equals(line)) // we dont like mkyong
				.collect(Collectors.toList()); // collect the output and convert
												// streams to a List

		result.forEach(System.out::println); // output : spring, node

		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);
		//// C1 //// C2

		Arrays.asList("a1", "a2", "a3").stream().findFirst().ifPresent(System.out::println); // a1

		Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println); // a1

		IntStream.range(1, 4).forEach(System.out::println); //// 1 // 2// 3

		Arrays.stream(new int[] { 1, 2, 3 }).map(n -> 2 * n + 1).average().ifPresent(System.out::println); // 5.0

		Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).max()
				.ifPresent(System.out::println); // 3

		IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(System.out::println); //// a1////
																					//// a2////
																					//// a3

		Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));

		stream.anyMatch(s -> true); // ok
		// stream.noneMatch(s -> true); // exception

		Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));

		streamSupplier.get().anyMatch(s -> true); // ok
		streamSupplier.get().noneMatch(s -> true); // ok

	}

	public static void Filter2() {
		// http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/

		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));

		List<Person> filtered = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());
		System.out.println(filtered); // [Peter, Pamela]

		Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));
		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		// age 18: [Max]
		// age 23: [Peter, Pamela]
		// age 12: [David]

		Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));
		System.out.println(averageAge); // 19.0

		IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.age));
		System.out.println(ageSummary);
		// IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000,
		// max=23}

		String phrase = persons.stream().filter(p -> p.age >= 18).map(p -> p.name)
				.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
		System.out.println(phrase);
		// In Germany Max and Peter and Pamela are of legal age.

		Map<Integer, String> map = persons.stream()
				.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + ";" + name2));
		System.out.println(map);
		// {18=Max, 23=Peter;Pamela, 12=David}

		Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner(" | "), // supplier
				(j, p) -> j.add(p.name.toUpperCase()), // accumulator
				(j1, j2) -> j1.merge(j2), // combiner
				StringJoiner::toString); // finisher
		String names = persons.stream().collect(personNameCollector);
		System.out.println(names); // MAX | PETER | PAMELA | DAVID

		List<Foo> foos = new ArrayList<>();
		// create foos
		IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));
		// create bars
		foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
		foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));
		// Bar1 <- Foo1
		// Bar2 <- Foo1
		// Bar3 <- Foo1
		// Bar1 <- Foo2
		// Bar2 <- Foo2
		// Bar3 <- Foo2
		// Bar1 <- Foo3
		// Bar2 <- Foo3
		// Bar3 <- Foo3

		IntStream.range(1, 4).mapToObj(i -> new Foo("Foo" + i))
				.peek(f -> IntStream.range(1, 4).mapToObj(i -> new Bar("Bar" + i + "<-" + f.name)).forEach(f.bars::add))
				.flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));

	}

	public static void Filter3() {
		// http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
		Outer outer = new Outer();
		if (outer != null && outer.nested != null && outer.nested.inner != null) {
			System.out.println(outer.nested.inner.foo);
		}

		Optional.of(new Outer()).flatMap(o -> Optional.ofNullable(o.nested)).flatMap(n -> Optional.ofNullable(n.inner))
				.flatMap(i -> Optional.ofNullable(i.foo)).ifPresent(System.out::println);

		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));
		persons.stream().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2).ifPresent(System.out::println); // Pamela

		Person result = persons.stream().reduce(new Person("", 0), (p1, p2) -> {
			p1.age += p2.age;
			p1.name += p2.name;
			return p1;
		});
		System.out.format("name=%s; age=%s", result.name, result.age);
		// name=MaxPeterPamelaDavid; age=76

		Integer ageSum = persons.stream().reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
		System.out.println(ageSum); // 76

		Integer ageSum1 = persons.stream().reduce(0, (sum, p) -> {
			System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
			return sum += p.age;
		}, (sum1, sum2) -> {
			System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
			return sum1 + sum2;
		});
		// accumulator: sum=0; person=Max
		// accumulator: sum=18; person=Peter
		// accumulator: sum=41; person=Pamela
		// accumulator: sum=64; person=David

		Integer ageSum2 = persons.parallelStream().reduce(0, (sum, p) -> {
			System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
			return sum += p.age;
		}, (sum1, sum2) -> {
			System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
			return sum1 + sum2;
		});
		// accumulator: sum=0; person=Pamela
		// accumulator: sum=0; person=David
		// accumulator: sum=0; person=Max
		// accumulator: sum=0; person=Peter
		// combiner: sum1=18; sum2=23
		// combiner: sum1=23; sum2=12
		// combiner: sum1=41; sum2=35
	}

	public static void Filter4() {
		// http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/

		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		System.out.println(commonPool.getParallelism()); // 3

		Arrays.asList("a1", "a2", "b1", "c2", "c1").parallelStream().filter(s -> {
			System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		}).map(s -> {
			System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		}).forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
		/*
		 * filter: b1 [main] filter: a2 [ForkJoinPool.commonPool-worker-1] map:
		 * a2 [ForkJoinPool.commonPool-worker-1] filter: c2
		 * [ForkJoinPool.commonPool-worker-3] map: c2
		 * [ForkJoinPool.commonPool-worker-3] filter: c1
		 * [ForkJoinPool.commonPool-worker-2] map: c1
		 * [ForkJoinPool.commonPool-worker-2] forEach: C2
		 * [ForkJoinPool.commonPool-worker-3] forEach: A2
		 * [ForkJoinPool.commonPool-worker-1] map: b1 [main] forEach: B1 [main]
		 * filter: a1 [ForkJoinPool.commonPool-worker-3] map: a1
		 * [ForkJoinPool.commonPool-worker-3] forEach: A1
		 * [ForkJoinPool.commonPool-worker-3] forEach: C1
		 * [ForkJoinPool.commonPool-worker-2]
		 */

		Arrays.asList("a1", "a2", "b1", "c2", "c1").parallelStream().filter(s -> {
			System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		}).map(s -> {
			System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		}).sorted((s1, s2) -> {
			System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
			return s1.compareTo(s2);
		}).forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
		/*
		 * filter: c2 [ForkJoinPool.commonPool-worker-3] filter: c1
		 * [ForkJoinPool.commonPool-worker-2] map: c1
		 * [ForkJoinPool.commonPool-worker-2] filter: a2
		 * [ForkJoinPool.commonPool-worker-1] map: a2
		 * [ForkJoinPool.commonPool-worker-1] filter: b1 [main] map: b1 [main]
		 * filter: a1 [ForkJoinPool.commonPool-worker-2] map: a1
		 * [ForkJoinPool.commonPool-worker-2] map: c2
		 * [ForkJoinPool.commonPool-worker-3] sort: A2 <> A1 [main] sort: B1 <>
		 * A2 [main] sort: C2 <> B1 [main] sort: C1 <> C2 [main] sort: C1 <> B1
		 * [main] sort: C1 <> C2 [main] forEach: A1
		 * [ForkJoinPool.commonPool-worker-1] forEach: C2
		 * [ForkJoinPool.commonPool-worker-3] forEach: B1 [main] forEach: A2
		 * [ForkJoinPool.commonPool-worker-2] forEach: C1
		 * [ForkJoinPool.commonPool-worker-1]
		 */

		persons.parallelStream().reduce(0, (sum, p) -> {
			System.out.format("accumulator: sum=%s; person=%s [%s]\n", sum, p, Thread.currentThread().getName());
			return sum += p.age;
		}, (sum1, sum2) -> {
			System.out.format("combiner: sum1=%s; sum2=%s [%s]\n", sum1, sum2, Thread.currentThread().getName());
			return sum1 + sum2;
		});
		/*
		 * accumulator: sum=0; person=Pamela; [main] accumulator: sum=0;
		 * person=Max; [ForkJoinPool.commonPool-worker-3] accumulator: sum=0;
		 * person=David; [ForkJoinPool.commonPool-worker-2] accumulator: sum=0;
		 * person=Peter; [ForkJoinPool.commonPool-worker-1] combiner: sum1=18;
		 * sum2=23; [ForkJoinPool.commonPool-worker-1] combiner: sum1=23;
		 * sum2=12; [ForkJoinPool.commonPool-worker-2] combiner: sum1=41;
		 * sum2=35; [ForkJoinPool.commonPool-worker-2]
		 */

	}

	public static void Filter5() {
		// http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> twoEvenSquares = numbers.stream().filter(n -> {
			System.out.println("filtering " + n);
			return n % 2 == 0;
		}).map(n -> {
			System.out.println("mapping " + n);
			return n * n;
		}).limit(2).collect(Collectors.toList());

		List<String> words = Arrays.asList("Oracle", "Java", "Magazine");
		List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());

		int sum = numbers.stream().reduce(0, (a, b) -> a + b);

		int product = numbers.stream().reduce(1, (a, b) -> a * b);
		int max = numbers.stream().reduce(1, Integer::max);

		IntStream oddNumbers = IntStream.rangeClosed(10, 30).filter(n -> n % 2 == 1);

		Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4);
		int[] numbers1 = { 1, 2, 3, 4 };
		IntStream numbersFromArray = Arrays.stream(numbers1);

		try {
			long numberOfLines = Files.lines(Paths.get("yourFile.txt"), Charset.defaultCharset()).count();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Stream<Integer> numbers2 = Stream.iterate(0, n -> n + 10);
		numbers2.limit(5).forEach(System.out::println); // 0, 10, 20, 30, 40

	}

	public static void Filter6() {
		// http://www.baeldung.com/java-8-streams
		Stream<String> streamEmpty = Stream.empty();

		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.stream();

		Stream<String> streamOfArray = Stream.of("a", "b", "c");

		String[] arr = new String[] { "a", "b", "c" };
		Stream<String> streamOfArrayFull = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

		Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

		Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);

		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

		IntStream intStream = IntStream.range(1, 3);
		LongStream longStream = LongStream.rangeClosed(1, 3);

		Random random = new Random();
		DoubleStream doubleStream = random.doubles(3);

		IntStream streamOfChars = "abc".chars();

		Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");

		Path path = Paths.get("C:\\file.txt");
		try {
			Stream<String> streamOfStrings = Files.lines(path);
			Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
		Optional<String> anyElement = stream.findAny();

		Optional<String> firstElement = stream.findFirst();

		List<String> elements = Stream.of("a", "b", "c").filter(element -> element.contains("b"))
				.collect(Collectors.toList());
		Optional<String> anyElement1 = elements.stream().findAny();
		Optional<String> firstElement1 = elements.stream().findFirst();

		Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1);

		Stream<String> twiceModifiedStream = stream.skip(1).map(element -> element.substring(0, 3));

		List<String> list = Arrays.asList("abc1", "abc2", "abc3");
		long size = list.stream().skip(1).map(element -> element.substring(0, 3)).sorted().count();

		List<String> list1 = Arrays.asList("abc1", "abc2", "abc3");
		counter1 = 0;
		Stream<String> stream1 = list1.stream().filter(element -> {
			wasCalled();
			return element.contains("2");
		});

		Optional<String> stream2 = list.stream().filter(element -> {
			System.out.println("filter() was called");
			return element.contains("2");
		}).map(element -> {
			System.out.println("map() was called");
			return element.toUpperCase();
		}).findFirst();

		long size1 = list.stream().map(element -> {
			wasCalled();
			return element.substring(0, 3);
		}).skip(2).count();

		long size2 = list.stream().skip(2).map(element -> {
			wasCalled();
			return element.substring(0, 3);
		}).count();

		OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> a + b);

		int reducedTwoParams = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);

		int reducedParams = Stream.of(1, 2, 3).reduce(10, (a, b) -> a + b, (a, b) -> {
			System.out.println("combiner was called");
			return a + b;
		});

		int reducedParallel = Arrays.asList(1, 2, 3).parallelStream().reduce(10, (a, b) -> a + b, (a, b) -> {
			System.out.println("combiner was called");
			return a + b;
		});

		List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));

		List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());

		String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));

		double averagePrice = productList.stream().collect(Collectors.averagingInt(Product::getPrice));

		int summingPrice = productList.stream().collect(Collectors.summingInt(Product::getPrice));

		IntSummaryStatistics statistics = productList.stream().collect(Collectors.summarizingInt(Product::getPrice));

		Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
				.collect(Collectors.groupingBy(Product::getPrice));

		Map<Boolean, List<Product>> mapPartioned = productList.stream()
				.collect(Collectors.partitioningBy(element -> element.getPrice() > 15));

		Set<Product> unmodifiableSet = productList.stream()
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));

		Collector<Product, ?, LinkedList<Product>> toLinkedList = Collector.of(LinkedList::new, LinkedList::add,
				(first, second) -> {
					first.addAll(second);
					return first;
				});

		LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);

		Stream<Product> streamOfCollection1 = productList.parallelStream();
		boolean isParallel = streamOfCollection1.isParallel();
		boolean bigPrice = streamOfCollection1.map(product -> product.getPrice() * 12).anyMatch(price -> price > 200);

		IntStream intStreamParallel = IntStream.range(1, 150).parallel();
		boolean isParallel1 = intStreamParallel.isParallel();

		IntStream intStreamSequential = intStreamParallel.sequential();
		boolean isParallel2 = intStreamSequential.isParallel();

	}

	public static void Filter7() {
		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("A");
		}).forEach(s -> System.out.println("forEach: " + s));
		// map: d2
		// filter: D2
		// map: a2
		// filter: A2
		// forEach: A2
		// map: b1
		// filter: B1
		// map: b3
		// filter: B3
		// map: c
		// filter: C

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("forEach: " + s));
		// filter: d2
		// filter: a2
		// map: a2
		// forEach: A2
		// filter: b1
		// filter: b3
		// filter: c

		Stream.of("d2", "a2", "b1", "b3", "c").sorted((s1, s2) -> {
			System.out.printf("sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("forEach: " + s));
		/*
		 * sort: a2; d2 sort: b1; a2 sort: b1; d2 sort: b1; a2 sort: b3; b1
		 * sort: b3; d2 sort: c; b3 sort: c; d2 filter: a2 map: a2 forEach: A2
		 * filter: b1 filter: b3 filter: c filter: d2
		 */

		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).sorted((s1, s2) -> {
			System.out.printf("sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("forEach: " + s));

		// filter: d2
		// filter: a2
		// filter: b1
		// filter: b3
		// filter: c
		// map: a2
		// forEach: A2

	}

	private static long counter1;

	private static void wasCalled() {
		counter1++;
	}

	public static Stream<String> streamOf(List<String> list) {
		return list == null || list.isEmpty() ? Stream.empty() : list.stream();
	}

}
