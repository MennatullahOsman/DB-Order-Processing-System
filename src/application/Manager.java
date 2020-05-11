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
//		frame.setFlag(true);
		frame.add.setVisible(true);
		frame.modify.setVisible(false);
		frame.getdata.setVisible(false);
	}

	@FXML
	void modifyexistingbook(ActionEvent event) {
		/// open frame as dialog
		if (!mangpane.getChildren().isEmpty()) {
			mangpane.getChildren().remove(0);
		}
		AddBookD frame = new AddBookD(this);
//		frame.setFlag(false);
		frame.add.setVisible(false);
		frame.modify.setVisible(true);
		frame.getdata.setVisible(true);
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

	}

	@FXML
	private void initialize() {
	}

}