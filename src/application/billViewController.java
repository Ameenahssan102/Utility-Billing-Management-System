package application;


import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class billViewController {
	 public billViewController() {
	        this.billist = new BillList();  // Assuming BillList has a default constructor
	    }
    @FXML
    private Button backButton;
    
    @FXML
    private ListView<Bill> billListView;
    @FXML
	private Text consumer;
    private Customer customer;
   	private CustomerList list;
    private Bill bill;
   	private BillList billist;
    
    public void initialize() throws ClassNotFoundException, IOException {
    	billist = DataHandler.readBillList();
        updateBillListView();
	}
    

	public void setCustomer(Customer customer) {
		this.customer = customer;
		// Update the Text node with the customer's name
		consumer.setText("Statements of "+ customer.getFullName());
		 updateBillListView();
	}

	private void updateBillListView() {
		  if (customer != null) {
	            List<Bill> customerBills = billist.getBillsForCustomer(customer.getId());

	            if (customerBills.isEmpty()) {
//	                 If the bill list is empty, shows message in the center of the ListView
	                Text emptyMessage = new Text("No generated bills for this customer.");
	                emptyMessage.setFont(new Font(14));
	                billListView.setPlaceholder(emptyMessage);
	            } else {
	                billListView.setPlaceholder(null);
	                billListView.getItems().clear();
	                billListView.getItems().addAll(customerBills);
	                billListView.setCellFactory(listView -> new BillListCell());
	            }
	        }
    }
    
    public void BackButtonClicked(ActionEvent e) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("NextPage.fxml"));
		// Build the scene graph.
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		// Display our window, using the scene graph.
		stage.setTitle("Dashboard");
		stage.setScene(scene);
		stage.show();
	}
    
    private class BillListCell extends javafx.scene.control.ListCell<Bill> {
        private final VBox content;
        private final Label nameLabel;

        public BillListCell() {
            content = new VBox();
            nameLabel = new Label();
            content.getChildren().addAll(nameLabel);
            setContentDisplay(ContentDisplay.RIGHT);
        }
        @Override
        protected void updateItem(Bill bill, boolean empty) {
            super.updateItem(bill, empty);
            if (empty || bill == null) {
     
                setGraphic(null);
            } else {
                nameLabel.setText(bill.printInvoice());
                setGraphic(content);
            }
        }
    }
        
             
    public void BackToHomeButtonListener(ActionEvent e) throws IOException {
    	Parent parent = FXMLLoader.load(
	               getClass().getResource("NextPage.fxml"));      
	      // Build the scene graph.
	      Scene scene = new Scene(parent); 
	      Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	      // Display our window, using the scene graph.
	      stage.setTitle("Login"); 
	      stage.setScene(scene);
	      stage.show(); 
    }
}