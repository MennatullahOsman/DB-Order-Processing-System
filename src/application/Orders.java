package application;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Orders extends Pane {

	Manager man;
	private Pane root;
	private static int counter = 0;
	private static List<String> ID_Order;
	private static List<String> Time_Stamp;
	private static List<Integer> Quentity;
	private static List<String> ISBN;

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
	private GridPane Grid0, Grid1, Grid2, Grid3, Grid4, Grid5, Grid6, Grid7, Grid8, Grid9;

	@FXML
	private Label ID0, ID1, ID2, ID3, ID4, ID5, ID6, ID7, ID8, ID9;

	@FXML
	private Line ISBN0, ISBN1, ISBN2, ISBN3, ISBN4, ISBN5, ISBN6, ISBN7, ISBN8, ISBN9;

	@FXML
	private Label Quantity0, Quantity1, Quantity2, Quantity3, Quantity4, Quantity5, Quantity6, Quantity7, Quantity8,
			Quantity9;

	@FXML
	private Line Line0, Line1, Line2, Line3, Line4, Line5, Line6, Line7, Line8, Line9;

	@FXML
	private Label TimeStamp0, TimeStamp1, TimeStamp2, TimeStamp3, TimeStamp4, TimeStamp5, TimeStamp6, TimeStamp7,
			TimeStamp8, TimeStamp9;
	@FXML
	private Button Confirm0, Confirm1, Confirm2, Confirm3, Confirm4, Confirm5, Confirm6, Confirm7, Confirm8, Confirm9;

	@FXML
	private Button Previous;

	@FXML
	private Button Next;

	@FXML
	void confirmAction(ActionEvent event) {
		String IDBtn = ((Node) event.getSource()).getId();
		String pattern = "(Confirm)(\\d+)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(IDBtn);
		if (m.find()) {

		}
	}

	@FXML
	void nextAction(ActionEvent event) {

	}

	@FXML
	void previousAction(ActionEvent event) {

	}

	@FXML
	void placeorder(ActionEvent event) {
		DBConnector db = DBConnector.getInstance();
		if (quantity.getText().isEmpty()) {
			quantityerrormsg.setText("Please enter the quantity");
			quantityerrormsg.setVisible(true);
			return;
		} else if (isNumeric(quantity.getText())) {
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
