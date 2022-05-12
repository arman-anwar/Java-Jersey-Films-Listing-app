package mmu.filmsapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import mmu.filmsapp.model.Film;


/**
 * Singleton Class
 * @author Arman
 *
 */
public class DBConn {

	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;

    private static DBConn dbConnection ;
	  private  Connection connection; 

	/**
	 * Singeton Constructor
	 */
	private DBConn() {
	}

	/**
	 * Method to instantiate DBConn class
	 * @return
	 */
	public static DBConn getInstance() {
		if (dbConnection == null) {
			dbConnection = new DBConn();
		}
		return dbConnection;
	}

	/**
	 * Method to return Connection Object
	 * @return
	 */
	public Connection getDBConnection() {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error at com.mysql.jdbc.Driver");
			System.out.println(e.getMessage());
			return null;
		}
		try {
		    String url = "jdbc:mysql://localhost:3306/filmsdb";
			String user = "root";
		    String password = "";
		    // Note none default port used, 6306 not 3306
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
			return conn;

		} catch (SQLException e) {
			System.out.println("Error at >>  SQLException");
			System.out.println(e);
		}
		return connection;
	}

}
