package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public void start1(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			System.out.print("text ");
			} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override	
    public void start(Stage stage) throws Exception {
    	System.out.println(getClass());
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("Registration.fxml"));
    	Parent root = (Parent) loader.load();
//    	Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
