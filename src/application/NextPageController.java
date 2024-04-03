package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NextPageController {

	@FXML
	private Button backToHome;

	@FXML
	private ListView<Customer> customerListView;

	private Customer customer;
	private CustomerList list;

	public void initialize() throws ClassNotFoundException, IOException {
		System.out.println("Initialise");
		list = DataHandler.readCustomerList();
		customerListView.getItems().addAll(list.getCustomers());
		customerListView.setCellFactory(listView -> new CustomerListCell());
	}

	private class CustomerListCell extends javafx.scene.control.ListCell<Customer> {
		private final HBox content;
		private final Label indexLabel;
		private final Label nameLabel;
		private final Region spacer;
		private final Button generateBillButton;
		private final Button generateViewStatementButton;

		public CustomerListCell() {
			content = new HBox();
			nameLabel = new Label();
			indexLabel = new Label();
			spacer = new Region();
			generateBillButton = new Button("Generate Bill");
			generateViewStatementButton = new Button("View Statement");
			HBox.setHgrow(spacer, Priority.ALWAYS);
			content.getChildren().addAll(nameLabel, spacer, generateViewStatementButton, generateBillButton);
			content.setAlignment(Pos.CENTER);
			content.setSpacing(10);
			setContentDisplay(ContentDisplay.RIGHT);
			generateViewStatementButton.setFont(Font.font(12));
			generateBillButton.setFont(Font.font(12));
//    	    ----------------------------------------------
			generateViewStatementButton.setOnAction(event -> {
				try {
					viewBillClicked(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			generateBillButton.setOnAction(event -> {
				try {
					generateBillClicked(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}

		@Override
		protected void updateItem(Customer customer, boolean empty) {
			super.updateItem(customer, empty);

			if (empty || customer == null) {
				setGraphic(null);
			} else {
				int index = customer.getId();
				indexLabel.setText(String.valueOf(index));
				nameLabel.setText(customer.getFullName());
				setGraphic(content);
			}
		}

		private void viewBillClicked(ActionEvent event) throws IOException {
			Customer selectedCustomer = getItem();
			if (selectedCustomer != null) {
				Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				currentStage.close();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("viewBill.fxml"));
				Parent parent = loader.load();
				billViewController billViewController = loader.getController();
				billViewController.setCustomer(selectedCustomer);
				// Build the scene graph
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setTitle("Generated Bills for " + selectedCustomer.getFullName());
				stage.setScene(scene);
				stage.show();
			} else {
				System.out.println("Please select a customer to view the bill.");
			}
		}

		private void generateBillClicked(ActionEvent event) throws IOException {
			Customer selectedCustomer = getItem();
			if (selectedCustomer != null) {
				Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				currentStage.close();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("generateBill.fxml"));
				Parent parent = loader.load();
				GenerateBillController generateBillController = loader.getController();
				generateBillController.setCustomer(selectedCustomer);

				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setTitle("Generate Bill");
				stage.setScene(scene);
				stage.show();
			} else {
				// Handle the case where no customer is selected
				System.out.println("Please select a customer to generate the bill.");
			}
		}
	}

	public void BackToHomeButtonListener(ActionEvent e) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
		// Build the scene graph.
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		// Display our window, using the scene graph.
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();
	}

	public void registerPageButtonListener(ActionEvent e) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("userRegistration.fxml"));

		// Build the scene graph.
		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		// Display our window, using the scene graph.
		stage.setTitle("User Registration Page");
		stage.setScene(scene);
		stage.show();
	}
}