package mmu.filmsapp.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import mmu.filmsapp.dao.FilmDAO;
import mmu.filmsapp.model.Film;


// Will map the resource to the URL todos
@Path("/films")
public class FilmsResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Film> getFilmsBrowser() throws SQLException {
		List<Film> films = new ArrayList<>();
		films.addAll(FilmDAO.instance.getAllFilms().values());
		return films;
	}

	// Return the list of films for applications
	// Use http://localhost:8080/com.democo.jersey.todo/rest/todos
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<Film> getFilms() throws SQLException {
		ArrayList<Film> films = new ArrayList<Film>();
		films.addAll(FilmDAO.instance.getAllFilms().values());
		return films;
	}

	// retuns the number of todos
	// Use http://localhost:8080/com.democo.jersey.todo/rest/todos/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() throws SQLException {
		int count = FilmDAO.instance.getAllFilms().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String newFilm(Film film) throws IOException, SQLException, Exception {
		return FilmDAO.instance.addFilm(film) + "";
	}

	// Defines that the next path parameter after todos is
	// treated as a parameter and passed to the TodoResources
	// Allows to type http://localhost:8080/com.democo.jersey.todo/rest/todos/1
	// 1 will be treated as parameter todo and passed to TodoResource
	@Path("{film}")
	public FilmResource getFilm(@PathParam("film") Integer id) {
		return new FilmResource(uriInfo, request, id);
	}

}
