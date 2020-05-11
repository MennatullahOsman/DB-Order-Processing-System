package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/// NEED TO RUN THIS FIRST
//add.setVisible(flag);
//modify.setVisible(!flag);
//getdata.setVisible(!flag);

public class AddBookD {

	@FXML
	private Button cancel;

	@FXML
	private Button add;

	@FXML
	private MenuItem art;

	@FXML
	private TextField pname;

	@FXML
	private TextField isbn;

	@FXML
	private TextField authers;

	@FXML
	private TextField title;

	@FXML
	private Button getdata;

	@FXML
	private TextField thershold;

	@FXML
	private TextField pyear;

	@FXML
	private MenuItem sc;

	@FXML
	private MenuItem geo;

	@FXML
	private Button modify;

	@FXML
	private MenuItem re;

	@FXML
	private MenuItem his;

	@FXML
	private TextField price;

	@FXML
	private TextField category;

	private String currentISBN = null, ctitle, cpname, cpyear, ccat, cthr, cprice, cauthors;
	private boolean flag = false; // add true, modify false

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@FXML
	void closedialog(ActionEvent event) {
		/// close this frame
	}

	@FXML
	void addnew(ActionEvent event) {
		DBConnector db = DBConnector.getInstance();
		if (!db.bookexist(isbn.getText())) {
			db.addBook(isbn.getText(), title.getText(), pname.getText(), pyear.getText(), category.getText(),
					price.getText(), thershold.getText(), authers.getText());
		}
	}

	@FXML
	void getdata(ActionEvent event) {
		if (isbn.getText().isEmpty()) {
			/// error please insert isbn
		} else {
			DBConnector db = DBConnector.getInstance();
			if (db.bookexist(isbn.getText())) {
				db.getbookdata(isbn.getText());
				/// get data from pass data and put it in
				/// ctitle,cpname,cpyear,ccat,cthr,cprice,cauthors and text fields
				currentISBN = isbn.getText();
			} else {
				/// error this book not found
			}
		}
	}

	@FXML
	void modifydata(ActionEvent event) {
		if (currentISBN == null) {
			/// error please enter isbn and get data first
		} else {
			if (isbn.getText().isEmpty() || title.getText().isEmpty() || pname.getText().isEmpty()
					|| pyear.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty()
					|| thershold.getText().isEmpty() || authers.getText().isEmpty()) {
				/// error please enter missed data
			} else {
				DBConnector db = DBConnector.getInstance();
				db.modifybook(isbn.getText(), title.getText(), pname.getText(), pyear.getText(), category.getText(),
						price.getText(), thershold.getText(), authers.getText());
			}
		}
	}

}
