package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
//import javafx.stage.Window;
import javafx.stage.Stage;

public class Registration {
	@FXML
	private TextField firstname;

	@FXML
	private TextField email;

	@FXML
	private PasswordField passwordup;

	@FXML
	private Button signup;

	@FXML
	private TextField usernamein;

	@FXML
	private TextField lastname;

	@FXML
	private PasswordField passwordin;

	@FXML
	private Button signin;

	@FXML
	private TextField usernameup;

	@FXML
	private TextField phone;

	@FXML
	private TextField shippingadd;

	@FXML
	private Text userErrorLogin;

	@FXML
	private Text PassErrorLogin;

	@FXML
	private Text userErrorSignup;

	@FXML
	private Text firstErrorSignup;

	@FXML
	private Text secondErrorSignup;

	@FXML
	private Text PassErrorSignup;

	@FXML
	private Text addressErrorSignup;

	@FXML
	private Text emailErrorSignup;

	@FXML
	private Text phoneErrorSignup;

	@FXML
	private Text signuperror;
	@FXML
	private Text signinerror;

	@FXML
	public void reg(ActionEvent event) throws SQLException {

		// Window owner = signup.getScene().getWindow();
		boolean flag = false;
		if (usernameup.getText().isEmpty()) {
			userErrorSignup.setVisible(true);
			flag = true;
		}
		if (firstname.getText().isEmpty() || firstname.getText().matches(".*\\d.*")) {
			firstErrorSignup.setVisible(true);
			flag = true;
		}
		if (lastname.getText().isEmpty() || lastname.getText().matches(".*\\d.*")) {
			secondErrorSignup.setVisible(true);
			flag = true;
		}
		if (email.getText().isEmpty() || !validEmail(email.getText())) {
			emailErrorSignup.setVisible(true);
			flag = true;
		}
		if (phone.getText().isEmpty() || !isNumeric(phone.getText())) {
			phoneErrorSignup.setVisible(true);
			flag = true;
		}
		if (passwordup.getText().isEmpty()) {
			PassErrorSignup.setVisible(true);
			flag = true;
		}
		if (shippingadd.getText().isEmpty()) {
			addressErrorSignup.setVisible(true);
			flag = true;
		}
		if (!flag) {
			String firstName = firstname.getText();
			String lastName = lastname.getText();
			String userName = usernameup.getText();
			String emailadd = email.getText();
			String password = passwordup.getText();
			String phonen = phone.getText();
			String shippingadds = shippingadd.getText();
			DBConnector DB = new DBConnector();
			/// ERROR WHEN TRY TO ENTER ALREADY EXISTING USERNAME
			if (DB.existusername(userName)) {
				signuperror.setText("Please enter another username as this user name already exist!");
			} else {
				DB.insertRecord(userName, password, firstName, lastName, emailadd, phonen, shippingadds);
				signuperror.setText("Registration Successful! " + "Welcome " + firstname.getText());
				/// open the main page.
			    final Node source = (Node) event.getSource();
			    final Stage s = (Stage) source.getScene().getWindow();
			    s.hide();
				Parent root1;
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
					root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));  
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	public void login(ActionEvent event) throws SQLException {

		if (usernamein.getText().isEmpty()) {
			userErrorLogin.setVisible(true);
			return;
		}
		if (passwordin.getText().isEmpty()) {
			PassErrorLogin.setVisible(true);
			return;
		}

		String username = usernamein.getText();
		String password = passwordin.getText();

		DBConnector DB = new DBConnector();
		boolean flag = DB.validate(username, password);
//		boolean flag = true;
		if (!flag) {
			signinerror.setText("Please enter correct User Name and Password");
			return;
		} else {
			signinerror.setText("Login Successful!");
			/// open the main page.
		    final Node source = (Node) event.getSource();
		    final Stage s = (Stage) source.getScene().getWindow();
		    s.hide();
			Parent root1;
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
				root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));  
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean validEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	@FXML
	void TestAddressSignup(KeyEvent event) {
		addressErrorSignup.setVisible(false);
	}

	@FXML
	void TestEmailSignup(KeyEvent event) {
		emailErrorSignup.setVisible(false);
	}

	@FXML
	void TestFirstSignup(KeyEvent event) {
		firstErrorSignup.setVisible(false);
	}

	@FXML
	void TestPassLogin(KeyEvent event) {
		PassErrorLogin.setVisible(false);

	}

	@FXML
	void TestPassSignup(KeyEvent event) {
		PassErrorSignup.setVisible(false);
	}

	@FXML
	void TestPhoneSignup(KeyEvent event) {
		phoneErrorSignup.setVisible(false);
	}

	@FXML
	void TestSecondSignup(KeyEvent event) {
		secondErrorSignup.setVisible(false);
	}

	@FXML
	void TestUserLogIn(KeyEvent event) {
		userErrorLogin.setVisible(false);
	}

	@FXML
	void TestUserSignup(KeyEvent event) {
		userErrorSignup.setVisible(false);
	}

}