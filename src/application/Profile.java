package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Profile {

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
	private String first, last, emailadd, phonenum, shipadd, ccn, ed;

	@FXML
	void editData(ActionEvent event) {
		first = firstname.getText();
		last = lastname.getText();
		emailadd = email.getText();
		phonenum = phone.getText();
		shipadd = shippingadd.getText();
		ccn = cc.getText();
		ed = edate.getText();
		// DB.editData(first, last, emailadd, phonenum, shipadd, ccn, ed);
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
	void BACK(ActionEvent event) {
		/// OPEN THE PREV FRAME.
	}
}
