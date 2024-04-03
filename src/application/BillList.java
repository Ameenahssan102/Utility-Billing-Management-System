package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BillList implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Bill> billList;

	public BillList() {
		billList = new ArrayList<Bill>();
	}

	public List<Bill> getBills() {
		return billList;
	}

	public void addBill(Bill b) {
		billList.add(b);
	}

	public void setBillList(List<Bill> billList) {
		this.billList = billList;
	}

	public List<Bill> getBillsForCustomer(int customerId) {
		return billList.stream().filter(bill -> bill.getConsumer().getId() == customerId).collect(Collectors.toList());
	}
}
