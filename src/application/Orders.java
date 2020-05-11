package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Orders extends Pane{

	Manager man;
	private Pane root;
	public Orders(Manager m) {
		man = m;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Orders.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		man.mangpane.getChildren().add(root);
	}
	
    @FXML
    private TextField quantity;

    @FXML
    private TextField isbn;

    @FXML
    private Text quantityerrormsg;

    @FXML
    private Text isbnerrormsg;
    
    @FXML
    void placeorder(ActionEvent event) {
    	DBConnector db = DBConnector.getInstance();
    	if (quantity.getText().isEmpty()) {
    		quantityerrormsg.setText("Please enter the quantity");
    		quantityerrormsg.setVisible(true);
    		return;
		}else if (isNumeric(quantity.getText())) {
	   		quantityerrormsg.setText("Please enter valid number for quantity");
    		quantityerrormsg.setVisible(true);
    		return;
		} else {
    		quantityerrormsg.setVisible(false);
		}
    	
    	if (isbn.getText().isEmpty()) {
			isbnerrormsg.setText("Please enter the book ISBN");
    		isbnerrormsg.setVisible(true);
    		return;
		} else if (!db.bookexist(isbn.getText())) {
			isbnerrormsg.setText("Book not even exist please add its data first");
    		isbnerrormsg.setVisible(true);
    		return;
		} else {
    		isbnerrormsg.setVisible(false);
		}
    	db.addorder(isbn.getText(), quantity.getText());
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
