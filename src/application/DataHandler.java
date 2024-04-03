package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;


public class DataHandler {
	private static String customerListFileName;
	private static String billListFileName;
	
	public static void readProperties(Properties config) {
		customerListFileName = config.getProperty("customerlist.file");
		billListFileName  = config.getProperty("billList.file");
	}
	
	
	// Serialize the object to a file
	public static void doSerialize(Object obj, String outputFile) throws IOException {
        FileOutputStream fileTowrite = new FileOutputStream(outputFile);
        ObjectOutputStream objTowrite = new ObjectOutputStream(fileTowrite);
        objTowrite.writeObject(obj);
        fileTowrite.close();
    }

    // Deserialize the Java object from a given file
    public static Object doDeserialize(String inputFile) throws IOException, ClassNotFoundException {
    	Object obj = new Object();
    	File f = new File (inputFile);
    	if (f.exists()) {
    	FileInputStream fileToread = new FileInputStream(inputFile);
        ObjectInputStream objToread = new ObjectInputStream(fileToread);
    	  if (f.length() > 0) {
            obj = objToread.readObject();
            objToread.close();
    	   } else {
    		   System.out.println("File " + inputFile + " is empty");
    	   }
    	}
    	  else {
    		  System.out.println("File " + inputFile + " does not exist");
    	  }
        return obj;
    }
    
	public static void writeToFile(CustomerList list) throws IOException {
	      doSerialize(list, customerListFileName);
	      System.out.println("The serialized objects " +"were written to "+ customerListFileName);	
	}
    
	public static CustomerList readCustomerList() throws IOException, ClassNotFoundException {
		CustomerList list = new CustomerList();
		Object obj;
		obj = doDeserialize(customerListFileName);
		if (obj instanceof CustomerList)
			list = (CustomerList) obj;
		System.out.println("list size: "+list.getCustomers().size());
		
		// display list
		if (list.getCustomers().size() > 0) {
			System.out.println("Consumers in the list are: ");
			for (int i = 0; i < list.getCustomers().size(); i++) {
				System.out.println("Consumer Name: " + list.getCustomers().get(i));
			}
		}
		return list;
	}
	
	
	public static void writeToFile(BillList list) throws IOException {
	      doSerialize(list, billListFileName);
	      System.out.println("The serialized objects " +"were written to "+ billListFileName);	
	}
	
	public static BillList readBillList() throws IOException, ClassNotFoundException {
		BillList list = new BillList();
		Object obj = doDeserialize(billListFileName);
		if (obj instanceof BillList)
			list = (BillList) obj;
		System.out.println("list size: "+list.getBills().size());		
		// display list
		if (list.getBills().size() > 0) {
			System.out.println("Bills in the list are: ");
			for (int i = 0; i < list.getBills().size(); i++) {
				System.out.println("Bill: " + list.getBills().get(i));
			}
		}
		return list;
	}
}
