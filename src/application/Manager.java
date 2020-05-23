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
	Pane mangpane;

	@FXML
	void addnewbook(ActionEvent event) {
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
		AddBookD frame = new AddBookD(this, "Add");
		// frame.setFlag(true);
		// frame.copies.setVisible(true);
	}

	@FXML
	void modifyexistingbook(ActionEvent event) {
		/// open frame as dialog
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
		AddBookD frame = new AddBookD(this, "Modify");
		// frame.copies.setVisible(false);

	}

	@FXML
	void placeneworder(ActionEvent event) {
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
		DBConnector db = new DBConnector();
		db.getAllOrders();
		Orders frame = new Orders(this);
	}

	@FXML
	void promotion(ActionEvent event) {
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
		Users frame = new Users(this);
	}

	@FXML
	void addpublisher(ActionEvent event) {
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
		Publisher frame = new Publisher(this);
	}

	@FXML
	private void initialize() {
	}

	@FXML
	void reports(ActionEvent event) {
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
		Reports frame = new Reports(this);
	}
}