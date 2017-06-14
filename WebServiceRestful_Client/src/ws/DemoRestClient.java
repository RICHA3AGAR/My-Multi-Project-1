package ws;

import javax.ws.rs.core.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;

import java.net.URI;
import java.text.MessageFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

public class DemoRestClient {

	private static final String BASE_URI = "http://localhost:8080/UserManagement/rest";

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();

		org.glassfish.jersey.client.JerseyClient client = org.glassfish.jersey.client.ClientConfig.ClientBuilder
				.newClient(config);

		WebTarget target = client.target(getBaseURI());

		String response = target.path("rest").path("getMovies").request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class).toString();

		String plainAnswer = target.path("rest").path("getMovies").request().accept(MediaType.TEXT_PLAIN)
				.get(String.class);
		String xmlAnswer = target.path("rest").path("getMovies").request().accept(MediaType.TEXT_XML).get(String.class);
		String htmlAnswer = target.path("rest").path("getMovies").request().accept(MediaType.TEXT_HTML)
				.get(String.class);

		System.out.println(response);
		System.out.println(plainAnswer);
		System.out.println(xmlAnswer);
		System.out.println(htmlAnswer);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/UserManagement").build();
	}
}
