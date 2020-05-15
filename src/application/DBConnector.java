package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBConnector {

	private static DBConnector single_instance = null;
	private static Connection connection;

	private static String username; 
	// Replace below database url, username and password with your actual database
	// credentials
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/order_processing_system?useSSL=false";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "root";
	// private static final String INSERT_QUERY = "INSERT INTO registration
	// (userName, password, firstName, lastName, email, phone, shippingadd) VALUES
	// (?, ?, ?, ?, ?, ?, ?)";
	// private static final String SELECT_QUERY = "SELECT * FROM registration WHERE
	// username = ? and password = ?";
	// private static final String VALIDATE_QUERY = "SELECT * FROM registration
	// WHERE username = ?";

	public static DBConnector getInstance() {
		if (single_instance == null) {
			single_instance = new DBConnector();
		}
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				printSQLException(e);
			}
		}
		return single_instance;
	}

	/// CHECK IF USER NAME IS ALREADY EXIST OR NOT BEFORE INSERTION.
	public boolean existusername(String username) {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "select username from Users where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			/// CHANGE CONDITION TO COUNT IF NOT 0 THEN TRUE ELSE THEN FALSE.
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return false;
	}

	/// INSERT DATA OF THE NEW USER
	public void insertRecord(String userName, String password, String firstName, String lastName, String email,
			String phone, String shippingadd) throws SQLException {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "insert into Users values(?,?,?,SHA1(?),?,?,?,'customer')";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, shippingadd);
			preparedStatement.setString(7, email);

			preparedStatement.executeUpdate();
			username = userName;
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}
	}

	/// CHECK IF USERNAME AND PASSWORD FOR THE USER ARE VALID OR NOT.
	public boolean validate(String username, String password) throws SQLException {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "select * from Users where username=? and password=SHA1(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			/// GET DATA FROM RESULT SET AND SAVE IT
			// Profile p = new Profile();
			// p.setData();
			if (resultSet.next()) {
				this.username = username;
				return true;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return false;
	}

	public void editPassword(String password) {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "update Users(password) "
					+ "set values(SHA1(?)) where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, username);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void editData(String firstname, String lastname, String email, String phone, String shippingadd, String cc,
			String edate) {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "update Users set firsr_name=?, second_name=?, user_phone=?, user_address=?, "
					+ "user_email=? where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, shippingadd);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, username);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public boolean publisherExists(String name) {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "select * from Publisher where Publisher_name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			/// CHANGE CONDITION TO COUNT IF NOT 0 THEN TRUE ELSE THEN FALSE.
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return false;
	}
	
	public void addPublisher(String name, ArrayList<String> phones, ArrayList<String> addresses) {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			connection.setAutoCommit(false);
			String query = "insert into Publisher values(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.executeQuery();
			
			String publisherAddressesQuery = "insert into Publisher_Address values(?,?)";
			for (String a : addresses) {
				preparedStatement = connection.prepareStatement(publisherAddressesQuery);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, a);
				preparedStatement.executeQuery();
			}
			String publisherPhonesQuery = "insert into Publisher_Address values(?,?)";
			for (String ph : phones) {
				preparedStatement = connection.prepareStatement(publisherPhonesQuery);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, ph);
				preparedStatement.executeQuery();
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				if (connection != null) {
					connection.rollback();
					connection.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			printSQLException(e);
		}
	}
	
	public void addBook(String isbn, String title, String pname, String pyear, String category, String price,
			String threashold, String availableCopies, String orderQuantity, String authors) {
		/// to db
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			connection.setAutoCommit(false);
			String query = "insert into Book values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, isbn);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, pname);
			preparedStatement.setString(4, pyear);
			preparedStatement.setDouble(5, Double.valueOf(price));
			preparedStatement.setString(6, category);
			preparedStatement.setInt(7, Integer.valueOf(availableCopies));
			preparedStatement.setInt(8, Integer.valueOf(threashold));
			preparedStatement.setInt(9, Integer.valueOf(orderQuantity));
			preparedStatement.executeQuery();
			
			String authorsQuery = "insert into Book_Authors values(?,?)";
			String[] authorsArray = authors.split(",");
			for (String a : authorsArray) {
				preparedStatement = connection.prepareStatement(authorsQuery);
				preparedStatement.setString(1, isbn);
				preparedStatement.setString(2, a);
				preparedStatement.executeQuery();
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				if (connection != null) {
					connection.rollback();
					connection.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			printSQLException(e);
		}
	}

	public boolean bookexist(String isbn) {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "select * from Book where ISBN_number=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, isbn);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
		return false;
		/// db
	}

	public void getbookdata(String isbn) {
		/// db
		/// pass values to AddBookD
		String selectFromBookQuery = "select * from Book where ISBN_number=?";
		String selectFromBookAuthorsQuery = "select * from Book_Authors where ISBN_number=?";
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			/** get book data from book relation **/
			PreparedStatement preparedStatement = connection.prepareStatement(selectFromBookQuery);
			preparedStatement.setString(1, isbn);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				String title = result.getString("title");
				String publisherName = result.getString("publisher_name");
				String publicationYear = result.getString("publication_year");
				double sellingPrice = result.getDouble("selling_price");
				String category = result.getString("category");
				int availableCopies = result.getInt("available_copies");
				int threshold = result.getInt("threshold");
				int orderQuantity = result.getInt("order_quantity");
			}
			/** get book authors from book authors relation **/
			preparedStatement = connection.prepareStatement(selectFromBookAuthorsQuery);
			preparedStatement.setString(1, isbn);
			result = preparedStatement.executeQuery();
			ArrayList<String> authors = new ArrayList<>();
			while (result.next()) {
				authors.add(result.getString("Author"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}

	public void modifybook(String isbn, String title, String pname, String pyear, String category, String price,
			String threashold, String orderQuantity, String authors) {
		/// db
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			connection.setAutoCommit(false);
			String query = "update Book set title=?, publisher_name=?, publication_year=?, selling_price=?,"
					+ "category=?, threshold=?, order_quantity=? where ISBN_number=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, pname);
			preparedStatement.setString(3, pyear);
			preparedStatement.setDouble(4, Double.valueOf(price));
			preparedStatement.setString(5, category);
			preparedStatement.setInt(6, Integer.valueOf(threashold));
			preparedStatement.setInt(7, Integer.valueOf(orderQuantity));
			preparedStatement.setString(8, isbn);
			preparedStatement.executeQuery();
			
			String deleteAuthorsQuery = "delete from Book_Authors where ISBN_number=?";
			preparedStatement = connection.prepareStatement(deleteAuthorsQuery);
			preparedStatement.setString(1, isbn);
			preparedStatement.executeQuery();
			
			String authorsQuery = "insert into Book_Authors values(?,?)";
			String[] authorsArray = authors.split(",");
			for (String a : authorsArray) {
				preparedStatement = connection.prepareStatement(authorsQuery);
				preparedStatement.setString(1, isbn);
				preparedStatement.setString(2, a);
				preparedStatement.executeQuery();
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				if (connection != null) {
					connection.rollback();
					connection.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			printSQLException(e);
		}
	}

	public void addorder(String isbn, String quantity) {
		/// db
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "call add_order(?,?)";
			CallableStatement statement = connection.prepareCall(query);
			statement.setString(1, isbn);
			statement.setInt(2, Integer.valueOf(quantity));
			statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}

	public void getAllOrders() {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "select * from Orders";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int orderId = result.getInt("id");
				String ISBN = result.getString("ISBN_number");
				Date timeStamp = result.getTimestamp("order_date");		//YYYY-MM-DD HH:MM:SS
				int quantity = result.getInt("quantity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}
	
	public void confirmOrder(int id) {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "delete from Orders where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}
	
	public String getuserdata(String username) {
		/// db
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "select * from Users where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				String firstName = result.getString("first_name");
				String secondName = result.getString("last_name");
				String phone = result.getString("user_phone");
				String email = result.getString("user_email");
				String address = result.getString("user_address");
				String privelege = result.getString("user_privilege");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
		String data = " ";
		return data;
	}

	public void promote(String username) {
		/// db
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "update users set user_privilege='manager' where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}

	// list of authors changed to String
	public void bookSearch(String title, String publisher, String category, int publicationYear, double sellingPrice,
			String auther) {
		// use setTitle, setAuthers, setPublisher, setPublicationYear, setSellingPrice,
		// setISBN, setCategory
		boolean first = true;
		String query;
		if (!auther.equals("")) {
			query = "select * from Book, Book_Author where Book_Author.ISBN_number and ";
		} else {
			query = "select * from Book where ";
		}
		if (!title.equals("")) {
			query += "title='" + title + "'";
			first = false;
		}
		if (!publisher.equals("")) {
			if (!first)
				query += " and ";
			query += "publisher_name='" + publisher + "'";
			first = false;
		}
		if (!category.equals("")) {
			if (!first)
				query += " and ";
			query += "category='" + category + "'";
			first = false;
		}
		String publicationYearString = String.valueOf(publicationYear);
		if (publicationYear != 0 && publicationYearString.length() == 4) {
			if (!first)
				query += " and ";
			query += "publication_year='" + publicationYearString + "'";
			first = false;
		}
		if (sellingPrice != 0) {
			if (!first)
				query += " and ";
			query += "selling_price=" + String.valueOf(sellingPrice);
			first = false;
		}
		if (!auther.equals("")) {
			if (!first)
				query += " and ";
			query += "Author='" + auther +"'";
		}
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				String ISBN = result.getString("ISBN_number");
				String authorsQuery = "select * from Book_Authors where ISBN_numner=?";
				preparedStatement = connection.prepareStatement(authorsQuery);
				preparedStatement.setString(1, ISBN);
				ResultSet authors = preparedStatement.executeQuery();
				while (authors.next()) {
					result.getString("Author");
				}
				result.getString("title");
				result.getString("publisher_name");
				result.getString("publication_year");
				result.getString("category");
				result.getDouble("selling_price");
				result.getInt("available_copies");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}

	public void checkOut(List<String> ISBN) {
		String updateBookQuery = "update book set available_copies = available_copies - 1 where ISBN_number=?";
		String addToSalesProcedureCall = "call add_to_sales(?,?)";
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			connection.setAutoCommit(false);
			for (String s : ISBN) {
				PreparedStatement preparedStatement = connection.prepareStatement(updateBookQuery);
				preparedStatement.setString(1, s);
				preparedStatement.executeQuery();
				CallableStatement callableStatement = connection.prepareCall(addToSalesProcedureCall);
				callableStatement.setString(1, username);
				callableStatement.setString(2, s);
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
			if (connection != null) {
				try {
					connection.rollback();
					connection.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					printSQLException(e1);
				}
			}
		}
	}
	
	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}