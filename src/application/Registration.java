package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

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
    public void register(ActionEvent event) throws SQLException {

        Window owner = signup.getScene().getWindow();

        if (usernameup.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your username");
            return;
        }

        if (firstname.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your first name");
            return;
        }
        
        if (lastname.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your last name");
            return;
        }
        if (email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email address");
            return;
        }
        if (phone.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your phone");
            return;
        }
        if (passwordup.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }
        if (shippingadd.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter the shipping address");
            return;
        }

        String firstName = firstname.getText();
        String lastName = lastname.getText();
        String userName = usernameup.getText();
        String emailadd = email.getText();
        String password = passwordup.getText();
        String phonen = phone.getText();
        String shippingadds = shippingadd.getText();
        DBConnector DB = new DBConnector();
        DB.insertRecord(userName, password, firstName, lastName, emailadd, phonen, shippingadds);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
            "Welcome " + firstname.getText());
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
