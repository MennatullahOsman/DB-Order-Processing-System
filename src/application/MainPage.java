package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MainPage{
	private static String Title = "";
	private static List<String> Authers = new ArrayList<String>();
	private static String Publisher = "";
	private static int PublicationYear = 0;
	private static int SellingPrice = 0;
	String choices[] = {"Science", "Art", "Religion", "History" , "Geography"} ;

	  @FXML
	    public static Button Search;
	  
	    @FXML
	    private void SearchAction(ActionEvent event) {
	    	Dialog<String> dialog = new Dialog<>();
	    
	    	ButtonType loginButtonType = new ButtonType("Search", ButtonData.OK_DONE);
	    	dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);

	    	GridPane grid = new GridPane();
	    	grid.setHgap(10);
	    	grid.setVgap(10);
	    	grid.setPadding(new Insets(20, 150, 10, 10));

	    	TextField Title = new TextField();
	    	Title.setPromptText("Title");
	    	TextArea Authers = new TextArea();
	    	Authers.setPromptText("Auther(s)");
	    	TextField Publisher = new TextField();
	    	Publisher.setPromptText("Publisher");
	    	TextField Publication_year = new TextField();
	    	Publication_year.setPromptText("Publication year");
	    	TextField Selling_price = new TextField();
	    	Selling_price.setPromptText("Selling price");
	    	ComboBox<String> Category = new ComboBox<String>(FXCollections.observableArrayList(choices));
	    	Category.setPromptText("Category");
	    	
	    	Label error_publication_year = new Label("error: enter numbers only");
	    	Label error_selling_price = new Label("error: enter numbers only");
	    	
	    	error_publication_year.setVisible(false);
	    	error_selling_price.setVisible(false);

	    	grid.add(new Label("Title:"), 0, 0);
	    	grid.add(Title, 1, 0);
	    	grid.add(new Label("Auther(s):"), 0, 1);
	    	grid.add(Authers, 1, 1);
	    	grid.add(new Label("Publisher:"), 0, 2);
	    	grid.add(Publisher, 1, 2);
	    	grid.add(new Label("Publication year:"), 0, 3);
	    	grid.add(Publication_year, 1, 3);
	    	grid.add(error_publication_year, 1, 4);
	    	grid.add(new Label("Selling price"), 0, 5);
	    	grid.add(Selling_price, 1, 5);
	    	grid.add(error_selling_price, 1, 6);
	    	grid.add(Category, 1, 7);
	    	
	    	Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
	    	
	    	Publication_year.textProperty().addListener((observable, oldValue, newValue) -> {
	    		 if (!Publication_year.getText().matches("\\d*")) {
	    			    error_publication_year.setTextFill(Color.RED);
		    	    	error_publication_year.setVisible(true);
		    	    	loginButton.setDisable(true);
		            } else {
		    	    	error_publication_year.setVisible(false);
		    	    	loginButton.setDisable(false);
		            }
	        });
	    	
	    	Selling_price.textProperty().addListener((observable, oldValue, newValue) -> {
	    		   if (!Selling_price.getText().matches("\\d*")) {
	    			    error_selling_price.setTextFill(Color.RED);
		    	    	error_selling_price.setVisible(true);
		    	    	loginButton.setDisable(true);
		            } else {
		            	error_selling_price.setVisible(false);
		    	    	loginButton.setDisable(false);
		            }
	        });
	    	

            dialog.getDialogPane().setContent(grid);

	      dialog.setResultConverter(dialogButton -> {
	    	    if (dialogButton == loginButtonType && !Publication_year.getText().matches("\\d*") && !Selling_price.getText().matches("\\d*")) {
	    	    	this.Title = Title.getText(); 
		    		 for (String line : Authers.getText().split("\\n")) {
		             	  this.Authers.add(line);
		             }
	 	             this.Publisher = Publisher.getText();    	
	                  if(Publication_year.getText().equals("")) {
		                 PublicationYear = 0;
		              } else {
		            	  PublicationYear = Integer.parseInt(Publication_year.getText());
		              }  
	                  if(Selling_price.getText().equals("")) {
	                	  SellingPrice = 0;
	 	             } else {
	 	            	SellingPrice = Integer.parseInt(Selling_price.getText());
	 	             } 
	    	    }
	    	    return null;
	    	});
	      dialog.showAndWait();
	    }
	   

}
