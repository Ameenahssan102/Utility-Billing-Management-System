package application;

import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegistrationController {

    @FXML
    private TextField fullName;

    @FXML
    private TextField gasReading;
    
    @FXML
    private TextField ElectricReading;

    @FXML
    private TextArea address;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private MenuButton chooseTariff;
    
    @FXML
	private DatePicker dateMovedIn;

    @FXML
    private MenuButton fuelType;

    @FXML
    private Button register;

    @FXML
    private Button backButton;
    
    private Customer customer;
   	private CustomerList list;
    private int tariffValue;

  
    public void initialize() throws ClassNotFoundException, IOException {
		System.out.println("Initialise");
		list = DataHandler.readCustomerList(); // read existing customer list from file
		Customer.setCustomerCount(list.getCustomers().size());
		chooseTariff.getItems().forEach(item -> {
            item.setOnAction(this::handleTariffSelection);
        });
		customer = new Customer(0,0);
	}

	public void registerButtonClicked() throws IOException, ClassNotFoundException {
		String name = fullName.getText();
		double igasReading = Double.parseDouble(gasReading.getText());
		double iElectrReading = Double.parseDouble(ElectricReading.getText());
		String tphone = phone.getText();
		String eemail = email.getText();
		String caddress = address.getText();
		LocalDate date = dateMovedIn.getValue();
//--------------------------------------------------------->
		customer.setFullName(name);
		customer.setGasReading(igasReading);
		customer.setPhone(tphone);
		customer.setEmail(eemail);
		customer.setElectricityReading(iElectrReading);
		customer.setAddress(caddress);
		customer.setDateMovedIn(date);
		customer.setTariff(tariffValue);
//		--------------------------------------------------->
		list.addCustomer(customer);
		DataHandler.writeToFile(list);
	}
	
	public void movedInDateListener() {
		LocalDate date = dateMovedIn.getValue();
		System.out.println("Selected Date: " + date);
	}

    public void BackButtonClicked(ActionEvent e) throws IOException {
    	Parent parent = FXMLLoader.load(
	               getClass().getResource("NextPage.fxml"));      
	      // Build the scene graph.
	      Scene scene = new Scene(parent); 
	      Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	      // Display our window, using the scene graph.
	      stage.setTitle("Login Page"); 
	      stage.setScene(scene);
	      stage.show(); 
    }

    private void handleTariffSelection(ActionEvent event) {
        if (event.getSource() instanceof MenuItem) {
            MenuItem selectedMenuItem = (MenuItem) event.getSource();
            String tariffName = selectedMenuItem.getText();
            int tariffValue2 = Integer.parseInt(selectedMenuItem.getUserData().toString());
            System.out.println("Selected Tariff: " + tariffName + ", Value: " + tariffValue2);
            chooseTariff.setText(tariffName);
            tariffValue = tariffValue2;
        }
    }
}