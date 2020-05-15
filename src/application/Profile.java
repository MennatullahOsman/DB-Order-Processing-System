package application;

import java.io.IOException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Profile extends Pane {
	MainPage mainPage;
	private Pane root;

	public Profile(MainPage main) {
		mainPage = main;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
		loader.setController(this);

		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		main.ParentPane.getChildren().add(root);
	}

	@FXML
	private TextField cc;

	@FXML
	private TextField firstname;

	@FXML
	private TextField phone;

	@FXML
	private PasswordField oldpassword;

	@FXML
	private Button save;

	@FXML
	private Button back;

	@FXML
	private PasswordField newpassword;

	@FXML
	private TextField edate;

	@FXML
	private TextField email;

	@FXML
	private TextField shippingadd;

	@FXML
	private TextField lastname;
	@FXML
	private Text errorpass;
	@FXML
	private Text errordata;
	private String first, last, emailadd, phonenum, shipadd, ccn, ed;

	@FXML
	void editData(ActionEvent event) {
		if (firstname.getText().isEmpty()) {
			errordata.setText("Please enter first name first");
			return;
		} else {
			first = firstname.getText();
		}
		if (lastname.getText().isEmpty()) {
			errordata.setText("Please enter last name first");
			return;
		} else {
			last = lastname.getText();
		}
		if (email.getText().isEmpty() || !validEmail(email.getText())) {
			errordata.setText("Please enter a valid email first");
			return;
		} else {
			emailadd = email.getText();
		}
		if (phone.getText().isEmpty() || !isNumeric(phone.getText())) {
			errordata.setText("Please enter a valid phone number");
			return;
		} else {
			phonenum = phone.getText();
		}
		shipadd = shippingadd.getText();
		if (!cc.getText().isEmpty() && !isNumeric(cc.getText())) {
			errordata.setText("Please enter a valid cc number");
			return;
		} else {
			ccn = cc.getText();
		}
		ed = edate.getText();
		// DB.editData(first, last, emailadd, phonenum, shipadd, ccn, ed);
		mainPage.ParentPane.getChildren().remove(root);
	}

	void viewData() {
		/// WANT TO CALL IT WHEN FRAME OPEND DIRECTLY
		firstname.setText(first);
		lastname.setText(last);
		cc.setText(ccn);
		email.setText(emailadd);
		phone.setText(phonenum);
		shippingadd.setText(shipadd);
		edate.setText(ed);
	}

	void setData(String firstname, String lastname, String email, String phone, String shippingadd, String cc,
			String edate) {
		first = firstname;
		last = lastname;
		emailadd = email;
		phonenum = phone;
		shipadd = shippingadd;
		ccn = cc;
		ed = edate;
	}

	@FXML
	void changepass(ActionEvent event) {
		DBConnector db = DBConnector.getInstance();
		if (!oldpassword.getText().isEmpty()) {
			if (oldpassword.getText() == db.getpass()) {
				if (!newpassword.getText().isEmpty()) {
					db.editPassword(newpassword.getText());
				} else {
					errorpass.setText("Error: Please enter new password");
				}
			} else {
				errorpass.setText("Error: Please enter your valid password");
			}
		} else {
			errorpass.setText("Error: Please enter old password first");
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
	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
