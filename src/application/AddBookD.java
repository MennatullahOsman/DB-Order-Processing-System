package application;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AddBookD extends Pane {

	Manager man;
	private Pane root;
	private static List<String> dataToModify = new ArrayList<String>();

	public AddBookD(Manager m, String whitchbtn) {
		man = m;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBookD.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		man.mangpane.getChildren().add(root);
		if (whitchbtn.equals("Modify")) {
			getdata.setVisible(true);

		} else {
			add.setVisible(true);
			viewAddModifyBook();
		}
	}

	@FXML
	private Label CategoryLabel;

	@FXML
	private Label TitleLabel;

	@FXML
	private Label PriceLabel;

	@FXML
	private Label PublisherLabel;

	@FXML
	private Label PublicationYearLabel;

	@FXML
	private Label ThersholdLabel;

	@FXML
	private Label AuthorsLabel;

	@FXML
	private TextField isbn;

	@FXML
	private TextField category;

	@FXML
	private MenuItem sc;

	@FXML
	private MenuItem re;

	@FXML
	private MenuItem art;

	@FXML
	private MenuItem his;

	@FXML
	private MenuItem geo;

	@FXML
	private TextField price;

	@FXML
	private TextField pname;

	@FXML
	private TextField pyear;

	@FXML
	private TextField thershold;

	@FXML
	private TextField title;

	@FXML
	private Text notification;

	@FXML
	private Button cancel;

	@FXML
	Button add;

	@FXML
	Button getdata;

	@FXML
	Button modify;

	@FXML
	private Label CpiesLabel;

	@FXML
	private Label QuantityLabel;

	@FXML
	TextField copies;

	@FXML
	private TextField orderQ;

	@FXML
	private Text error;

	@FXML
	private TextArea Authers;

	@FXML
	private Label ISBNLabel;

	private String currentISBN = null, ctitle, cpname, cpyear, ccat, cthr, cprice, cOQ, cauthors;

	@FXML
	void closedialog(ActionEvent event) {
		/// close this frame
	}

	@FXML
	void addnew(ActionEvent event) throws ParseException {
		DBConnector db = DBConnector.getInstance();
		/// check if all are entered first if not show error msg.
		if (isbn.getText().isEmpty() || title.getText().isEmpty() || pname.getText().isEmpty()
				|| pyear.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty()
				|| thershold.getText().isEmpty() || copies.getText().isEmpty() || orderQ.getText().isEmpty()
				|| Authers.getText().isEmpty()) {
			/// error please enter missed data
			error.setText("ERROR: Please enter missed data");
			return;
		} else if (!isNumeric(price.getText()) || !isNumeric(orderQ.getText()) || !isNumeric(thershold.getText())
				|| !isNumeric(copies.getText())) {
			/// error please enter numerical values for threshold, price, copies and order
			/// quantity.
			error.setText("ERROR: Please enter numerical values for threshold, price, copies and order quantity.");
		}
		List<String> authers = new ArrayList<String>();
		for (String line : Authers.getText().split("\\n")) {
			authers.add(line);
		}
		if (!db.bookexist(isbn.getText())) {
			db.addBook(isbn.getText(), title.getText(), pname.getText(), pyear.getText(), category.getText(),
					price.getText(), thershold.getText(), copies.getText(), orderQ.getText(), authers);
		}
	}

	@FXML
	void getdata(ActionEvent event) {
		if (isbn.getText().isEmpty() || !isNumeric(isbn.getText())) {
			/// error please insert valid isbn
			error.setVisible(true);
			error.setText("ERROR: Please insert valied ISBN");
		} else {
			viewAddModifyBook();
			modify.setVisible(true);
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
				Authers.setText(cauthors);
				currentISBN = isbn.getText();
			} else {
				/// error this book not found
				error.setText("ERROR: This book wasn't found");
			}
		}
	}

	@FXML
	void modifydata(ActionEvent event) {
		if (currentISBN == null) {
			/// error please enter isbn and get data first
			error.setText("ERROR: Please enter the ISBN and click on get data first");
			return;
		} else {
			if (isbn.getText().isEmpty() || title.getText().isEmpty() || pname.getText().isEmpty()
					|| pyear.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty()
					|| thershold.getText().isEmpty() || orderQ.getText().isEmpty() || Authers.getText().isEmpty()) {
				/// error please enter missed data
				error.setText("ERROR: Please enter missed data");
				return;
			} else if (!isNumeric(price.getText()) || !isNumeric(orderQ.getText()) || !isNumeric(thershold.getText())) {
				/// error please enter numerical values for threshold, price and order quantity.
				error.setText("ERROR: Please enter numerical values for threshold, price and order quantity.");
			} else {
				List<String> authers = new ArrayList<String>();
				for (String line : Authers.getText().split("\\n")) {
					authers.add(line);
				}
				DBConnector db = DBConnector.getInstance();
				db.modifybook(isbn.getText(), title.getText(), pname.getText(), pyear.getText(), category.getText(),
						price.getText(), thershold.getText(), orderQ.getText(), authers);
			}
		}
	}

	private void viewAddModifyBook() {
		CategoryLabel.setVisible(true);
		TitleLabel.setVisible(true);
		PriceLabel.setVisible(true);
		PublisherLabel.setVisible(true);
		PublicationYearLabel.setVisible(true);
		ThersholdLabel.setVisible(true);
		AuthorsLabel.setVisible(true);
		QuantityLabel.setVisible(true);
		CpiesLabel.setVisible(true);
		isbn.setVisible(true);
		category.setVisible(true);
		price.setVisible(true);
		pname.setVisible(true);
		pyear.setVisible(true);
		thershold.setVisible(true);
		title.setVisible(true);
		notification.setVisible(true);
		cancel.setVisible(true);
		orderQ.setVisible(true);
		copies.setVisible(true);
		Authers.setVisible(true);

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