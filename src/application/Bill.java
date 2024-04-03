package application;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * stores information of each bill
 */
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private double billAmount;
	private LocalDate generatedDate;
	private static int billCount = 0;
	private double gasmeterReading;
	private double elmeterReading;
	private int id;
	
	public Bill(Customer customer, double billAmount, double gasmeterReading,double elmeterReading,LocalDate generatedDate
			) {
		this.customer = customer;
		this.billAmount = billAmount;
		this.gasmeterReading = gasmeterReading;
		this.elmeterReading = elmeterReading;
		this.generatedDate = generatedDate;
		this.id = billCount++;
	}

	public Customer getConsumer() {
		return customer;
	}

	public void setConsumer(Customer customer) {
		this.customer = customer;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public LocalDate getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(LocalDate generatedDate) {
		this.generatedDate = generatedDate;
	}

	public double getgasMeterReading() {
		return gasmeterReading;
	}

	public void setgasMeterReading(double meterReading) {
		this.gasmeterReading = meterReading;
	}

	public double getElmeterReading() {
		return elmeterReading;
	}

	public void setElmeterReading(double elmeterReading) {
		this.elmeterReading = elmeterReading;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getBillCount() {
		return billCount;
	}

	public static void setBillCount(int billCount) {
		Bill.billCount = billCount;
	}

	@Override
	public String toString() {
		return "Bill [customer =" + customer.getFullName() + ",bill Amount = " + billAmount + ",generatedDate = "
				+ generatedDate + ", bill id =" + id + " ]";
	}
	public String printInvoice() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String str = "\n";
//		str += "-----------------------\n";
		str += "Bill Id: "+id +"\n";
		System.out.println("Bill Id: "+id);
		str += "Date: "+formatter.format(generatedDate) +"\n";
		System.out.println("Generated Date: "+formatter.format(generatedDate));
	    long diffInDays = ChronoUnit.DAYS.between(customer.getDateMovedIn(), generatedDate);
	    str += "Total number of days: "+ diffInDays + "\n";
	    str += "Energy Reading: "+ elmeterReading + "\n";
	    str += "Gas Reading: "+ gasmeterReading + "\n";
		System.out.println("Total number of days: "+ diffInDays);
		DecimalFormat format = new DecimalFormat("0.00");
		str +=  "Total amount to pay: £"+ format.format((billAmount)) + "\n";
		System.out.println("Total amount to pay: £"+ format.format((billAmount)));
		return str;
	}	
}
