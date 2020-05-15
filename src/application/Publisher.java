package application;

import java.util.List;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Publisher {

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private TextField name;

    @FXML
    private Text error;
    
    private ArrayList<String>  addresses = new ArrayList<String>();
    private ArrayList<String>  phones = new ArrayList<String>();

    @FXML
    void exadd(ActionEvent event) {
    	if (address.getText().isEmpty()) {
    		error.setText("ERROR: Please enter the address first");
    	} else {
    		addresses.add(address.getText());
    	}
    }

    @FXML
    void exphone(ActionEvent event) {
    	if (phone.getText().isEmpty()) {
    		error.setText("ERROR: Please enter the phone first");
    	} else {
    		phones.add(phone.getText());
    	}
    }

    @FXML
    void add(ActionEvent event) {
    	if (name.getText().isEmpty()) {
    		error.setText("Error: Please enter publisher name.");
    		return;
    	}
    	if (addresses.get(addresses.size()-1) != address.getText() && !address.getText().isEmpty()) {
    		addresses.add(address.getText());
    	} else if (address.getText().isEmpty() && addresses.size() == 0) {
    		error.setText("Error: Please enter at least one address");
    		return;
    	}
    	if (phones.get(phones.size()-1) != phone.getText() && !phone.getText().isEmpty()) {
    		phones.add(phone.getText());
    	} else if (phone.getText().isEmpty() && phones.size() == 0) {
    		error.setText("Error: Please enter at least one phone");
    		return;
    	}
    	DBConnector db = DBConnector.getInstance();
    	db.addPublisher(name.getText(), phones, addresses);
    	error.setText("Publisher added");
    	
    }

}
