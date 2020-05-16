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
		AddBookD frame = new AddBookD(this);
		// frame.setFlag(true);
		frame.add.setVisible(true);
		frame.modify.setVisible(false);
		frame.getdata.setVisible(false);
		frame.copies.setVisible(true);
	}

	@FXML
	void modifyexistingbook(ActionEvent event) {
		/// open frame as dialog
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
		AddBookD frame = new AddBookD(this);
		// frame.setFlag(false);
		frame.add.setVisible(false);
		frame.modify.setVisible(true);
		frame.getdata.setVisible(true);
		frame.copies.setVisible(false);
		/// label of copies set visible false
	}

	@FXML
	void placeneworder(ActionEvent event) {
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
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

}