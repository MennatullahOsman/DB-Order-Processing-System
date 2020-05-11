package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	Pane ParentPane;
	@FXML
	public static Button Search;

	@FXML
	private void SearchAction(ActionEvent event) {
		SearchBy searchby = new SearchBy(this);
	}

	@FXML
	void ManageAction(ActionEvent event) throws IOException {
		Parent sign = FXMLLoader.load(getClass().getResource("Manager.fxml"));
		Scene signIn = new Scene(sign);
		signIn.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = new Stage();
		window.setScene(signIn);
		window.show();
	}

	@FXML
	void MyCartAction(ActionEvent event) {

	}

	@FXML
	void editProfileAction(ActionEvent event) throws IOException {
		Parent sign = FXMLLoader.load(getClass().getResource("Profile.fxml"));
		Scene signIn = new Scene(sign);
		signIn.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = new Stage();
		window.setScene(signIn);
		window.show();
	}

	public void showStage() {
		thisStage.showAndWait();
	}

	@FXML
	public void initialize() {

	}
}
