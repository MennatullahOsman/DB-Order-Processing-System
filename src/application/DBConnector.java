package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBConnector {

	private static DBConnector single_instance = null;
	private static Connection connection;

	// private static String username;
	private static String password;
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
			// PassValues.setUserName("emanrafik");
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

			PassValues.setUserName(userName);
			PassValues.setFirstName(firstName);
			PassValues.setSecondName(lastName);
			PassValues.setPhone(phone);
			PassValues.setAddress(shippingadd);
			PassValues.setEmail(email);

			DBConnector.password = password;
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
			String query = "select * from Users where username=? and user_password=SHA1(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			/// GET DATA FROM RESULT SET AND SAVE IT
			// Profile p = new Profile();
			// p.setData();
			if (resultSet.next()) {
				PassValues.setUserName(resultSet.getString("username"));
				PassValues.setAddress(resultSet.getString("user_address"));
				PassValues.setFirstName(resultSet.getString("first_name"));
				PassValues.setSecondName(resultSet.getString("second_name"));
				PassValues.setPhone(resultSet.getString("user_phone"));
				PassValues.setEmail(resultSet.getString("user_email"));
				PassValues.setPrivilage(resultSet.getString("user_privilege"));
				DBConnector.password = password;
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
			String query = "update Users set user_password = SHA1(?) where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, PassValues.getUserName());
			preparedStatement.executeUpdate();
			DBConnector.password = password;
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
			String query = "update Users set first_name=?, second_name=?, user_phone=?, user_address=?, "
					+ "user_email=? where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, shippingadd);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, PassValues.getUserName());
			preparedStatement.executeUpdate();
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
			preparedStatement.executeUpdate();

			String publisherAddressesQuery = "insert into Publisher_Address values(?,?)";
			for (String a : addresses) {
				preparedStatement = connection.prepareStatement(publisherAddressesQuery);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, a);
				preparedStatement.executeUpdate();
			}
			String publisherPhonesQuery = "insert into Publisher_Address values(?,?)";
			for (String ph : phones) {
				preparedStatement = connection.prepareStatement(publisherPhonesQuery);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, ph);
				preparedStatement.executeUpdate();
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
			String threashold, String availableCopies, String orderQuantity, List<String> authors) {
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
			preparedStatement.executeUpdate();

			String authorsQuery = "insert into Book_Authors values(?,?)";

			for (String a : authors) {
				preparedStatement = connection.prepareStatement(authorsQuery);
				preparedStatement.setString(1, isbn);
				preparedStatement.setString(2, a.trim());
				preparedStatement.executeUpdate();
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
			List<String> dataToModify = new ArrayList<String>();
			if (result.next()) {
				String title = result.getString("title");
				dataToModify.add(title);
				String publisherName = result.getString("publisher_name");
				dataToModify.add(publisherName);
				Date date = result.getDate("publication_year");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				String publicationYear = formatter.format(date);
				dataToModify.add(publicationYear);
				double sellingPrice = result.getDouble("selling_price");
				dataToModify.add(String.valueOf(sellingPrice));
				String category = result.getString("category");
				dataToModify.add(category);
				// int availableCopies = result.getInt("available_copies");
				// dataToModify.add(String.valueOf(availableCopies));
				int threshold = result.getInt("threshold");
				dataToModify.add(String.valueOf(threshold));
				int orderQuantity = result.getInt("order_quantity");
				dataToModify.add(String.valueOf(orderQuantity));
			}
			/** get book authors from book authors relation **/
			preparedStatement = connection.prepareStatement(selectFromBookAuthorsQuery);
			preparedStatement.setString(1, isbn);
			result = preparedStatement.executeQuery();
			ArrayList<String> authors = new ArrayList<>();
			while (result.next()) {
				authors.add(result.getString("Author"));
			}
			String listString = "";
			for (String s : authors) {
				listString += s + ",";
			}
			dataToModify.add(listString);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}

	public void modifybook(String isbn, String title, String pname, String pyear, String category, String price,
			String threashold, String orderQuantity, List<String> authors) {
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
			preparedStatement.executeUpdate();

			String deleteAuthorsQuery = "delete from Book_Authors where ISBN_number=?";
			preparedStatement = connection.prepareStatement(deleteAuthorsQuery);
			preparedStatement.setString(1, isbn);
			preparedStatement.executeUpdate();

			String authorsQuery = "insert into Book_Authors values(?,?)";
			for (String a : authors) {
				preparedStatement = connection.prepareStatement(authorsQuery);
				preparedStatement.setString(1, isbn);
				preparedStatement.setString(2, a.trim());
				preparedStatement.executeUpdate();
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
				PassValues.setOrderID(result.getInt("id"));
				PassValues.setOrderISBN(result.getString("ISBN_number"));
				PassValues.setOrderDate(result.getTimestamp("order_date")); // YYYY-MM-DD HH:MM:SS
				PassValues.setOrderQuantity(result.getInt("quantity"));
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
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}

	public String getuserdata(String username) {
		/// db
		String res = "";
		try {
			if (connection == null) {
				connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			}
			String query = "select * from Users where username=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet result = preparedStatement.executeQuery();
			List<String> userdata = new ArrayList<String>();
			if (result.next()) {
				String firstName = result.getString("first_name");
				res += "First Name: " + firstName + "\n";
				userdata.add(firstName);
				String secondName = result.getString("second_name");
				res += "Second Name: " + secondName + "\n";
				userdata.add(secondName);
				String phone = result.getString("user_phone");
				res += "Phone: " + phone + "\n";
				userdata.add(phone);
				String email = result.getString("user_email");
				res += "E-Mail: " + email + "\n";
				userdata.add(email);
				String address = result.getString("user_address");
				res += "Address: " + address + "\n";
				userdata.add(address);
				String privelege = result.getString("user_privilege");
				res += "Privelege: " + privelege + "\n";
				userdata.add(privelege);
				// userdata.add(ccn);
				// userdata.add(expiredate);
				PassValues.setUserdata(userdata);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
		return res;
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
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		}
	}

	public void bookSearch(String title, String publisher, String category, int publicationYear, double sellingPrice,
			String auther) {
		boolean first = true;
		String query;
		// search by author only
		if (!auther.equals("") && title.equals("") && publisher.equals("") && category.equals("")
				&& publicationYear == 0 && sellingPrice == 0) {
			query = "select ISBN_number from Book_Authors where Author like '%" + auther + "%'";
			try {
				if (connection == null) {
					connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
				}
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet isbnResult = preparedStatement.executeQuery();
				while (isbnResult.next()) {
					String ISBN = isbnResult.getString("ISBN_number");
					// get book data by ISBN
					String dataQuery = "select * from Book where ISBN_number=?";
					preparedStatement = connection.prepareStatement(dataQuery);
					preparedStatement.setString(1, ISBN);
					ResultSet result = preparedStatement.executeQuery();
					while (result.next()) {
						PassValues.setTitle(result.getString("title"));
						PassValues.setPublisher(result.getString("publisher_name"));
						Date date = result.getDate("publication_year");
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
						PassValues.setPublicationYear(Integer.valueOf(formatter.format(date)));
						PassValues.setCategory(result.getString("category"));
						PassValues.setSellingPrice(result.getDouble("selling_price"));
						PassValues.setAvailableCopies(result.getInt("available_copies"));
						PassValues.setISBN(result.getString("ISBN_number"));
					}
					// get book authors by ISBN
					String authorsQuery = "select * from Book_Authors where ISBN_number=?";
					preparedStatement = connection.prepareStatement(authorsQuery);
					preparedStatement.setString(1, ISBN);
					ResultSet authors = preparedStatement.executeQuery();
					ArrayList<String> Authers = new ArrayList<String>();
					while (authors.next()) {
						Authers.add(authors.getString("Author"));
					}
					PassValues.setAuthers(Authers);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				printSQLException(e);
			}
		} else {
			if (!auther.equals("")) {
				query = "select * from Book, Book_Author where Book_Author.ISBN_number and ";
			} else {
				query = "select * from Book where ";
			}
			if (!title.equals("")) {
				query += "title like '%" + title + "%'";
				first = false;
			}
			if (!publisher.equals("")) {
				if (!first)
					query += " and ";
				query += "publisher_name like %'" + publisher + "%'";
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
				query += "Author like '%" + auther + "%'";
			}
			try {
				if (connection == null) {
					connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
				}
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					String ISBN = result.getString("ISBN_number");
					// get book authors by ISBN
					String authorsQuery = "select * from Book_Authors where ISBN_number=?";
					preparedStatement = connection.prepareStatement(authorsQuery);
					preparedStatement.setString(1, ISBN);
					ResultSet authors = preparedStatement.executeQuery();
					ArrayList<String> Authers = new ArrayList<String>();
					while (authors.next()) {
						Authers.add(authors.getString("Author"));
					}
					PassValues.setAuthers(Authers);
					PassValues.setTitle(result.getString("title"));
					PassValues.setPublisher(result.getString("publisher_name"));
					Date date = result.getDate("publication_year");
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
					PassValues.setPublicationYear(Integer.valueOf(formatter.format(date)));
					PassValues.setCategory(result.getString("category"));
					PassValues.setSellingPrice(result.getDouble("selling_price"));
					PassValues.setAvailableCopies(result.getInt("available_copies"));
					PassValues.setISBN(result.getString("ISBN_number"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				printSQLException(e);
			}
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
				preparedStatement.executeUpdate();
				CallableStatement callableStatement = connection.prepareCall(addToSalesProcedureCall);
				callableStatement.setString(1, PassValues.getUserName());
				callableStatement.setString(2, s);
				callableStatement.executeQuery();
			}
			System.out.println("commited");
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

	public String getpass() {
		return password;
	}

	public String getcurrentusername() {
		return PassValues.getUserName();
	}
}