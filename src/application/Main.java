package application;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
		private static final Properties config = new Properties();
		public static void initialise() throws FileNotFoundException, IOException {
			config.load(new FileInputStream("system.properties"));
			DataHandler.readProperties(config);
		}
		
		
		public static void main(String[] args) throws FileNotFoundException, IOException {
			initialise();
			launch(args);
		}
		
		@Override
		public void start(Stage stage) throws Exception {
			  // Load the FXML file.
		      Parent parent = FXMLLoader.load(
		               getClass().getResource("loginScreen.fxml")); 
		      // Build the scene graph.
		      Scene scene = new Scene(parent); 
		      
		      // Display our window, using the scene graph.
		      stage.setTitle("Login"); 
		      stage.setScene(scene);
		      stage.show();	
		}

	}
