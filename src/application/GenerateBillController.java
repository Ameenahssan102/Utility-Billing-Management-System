package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GenerateBillController {
	public GenerateBillController() {
		this.billlist = new BillList(); // Assuming BillList has a default constructor
	}

	@FXML
	private DialogPane dialog;

	@FXML
	private TextField gasReading;

	@FXML
	private TextField electricReading;

	@FXML
	private Button generate;

	@FXML
	private Button backButton;
	@FXML
	private TextArea viewGeneratedBill;

	private Customer customer;
	private CustomerList customerlist;
	@FXML
	private Text dayElectricReadingLabel;

	@FXML
	private Text tariff1;
	@FXML
	private Text tariff12;
	@FXML
	private Text tariff2;
	@FXML
	private Text tariff3;
	@FXML
	private Text tariff31;

	@FXML
	private TextField dayElectricReading;

	@FXML
	private Text nightElectricReadingLabel;

	@FXML
	private Text electricReadingLabel;

	@FXML
	private TextField nightElectricReading;

	@FXML
	private Text consumer;
	private BillList billlist;
	private Bill bill;
	private double lastEReading;
	private double lastGReading;

	public void initialize() throws ClassNotFoundException, IOException {
		System.out.println("Initialise");
		customerlist = DataHandler.readCustomerList(); // reading existing customer list from file
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;

		// Update the Text node with the customer's name
		consumer.setText(customer.getFullName());
		lastGReading = customer.getGasReading();
		lastEReading = customer.getElectricityReading();
		if (customer.getTariff() == 3) {
			electricReadingLabel.setVisible(false);
			electricReading.setVisible(false);
			dayElectricReadingLabel.setVisible(true);
			dayElectricReading.setVisible(true);
			nightElectricReadingLabel.setVisible(true);
			nightElectricReading.setVisible(true);
			tariff3.setVisible(true);
			tariff31.setVisible(true);
			tariff1.setVisible(false);
			tariff12.setVisible(false);
			tariff2.setVisible(false);

		} else {
			if (customer.getTariff() == 1) {
				tariff1.setVisible(true);
				tariff12.setVisible(true);
				tariff2.setVisible(false);
			} else if (customer.getTariff() == 2) {
				tariff12.setVisible(true);
				tariff2.setVisible(true);
				tariff1.setVisible(false);
			}
			tariff3.setVisible(false);
			tariff31.setVisible(false);
			electricReadingLabel.setVisible(true);
			electricReading.setVisible(true);
			dayElectricReadingLabel.setVisible(false);
			dayElectricReading.setVisible(false);
			nightElectricReadingLabel.setVisible(false);
			nightElectricReading.setVisible(false);

		}
	}

	public void GenerateButtonClicked() throws IOException, ClassNotFoundException {
		double gasReadings = Double.parseDouble(gasReading.getText());
		double electrReading = Double.parseDouble(electricReading.getText());
		double cMelectricReading = electrReading - lastEReading;
		double cMgasReading = gasReadings - lastGReading;
		EnergyCalculator energyCalculator = new EnergyCalculator();
		LocalDate currentdate = LocalDate.now();
		int diffInDaysInt = (int) ChronoUnit.DAYS.between(customer.getDateMovedIn(), currentdate);
//		----------------------------------------------
		Bill bill = new Bill(customer, billCalculator(energyCalculator, customer.getTariff(), cMgasReading,
				cMelectricReading, diffInDaysInt, 0, 0), gasReadings, electrReading, currentdate);
		viewGeneratedBill.setText(bill.printInvoice());
		customer.setElectricityReading(electrReading);
		customer.setGasReading(gasReadings);
		if (this.customerlist != null) {
			this.customerlist.updateCustomer(customer);
		} else {
			System.out.println("Error while updating Customer..");
		}
//		----------------------------------------------
		billlist.addBill(bill);
		DataHandler.writeToFile(billlist);
	}

	public double billCalculator(EnergyCalculator energyCalculator, int tariff, double gas, double electricity,
			int days, double electricityDayUsage, double electricityNightUsage) {
		if (tariff == 1) {
			return EnergyCalculator.calculateSVTCost(electricity, gas, days);
		} else if (tariff == 2) {
			return EnergyCalculator.calculateFixedCost(electricity, gas, days);
		} else {
			return EnergyCalculator.calculateEconomy7Cost(electricityDayUsage, electricityNightUsage, gas, days);
		}
	}

	public void BackButtonClicked(ActionEvent e) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("NextPage.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setTitle("Dashboard");
		stage.setScene(scene);
		stage.show();
	}
}
