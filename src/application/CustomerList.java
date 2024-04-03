package application;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Customer> customerList;

	public CustomerList() {
		customerList = new ArrayList<Customer>();
	}

	public List<Customer> getCustomers() {
		return customerList;
	}

	public void addCustomer(Customer s) {
		customerList.add(s);
	}

	public void updateCustomer(Customer updatedCustomer) throws IOException {
		System.out.println(updatedCustomer);
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getId() == updatedCustomer.getId()) {
				customerList.set(i, updatedCustomer);
				DataHandler.writeToFile(this);
				return;
			}
		}
	}

}
