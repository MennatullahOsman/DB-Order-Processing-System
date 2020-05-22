package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Profile extends Pane {
	MainPage mainPage;
	private Pane root;

	public Profile(MainPage main) {
		mainPage = main;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
		loader.setController(this);
		// viewData();
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
	private List<String> userdata = new ArrayList<String>();;

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
		DBConnector db = DBConnector.getInstance();
		db.editData(first, last, emailadd, phonenum, shipadd, ccn, ed);
		mainPage.ParentPane.getChildren().remove(root);
	}

	void viewData() {
		/// WANT TO CALL IT WHEN FRAME OPEND DIRECTLY
		DBConnector db = DBConnector.getInstance();
		db.getuserdata(db.getcurrentusername());
		userdata = PassValues.getUserdata();
		first = userdata.get(0);
		last = userdata.get(1);
		phonenum = userdata.get(2);
		emailadd = userdata.get(3);
		shipadd = userdata.get(4); // 5 is the privilege we don't need it here
		ccn = userdata.get(6);
		ed = userdata.get(7);

		firstname.setText(first);
		lastname.setText(last);
		cc.setText(ccn);
		email.setText(emailadd);
		phone.setText(phonenum);
		shippingadd.setText(shipadd);
		edate.setText(ed);
	}

	@FXML
	void MyInformationAction(ActionEvent event) {
		Dialog<String> dialog = new Dialog<>();
		ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("UserName:"), 0, 0);
		grid.add(new Label(PassValues.getUserName()), 1, 0);
		grid.add(new Label("FirstName:"), 0, 1);
		grid.add(new Label(PassValues.getFirstName()), 1, 1);
		grid.add(new Label("SecondName:"), 0, 2);
		grid.add(new Label(PassValues.getSecondName()), 1, 2);
		grid.add(new Label("Phone:"), 0, 3);
		grid.add(new Label(PassValues.getPhone()), 1, 3);
		grid.add(new Label("Address:"), 0, 4);
		grid.add(new Label(PassValues.getAddress()), 1, 4);
		grid.add(new Label("Email:"), 0, 5);
		grid.add(new Label(PassValues.getEmail()), 1, 5);
		grid.add(new Label("Credit Card:"), 0, 6);
		grid.add(new Label(PassValues.getCCard()), 1, 6);
		grid.add(new Label("Expired Date:"), 0, 7);
		grid.add(new Label(PassValues.getExpiredDate()), 1, 7);
		dialog.getDialogPane().setContent(grid);
		dialog.showAndWait();

	}

	@FXML
	void changepass(ActionEvent event) {
		DBConnector db = DBConnector.getInstance();
		if (!oldpassword.getText().isEmpty()) {
			if (oldpassword.getText().equals(db.getpass())) {
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
