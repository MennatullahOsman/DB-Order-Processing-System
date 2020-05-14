package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SearchBy extends Pane {
	MainPage mainPage;
	private Pane root;

	public SearchBy(MainPage main) {
		mainPage = main;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchBy.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		main.ParentPane.getChildren().add(root);
	}

	@FXML
	private TextField Title;

	@FXML
	private TextArea Authers;

	@FXML
	private TextField Publisher;

	@FXML
	private TextField PublicationYear;

	@FXML
	private TextField SellingPrice;

	@FXML
	private Text SellingPriceError;

	@FXML
	private Text PublicationYearError;

	@FXML
	private ComboBox<String> Category;

	@FXML
	private Button OK;

	@FXML
	void okAction(ActionEvent event) {
		int Publication_year = 0;
		double Selling_price = 0.0;
		String Categories = "";
		List<String> Auther = new ArrayList<String>();
		for (String line : Authers.getText().split("\\n")) {
			Auther.add(line);
		}
		PassValues.setPublisher(Publisher.getText());
		if (!PublicationYear.getText().equals("")) {
			Publication_year = Integer.parseInt(PublicationYear.getText());
		}
		if (!SellingPrice.getText().equals("")) {
			Selling_price = Integer.parseInt(SellingPrice.getText());
		}
		if (!Category.getSelectionModel().isEmpty()) {
			Categories = Category.getSelectionModel().getSelectedItem().toString();
		}
		DBConnector.getInstance().bookSearch(Title.getText(), Publisher.getText(), Categories, Publication_year,
				Selling_price, Auther);
		mainPage.ParentPane.getChildren().remove(root);
		PassValues.setWhichBtb("Add To Cart");
		BookList bookList = new BookList(mainPage);
	}

	@FXML
	void priceCheckAction(KeyEvent event) {
		if (!SellingPrice.getText().matches("\\d*")) {
			SellingPriceError.setVisible(true);
			OK.setDisable(true);
		} else {
			SellingPriceError.setVisible(false);
			OK.setDisable(false);
		}
	}

	@FXML
	void publicationYearCheckAction(KeyEvent event) {
		if (!PublicationYear.getText().matches("\\d*")) {
			PublicationYearError.setVisible(true);
			OK.setDisable(true);
		} else {
			PublicationYearError.setVisible(false);
			OK.setDisable(false);
		}
	}

	@FXML
	private void initialize() {
		Category.setItems(FXCollections.observableArrayList("Science", "Art", "Religion", "History", "Geography"));
	}
}
