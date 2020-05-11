package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Manager extends Pane {
	MainPage mainPage;
	private Pane root;

	public Manager(MainPage main) {
		mainPage = main;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		main.ParentPane.getChildren().add(root);
	}

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

	void vieworders() {

	}

	@FXML
	private void initialize() {
	}

}