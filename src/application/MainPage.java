package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPage {

	private final Stage thisStage;

	public MainPage() {
		thisStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
			loader.setController(this);
			thisStage.setScene(new Scene(loader.load()));
			thisStage.setTitle("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button CheckOut;

	@FXML
	private Button ManageData;

	@FXML
	Pane ParentPane;
	@FXML
	public static Button Search;
	@FXML
	public Label userName;// problems happens with set because static

	@FXML
	private void SearchAction(ActionEvent event) {
		PassValues.clearAllSearch();
		if (!ParentPane.getChildren().isEmpty()) {
			ParentPane.getChildren().remove(0);
		}
		SearchBy searchby = new SearchBy(this);
		CheckOut.setVisible(false);
	}

	@FXML
	void ManageAction(ActionEvent event) throws IOException {
		if (!ParentPane.getChildren().isEmpty()) {
			ParentPane.getChildren().remove(0);
		}
		Manager manager = new Manager(this);
		CheckOut.setVisible(false);

	}

	@FXML
	void MyCartAction(ActionEvent event) {
		if (!ParentPane.getChildren().isEmpty()) {
			ParentPane.getChildren().remove(0);
		}
		PassValues.setWhichBtb("Delete From Cart");
		BookList bookList = new BookList(this);
		CheckOut.setVisible(true);
		if (PassValues.getBookCartISBN().isEmpty()) {
			CheckOut.setDisable(true);
		} else {
			CheckOut.setDisable(false);
		}
	}

	@FXML
	void editProfileAction(ActionEvent event) throws IOException {
		if (!ParentPane.getChildren().isEmpty()) {
			ParentPane.getChildren().remove(0);
		}
		Profile profile = new Profile(this);
		CheckOut.setVisible(false);

	}

	@FXML
	void checkOutAction(ActionEvent event) throws IOException {
		ParentPane.getChildren().remove(0);
		PassValues.clearAllCart();
		CheckOut.setVisible(false);
		DBConnector.getInstance().checkOut(PassValues.getBookCartISBN());
	}

	public void showStage() {
		if (PassValues.getprivilage().equals("manager")) {
			ManageData.setVisible(true);
		} else {
			ManageData.setVisible(false);
		}
		thisStage.showAndWait();
	}

	@FXML
	public void initialize() {
		userName.setText("Hi, " + PassValues.getUserName());

	}

}