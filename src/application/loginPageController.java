package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class loginPageController {

	@FXML
	private Button nextPageButton;

	@FXML
	private Button loginButton;

	@FXML
	private Hyperlink register;

	@FXML
	private TextField emailcontroller;
	@FXML
	private TextField pwdcontroller;
	@FXML
	private ImageView loginImage;

	@FXML
	private Label toastLabel;

	@FXML
	private Image login;

	public void initialize() {
		login = new Image("file:images/login.png");
		loginImage.setImage(login);
	}

	private static final String VALID_USERNAME = "admin123";
	private static final String VALID_PASSWORD = "987654";

	public void loginSubmitButtonListener(ActionEvent e) throws IOException {
		String enteredUsername = emailcontroller.getText();
		String enteredPassword = pwdcontroller.getText();
		if (isValidCredentials(enteredUsername, enteredPassword)) {
			Parent parent = FXMLLoader.load(getClass().getResource("NextPage.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setTitle("Dashboard");
			stage.setScene(scene);
			stage.show();
		} else {
			toastLabel.setVisible(true);
		}
	}

	private boolean isValidCredentials(String username, String password) {
		return username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD);
	}

}