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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
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
    public void reg(ActionEvent event) throws SQLException {

        Window owner = signup.getScene().getWindow();
        if (usernameup.getText().isEmpty()) {
        	userErrorSignup.setVisible(true);
        }
        if (firstname.getText().isEmpty() || firstname.getText().matches(".*\\d.*")) {
            firstErrorSignup.setVisible(true);
        }
        if (lastname.getText().isEmpty() || lastname.getText().matches(".*\\d.*")) {
           secondErrorSignup.setVisible(true);
        }
        if (email.getText().isEmpty() || !validEmail(email.getText())) {
            emailErrorSignup.setVisible(true);
        }
        if (phone.getText().isEmpty() || !isNumeric(phone.getText())) {
            phoneErrorSignup.setVisible(true);
        }
        if (passwordup.getText().isEmpty()) {
        	PassErrorSignup.setVisible(true);
        }
        if (shippingadd.getText().isEmpty()) {
            addressErrorSignup.setVisible(true);
        }

//        String firstName = firstname.getText();
//        String lastName = lastname.getText();
//        String userName = usernameup.getText();
//        String emailadd = email.getText();
//        String password = passwordup.getText();
//        String phonen = phone.getText();
//        String shippingadds = shippingadd.getText();
//        DBConnector DB = new DBConnector();
//        /// ERROR WHEN TRY TO ENTER ALREADY EXISTING USERNAME
//        if (DB.existusername(userName)) {
//        	showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter another username as this user name already exist!");
//                return;
//        } else {
//        	DB.insertRecord(userName, password, firstName, lastName, emailadd, phonen, shippingadds);
//        	showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
//                    "Welcome " + firstname.getText());
//        }
    }

    @FXML
    public void login(ActionEvent event) throws SQLException {
    	
        if (usernamein.getText().isEmpty()) {
        	userErrorLogin.setVisible(true);
        }
        if (passwordin.getText().isEmpty()) {
        	PassErrorLogin.setVisible(true);
        }

        String username = usernamein.getText();
        String password = passwordin.getText();

//        DBConnector DB = new DBConnector();
//        boolean flag = DB.validate(username, password);
//
//        if (!flag) {
//            infoBox("Please enter correct User Name and Password", null, "Failed");
//        } else {
//            infoBox("Login Successful!", null, "Failed");
//        }
    }   	
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