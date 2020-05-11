package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Manager {

    @FXML
    void addnewbook(ActionEvent event) {
    	AddBookD frame = new AddBookD();
    	frame.setFlag(true);
    	/// open frame as dialog
    }

    @FXML
    void modifyexistingbook(ActionEvent event) {
    	AddBookD frame = new AddBookD();
    	frame.setFlag(false);
    	/// open frame as dialog
    }

    @FXML
    void placeneworder(ActionEvent event) {

    }

    @FXML
    void promotion(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
    	/// back to main page
    }
    
    void vieworders(){
    	
    }

}
