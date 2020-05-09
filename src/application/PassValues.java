package application;

import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;

public class PassValues {
	private static String Title = "";
	private static List<String> Authers = new ArrayList<String>();
	private static String Publisher = "";
	static int PublicationYear = 0;
	static int SellingPrice = 0;
    public Stage primaryStage;

	public static void setTitle(String Title_set) {
		Title = Title_set;
	}
    public static String getTitele() {
		return Title;
	}
	public static void setAuthers(List<String> Authers_set) {
		Authers = Authers_set;
	}
    public static List<String> getAuthers() {
		return Authers;
	}
    public static void setPublisher(String Publisher_set) {
    	Publisher = Publisher_set;
	}
    public static String getPublisher() {
		return Publisher;
	}
    public static void setPublicationYear(int PublicationYear_set) {
    	PublicationYear = PublicationYear_set;
	}
    public static int getPublicationYear() {
		return PublicationYear;
	}
    public static void setSellingPrice(int SellingPrice_set) {
    	SellingPrice = SellingPrice_set;
	}
    public static int getSellingPrice() {
		return SellingPrice;
	}
    public void setPrimaryStage(Stage stage) {
    	primaryStage = stage;
    }
    public Stage getPrimaryStage() {
    	return primaryStage;
    }
    
}
