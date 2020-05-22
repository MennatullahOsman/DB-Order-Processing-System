package application;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

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
		Dialog<Pair<String, String>> dialog = new Dialog<>();

		ButtonType OK = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(OK);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField Card = new TextField();
		Card.setPromptText("Card Number");
		TextField Date = new TextField();
		Date.setPromptText("Date");
		Label CardError = new Label("enter valid Card Number");
		grid.add(new Label("Credit Card Number:"), 0, 0);
		grid.add(Card, 1, 0);
		grid.add(CardError, 0, 1);
		grid.add(new Label("Expired Date:"), 0, 2);
		grid.add(Date, 1, 2);

		Node OKButton = dialog.getDialogPane().lookupButton(OK);
		OKButton.setDisable(true);
		CardError.setVisible(false);
		Card.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!Card.getText().matches("\\d*")) {
				OKButton.setDisable(true);
				CardError.setVisible(true);
			} else {
				if (!Card.getText().matches("") && checkLuhn(Card.getText())) {
					OKButton.setDisable(false);
				} else if (Card.getText().matches("")) {
					OKButton.setDisable(true);
					CardError.setVisible(false);
				} else {
					OKButton.setDisable(true);
					CardError.setVisible(true);
				}
			}
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> Card.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == OK) {
				ParentPane.getChildren().remove(0);
				CheckOut.setVisible(false);
				DBConnector.getInstance().checkOut(PassValues.getBookCartISBN());
				PassValues.clearAllCart();
			}
			return null;
		});
		Optional<Pair<String, String>> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("Your choice: " + result.get());
		}
	}

	public void showStage() {
		if (PassValues.getprivilage().equals("manager")) {
			ManageData.setVisible(true);
		} else {
			ManageData.setVisible(false);
		}
		thisStage.showAndWait();
	}

	private static boolean checkLuhn(String cardNo) {
		int[] ints = new int[cardNo.length()];
		for (int i = 0; i < cardNo.length(); i++) {
			ints[i] = Integer.parseInt(cardNo.substring(i, i + 1));
		}
		for (int i = ints.length - 2; i >= 0; i = i - 2) {
			int j = ints[i];
			j = j * 2;
			if (j > 9) {
				j = j % 10 + 1;
			}
			ints[i] = j;
		}
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			sum += ints[i];
		}
		return (sum % 10 == 0);

	}

	@FXML
	public void initialize() {
		userName.setText("Hi, " + PassValues.getUserName());

	}

}