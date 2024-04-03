package application;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fullName;
	private String address;
	private double ElectricityReading;
	private double GasReading;
	private LocalDate dateMovedIn;
	private int tariff;
	private String phone;
	private String email;
	private int id;
	private static int customerCount = 0;
	private String tariffPlan;

	public Customer(double gasReading,double electricityReading) {
		fullName = "";
		address = "";
		this.ElectricityReading = electricityReading;
        this.GasReading = gasReading;
		tariff =1;
		phone = "";
		dateMovedIn = null;
		email = "";
		this.id = customerCount++;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public double getElectricityReading() {
		return ElectricityReading;
	}

	public void setElectricityReading(double electricityReading) {
		this.ElectricityReading = electricityReading;
	}

	public double getGasReading() {
		return GasReading;
	}

	public void setGasReading(double gasReading) {
		this.GasReading = gasReading;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = 1000+ id;
	}

	public LocalDate getDateMovedIn() {
		return dateMovedIn;
	}

	public void setDateMovedIn(LocalDate dateMovedIn) {
		this.dateMovedIn = dateMovedIn;
	}

	public static int getCustomerCount() {
		return customerCount;
	}

	public static void setCustomerCount(int customerCount) {
		Customer.customerCount = customerCount;
	}

	
	public int getTariff() {
		return tariff;
	}

	public void setTariff(int tariff) {
		this.tariff = tariff;
	}
	
	public String getTariffPlan(int tariff) {
		if(tariff == 1) {
		 tariffPlan = "Standard Variable Tariff";
		}else if(tariff == 2) {
			tariffPlan = "Fixed Tariff";
		}else if(tariff == 3) {
			tariffPlan = "Economy 7";
		}else {
			tariffPlan = "";
		}
		return tariffPlan;
	}

	@Override
	public String toString() {
		return "Customer [name=" + fullName + ", address=" + address + ", Tariff Plan=" + getTariffPlan(tariff)
				+ ",Initial Electric Reading =" + ElectricityReading +"Kwh,,Initial Gas Reading =" + GasReading +"Kwh, date MovedIn=" + dateMovedIn + ", customerId=" + id + "]";
	}
}