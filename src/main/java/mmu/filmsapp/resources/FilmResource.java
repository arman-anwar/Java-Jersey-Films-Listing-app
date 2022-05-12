package mmu.filmsapp.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import mmu.filmsapp.dao.FilmDAO;
import mmu.filmsapp.model.Film;


public class FilmResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public FilmResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Film getFilm() throws SQLException {
		Film film = FilmDAO.instance.getAllFilms().get(id);
		if (film == null)
			throw new RuntimeException("Get: Film with  " + id + " not found");
		return film;
	}

	// For the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Film getTodoHTML() throws SQLException {
		Film todo = FilmDAO.instance.getAllFilms().get(id);
		if (todo == null)
			throw new RuntimeException("Get: Film with  " + id + " not found");
		return todo;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Film putFilm(JAXBElement<Film> film) throws SQLException {
		Film c = film.getValue();
		FilmDAO.instance.updateFilm(c);
		return c;
	}

	@DELETE
	public void deleteFilm() throws SQLException {
		Boolean c = FilmDAO.instance.deleteFilm(id);
		if (!c)
			throw new RuntimeException("Delete: Todo with " + id + " not found");
	}

}
