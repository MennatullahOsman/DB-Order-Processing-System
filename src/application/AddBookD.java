package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AddBookD extends Pane {

	Manager man;
	private Pane root;
	private static List<String> dataToModify = new ArrayList<String>();

	public AddBookD(Manager m) {
		man = m;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBookD.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		man.mangpane.getChildren().add(root);
	}

	@FXML
	private Button cancel;

	@FXML
	Button add;

	@FXML
	private MenuItem art;

	@FXML
	private TextField pname;

	@FXML
	private TextField isbn;

	@FXML
	private TextField authers;

	@FXML
	TextField copies;
	/// add label of copies and set not visable
	private TextField orderQ;

	@FXML
	private TextField title;

	@FXML
	Button getdata;

	@FXML
	private TextField thershold;

	@FXML
	private TextField pyear;

	@FXML
	private MenuItem sc;

	@FXML
	private MenuItem geo;

	@FXML
	Button modify;

	@FXML
	private MenuItem re;

	@FXML
	private MenuItem his;

	@FXML
	private TextField price;

	@FXML
	private TextField category;

	@FXML
	private Text error;

	private String currentISBN = null, ctitle, cpname, cpyear, ccat, cthr, cprice, cOQ, cauthors;

	@FXML
	void closedialog(ActionEvent event) {
		/// close this frame
	}

	@FXML
	void addnew(ActionEvent event) {
		DBConnector db = DBConnector.getInstance();
		/// check if all are entered first if not show error msg.
		if (isbn.getText().isEmpty() || title.getText().isEmpty() || pname.getText().isEmpty()
				|| pyear.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty()
				|| thershold.getText().isEmpty() || copies.getText().isEmpty() || orderQ.getText().isEmpty()
				|| authers.getText().isEmpty()) {
			/// error please enter missed data
			error.setText("ERROR: Please enter missed data");
			return;
		} else if (!isNumeric(price.getText()) || !isNumeric(orderQ.getText()) || !isNumeric(thershold.getText())
				|| !isNumeric(copies.getText())) {
			/// error please enter numerical values for threshold, price, copies and order
			/// quantity.
			error.setText("ERROR: Please enter numerical values for threshold, price, copies and order quantity.");
		}
		if (db.publisherExists(pname.getText())) {
			if (!db.bookexist(isbn.getText())) {
				db.addBook(isbn.getText(), title.getText(), pname.getText(), pyear.getText(), category.getText(),
						price.getText(), thershold.getText(), copies.getText(), orderQ.getText(), authers.getText());
			} else {
				error.setText("ERROR: There is a book with this ISBN!");
			}
		} else {
			error.setText("ERROR: There is no publisher with that name please add him first");
		}

	}

	@FXML
	void getdata(ActionEvent event) {
		if (isbn.getText().isEmpty() || !isNumeric(isbn.getText())) {
			/// error please insert valid isbn
			error.setText("ERROR: Please insert valied ISBN");
		} else {
			DBConnector db = DBConnector.getInstance();
			if (db.bookexist(isbn.getText())) {
				db.getbookdata(isbn.getText());
				/// get data from pass data and put it in
				/// ctitle,cpname,cpyear,ccat,cthr,cprice,cauthors and text fields
				dataToModify = PassValues.getDataToModify();
				ctitle = dataToModify.get(0);
				title.setText(ctitle);
				cpname = dataToModify.get(1);
				pname.setText(cpname);
				cpyear = dataToModify.get(2);
				pyear.setText(cpyear);
				cprice = dataToModify.get(3);
				price.setText(cprice);
				ccat = dataToModify.get(4);
				category.setText(ccat);
				cthr = dataToModify.get(5);
				thershold.setText(cthr);
				cOQ = dataToModify.get(6);
				orderQ.setText(cOQ);
				cauthors = dataToModify.get(7);
				authers.setText(cauthors);
				currentISBN = isbn.getText();
			} else {
				/// error this book not found
				error.setText("ERROR: This book wasn't found");
			}
		}
	}

	@FXML
	void modifydata(ActionEvent event) {
		DBConnector db = DBConnector.getInstance();
		if (currentISBN == null) {
			/// error please enter isbn and get data first
			error.setText("ERROR: Please enter the ISBN and click on get data first");
			return;
		} else {
			if (isbn.getText().isEmpty() || title.getText().isEmpty() || pname.getText().isEmpty()
					|| pyear.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty()
					|| thershold.getText().isEmpty() || orderQ.getText().isEmpty() || authers.getText().isEmpty()) {
				/// error please enter missed data
				error.setText("ERROR: Please enter missed data");
				return;
			} else if (!isNumeric(price.getText()) || !isNumeric(orderQ.getText()) || !isNumeric(thershold.getText())) {
				/// error please enter numerical values for threshold, price and order quantity.
				error.setText("ERROR: Please enter numerical values for threshold, price and order quantity.");
			} else if (!db.publisherExists(pname.getText())) {
				error.setText("ERROR: There is no publisher with that name please add him first");
			} else {
				db.modifybook(isbn.getText(), title.getText(), pname.getText(), pyear.getText(), category.getText(),
						price.getText(), thershold.getText(), orderQ.getText(), authers.getText());
			}
		}
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
