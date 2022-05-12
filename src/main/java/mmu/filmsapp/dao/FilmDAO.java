package mmu.filmsapp.dao;


import java.util.HashMap;
import java.util.Map;

import mmu.filmsapp.model.Film;

import java.sql.*;

public enum FilmDAO {
	instance;
	Film oneFilm = null;
	Connection dbConnection = null;
	private Map<Integer, Film> films = new HashMap<Integer, Film>();

	public Map<Integer,Film> getAllFilms() throws SQLException {
		

		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM films;";

		try {
			dbConnection = DBConn.getInstance().getDBConnection();
			statement = dbConnection.createStatement();
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {

				int id = result.getInt("ID");
				String title = result.getString("title");
				int year = result.getInt("Year");
				String director = result.getString("director");
				String stars = result.getString("stars");
				String review = result.getString("review");
				this.films.put(id, new Film(id, title, year, director, stars, review));

			}
		} catch (Exception e) {
			System.out.println("get all Films: " + e);
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return this.films;
	}

	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

	public Film getFilmByID(int id) throws SQLException {

		oneFilm = null;
		Statement statement = null;
		ResultSet result = null;


		try {
			dbConnection = DBConn.getInstance().getDBConnection();
			statement = dbConnection.createStatement();
			String query = "select * from films where id=" + id;
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {
				oneFilm = getNextFilm(result);
			}
		} catch (Exception e) {
			System.out.println("get all Books: " + e);
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return oneFilm;
	}

	/**
	 * Add film in DB
	 * 
	 * @param film
	 * @return true/false
	 * @throws SQLException
	 */
	public Integer addFilm(Film film) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String update = "INSERT INTO films (  title, year, director, stars, review) VALUES ('" + film.getTitle() + "',"
				+ film.getYear() + ",'" + film.getDirector() + "','" + film.getStars() + "','" + film.getReview() + "');";
		Integer result = 0;
		try {
			dbConnection = DBConn.getInstance().getDBConnection();
			statement = dbConnection.createStatement();

			result = statement.executeUpdate(update);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}

		}
		return result;
	}

	/**
	 * update film in DB
	 * 
	 * @param film
	 * @return true/false
	 * @throws SQLException
	 */
	public Film updateFilm(Film film) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "UPDATE films SET " + "title = '" + film.getTitle() + "',year = " + film.getYear() + ",director = '"
				+ film.getDirector() + "',stars = '" + film.getStars() + "'" + ",review = '" + film.getReview() + "'"
				+ " WHERE ID = " + film.getId() + ";";

		
		try {
			dbConnection = DBConn.getInstance().getDBConnection();
			statement = dbConnection.createStatement();
			// System.out.println(query);
			// execute SQL update
			statement.executeUpdate(query);
			this.films.put(film.getId(), film);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return null;

		} finally {

			if (statement != null) {
				statement.close();
			}
		}
		return film;
	}
	
	/**
	 * delete film from database
	 * @param filmId
	 * @return true/false
	 * @throws SQLException
	 */
	public Boolean deleteFilm(int filmId) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM films WHERE ID = " + filmId + ";";
		try {
			dbConnection = DBConn.getInstance().getDBConnection();
			statement = dbConnection.createStatement();
			result = statement.executeUpdate(query);
			this.films.remove(filmId);

		} finally {
			if (statement != null) {
				statement.close();
			}
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
}
