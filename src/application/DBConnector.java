package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBConnector {

	private static DBConnector single_instance = null;;

	public static DBConnector getInstance() {
		if (single_instance == null)
			single_instance = new DBConnector();

		return single_instance;
	}

	// Replace below database url, username and password with your actual database
	// credentials
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/javafx_registration?useSSL=false";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "root";
	private static final String INSERT_QUERY = "INSERT INTO registration (userName, password, firstName, lastName, email, phone, shippingadd) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT * FROM registration WHERE username = ? and password = ?";
	private static final String VALIDATE_QUERY = "SELECT * FROM registration WHERE username = ?";

	/// CHECK IF USER NAME IS ALREADY EXIST OR NOT BEFORE INSERTION.
	public boolean existusername(String username) {
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

				PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_QUERY)) {
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

		// Step 1: Establishing a Connection and
		// try-with-resource statement will auto close the connection.
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, "SHA(" + password + ")");
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, phone);
			preparedStatement.setString(7, shippingadd);

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}
	}

	/// CHECK IF USERNAME AND PASSWORD FOR THE USER ARE VALID OR NOT.
	public boolean validate(String username, String password) throws SQLException {

		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			/// GET DATA FROM RESULT SET AND SAVE IT
//            Profile p = new Profile();
//            p.setData();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return false;
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

	public void editData(String firstname, String lastname, String email, String phone, String shippingadd, String cc,
			String edate) {
		/// edit
	}

	public void addBook(String isbn, String title, String pname, String pyear, String category, String price,
			String threashold, String authors) {
		/// to db
	}

	public boolean bookexist(String isbn) {
		return false;
		/// db
	}

	public void getbookdata(String isbn) {
		/// db
		/// pass values to AddBookD
	}

	public void modifybook(String isbn, String title, String pname, String pyear, String category, String price,
			String threashold, String authors) {
		/// db
	}

	public void addorder(String isbn, String quantity) {
		/// db
	}

	public String getuserdata(String username) {
		/// db
		String data = " ";
		return data;
	}

	public void promote(String username) {
		/// db
	}

	public void bookSearch(String Title, String Publisher, String Category, int PublicationYear, double SellingPrice,
			List<String> Authers) {

	}

	public void checkOut(List<Integer> ISBN) {

	}
}