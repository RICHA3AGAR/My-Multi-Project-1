package com.myexamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;

import static com.jayway.jsonpath.JsonPath.parse;
import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

//https://github.com/json-path/JsonPath
//http://goessner.net/articles/JsonPath/
	
public class JsonPathExamples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Example1();
		Example2();
		Example3();

		// String json1 = "{\"date_as_long\" : 1411455611975}";
		// Date date = JsonPath.parse(json1).read("$['date_as_long']",
		// Date.class);

	}

	public static void Example1() {
		try {
			String json = new String(Files.readAllBytes(Paths.get("resources/store.json")));

			List<String> authors = JsonPath.read(json, "$.store.book[*].author");

			Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

			String author0 = JsonPath.read(document, "$.store.book[0].author");
			String author1 = JsonPath.read(document, "$.store.book[1].author");

			ReadContext ctx = JsonPath.parse(json);
			List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");

			// Book book = JsonPath.parse(json).read("$.store.book[0]",
			// Book.class);

			Predicate booksWithISBN = new Predicate() {
				@Override
				public boolean apply(PredicateContext ctx) {
					return ctx.item(Map.class).containsKey("isbn");
				}
			};

			List<Map<String, Object>> books = ctx.read("$.store.book[?].isbn", List.class, booksWithISBN);

			Filter cheapFictionFilter = filter(where("category").is("fiction").and("price").lte(10D));

			List<Map<String, Object>> books1 = parse(json).read("$.store.book[?]", cheapFictionFilter);

			System.out.println("Example1:: ");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Example3() {
		try {
			String json = new String(Files.readAllBytes(Paths.get("resources/store.json")));

			Configuration configuration = Configuration.defaultConfiguration();

			List<Map<String, Object>> expensiveBooks = JsonPath.using(configuration).parse(json)
					.read("$.store.book[?(@.price > 10)]", List.class);

			Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();

			List<String> pathList = JsonPath.using(conf).parse(json).read("$..author");

			System.out.println("Example3:: ");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Example2() {
		try {
			String json = new String(Files.readAllBytes(Paths.get("resources/person.json")));

			Configuration conf = Configuration.defaultConfiguration();

			// Works fine
			String gender0 = JsonPath.using(conf).parse(json).read("$[0]['gender']");
			// PathNotFoundException thrown
			// String gender1 =
			// JsonPath.using(conf).parse(json).read("$[1]['gender']");

			Configuration conf2 = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);

			// Works fine
			String gender01 = JsonPath.using(conf2).parse(json).read("$[0]['gender']");
			// Works fine (null is returned)
			String gender11 = JsonPath.using(conf2).parse(json).read("$[1]['gender']");

			System.out.println("Example2:: ");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
