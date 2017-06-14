package com.tutorialspoint;

//http://www.vogella.com/tutorials/REST/article.html
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/MovieService")

public class MovieService {

	ArrayList<Movie> movies = new ArrayList<Movie>();

	public MovieService() {
		GetAllMovies();
	}

	// http://localhost:8080/UserManagement/rest/MovieService/getMovies
	@GET
	@Path("/getMovies")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Movie> getMovies() {
		return movies;
	}

	// http://localhost:8080/UserManagement/rest/MovieService/getMovie/2
	@GET
	@Path("/getMovie/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMovie(@PathParam("id") int id) {
		List<Movie> result = movies.stream().filter(el -> el.getId() == id).collect(Collectors.toList());
		if (!result.isEmpty()) {
			return (Movie) result.toArray()[0];
		} else
			return new Movie();
	}

	// http://localhost:8080/UserManagement/rest/MovieService/postMovie and body
	// { "id": 5, "runningTimeMinutes": 81, "title": "Toy Story", "releaseDate":
	// 1492544934427 }
	@POST
	@Path("/postMovie")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Movie> postMovie(Movie value) {
		movies.add(value);
		return movies;
	}

	// http://localhost:8080/UserManagement/rest/MovieService/putMovie and body
	// { "id": 2, "runningTimeMinutes": 100, "title": "Toy Story",
	// "releaseDate": 1492544934427 }
	@PUT
	@Path("/putMovie")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Movie> putMovie(Movie value) {
		List<Movie> result = movies.stream().filter(el -> el.getId() == value.getId()).collect(Collectors.toList());
		if (!result.isEmpty()) {
			movies.remove(result.toArray()[0]);
			movies.add(value);
		}
		return movies;
	}

	// it is not working
	/*
	 * @POST
	 * 
	 * @Produces(MediaType.TEXT_HTML)
	 * 
	 * @Consumes(MediaType.APPLICATION_FORM_URLENCODED) public void
	 * newTodo(@FormParam("id") String id,
	 * 
	 * @FormParam("summary") String summary,
	 * 
	 * @FormParam("description") String description,
	 */
	@DELETE
	@Path("/deleteMovie/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Movie> deleteMovie(@PathParam("id") int id) {
		if (movies.stream().filter(o -> o.getId() == id).findFirst().isPresent()) {

		}
		List<Movie> result = movies.stream().filter(el -> el.getId() == id).collect(Collectors.toList());
		if (!result.isEmpty()) {
			movies.remove(result.toArray()[0]);
		}
		return movies;
	}

	private final ArrayList<Movie> GetAllMovies() {
		movies = new ArrayList<Movie>();
		Date date = new Date();
		movies.add(new Movie(1, "Up", date, 96));
		movies.add(new Movie(2, "Toy Story", date, 81));
		movies.add(new Movie(3, "Big Hero 6", date, 102));
		return movies;
	}
}