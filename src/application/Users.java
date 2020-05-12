package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Users extends Pane{

	Manager man;
	private Pane root;
	public Users(Manager m) {
		man = m;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Users.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		man.mangpane.getChildren().add(root);
	}
	@FXML
	private TextArea userdata;

	@FXML
	private TextField username;

	@FXML
	private Text errormsg;

	@FXML
	void getdata(ActionEvent event) {
		if (username.getText().isEmpty()) {
			errormsg.setText("Please Enter The UserName");
			errormsg.setVisible(true);
		} else {
			errormsg.setVisible(false);
			DBConnector db = DBConnector.getInstance();
			userdata.setText(db.getuserdata(username.getText()));
		}
	}

	@FXML
	void promot(ActionEvent event) {
		if (username.getText().isEmpty()) {
			errormsg.setText("Please Enter The UserName");
			errormsg.setVisible(true);
		} else {
			errormsg.setVisible(false);
			DBConnector db = DBConnector.getInstance();
			db.promote(username.getText());
		}
	}

}
