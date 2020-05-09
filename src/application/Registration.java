package application;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Window;

public class Registration {
	  @FXML
	private Text userError;
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
    public void reg(ActionEvent event) throws SQLException {

        Window owner = signup.getScene().getWindow();
        /// ERROR IF IT LEFT EMPTY
        if (usernameup.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your username");
            return;
        }
        /// ERROR IF IT LEFT EMPTY OR CONTAINS A NUMBER
        if (firstname.getText().isEmpty() || firstname.getText().matches(".*\\d.*")) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your first name");
            return;
        }
        /// ERROR IF IT LEFT EMPTY OR CONTAINS A NUMBER
        if (lastname.getText().isEmpty() || lastname.getText().matches(".*\\d.*")) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your last name");
            return;
        }
        /// ERROR IF IT LEFT EMPTY OR CONTAINS UN VALID EMAIL ADDRESS
        if (email.getText().isEmpty() || !validEmail(email.getText())) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email address");
            return;
        }
        /// ERROR IF IT LEFT EMPTY OR CONTAINS NON NUMERIC VALUE
        if (phone.getText().isEmpty() || !isNumeric(phone.getText())) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a valid phone number");
            return;
        }
        /// ERROR IF IT LEFT EMPTY
        if (passwordup.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }
        /// ERROR IF IT LEFT EMPTY
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
        /// ERROR WHEN TRY TO ENTER ALREADY EXISTING USERNAME
        if (DB.existusername(userName)) {
        	showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter another username as this user name already exist!");
                return;
        } else {
        	DB.insertRecord(userName, password, firstName, lastName, emailadd, phonen, shippingadds);
        	showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                    "Welcome " + firstname.getText());
        }
    }

    @FXML
    public void login(ActionEvent event) throws SQLException {

        Window owner = signin.getScene().getWindow();

        System.out.println(usernamein.getText());
        System.out.println(passwordin.getText());
        /// ERROR IF IT LEFT EMPTY
        if (usernamein.getText().isEmpty()) {
        	userError.setVisible(true);
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your user name");
            return;
        }
        /// ERROR IF IT LEFT EMPTY
        if (passwordin.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }

        String username = usernamein.getText();
        String password = passwordin.getText();

        DBConnector DB = new DBConnector();
        boolean flag = DB.validate(username, password);

        if (!flag) {
            infoBox("Please enter correct User Name and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
        }
    }
//    @FXML
//    void testForUserName(ActionEvent event) {
//    	userError.setVisible(false);
//    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public static boolean isNumeric(String str) { 
    	  try {  
    	    Double.parseDouble(str);  
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    	}
    public static boolean validEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
}
