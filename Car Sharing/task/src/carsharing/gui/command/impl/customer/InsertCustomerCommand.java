package carsharing.gui.command.impl.customer;

import carsharing.dao.CustomerDao;
import carsharing.dao.impl.DbCustomerDao;
import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.model.Customer;

import java.util.Scanner;

public class InsertCustomerCommand implements Command {

    @Override
    public void execute(BaseMenuContext menuContext) {
        menuContext.showMessage("Enter the customer name:");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        Customer newCustomer = new Customer();
        newCustomer.setName(name);

        CustomerDao customerDao = new DbCustomerDao();
        boolean success = customerDao.insert(newCustomer);

        if(success) {
            menuContext.showMessage("The customer was added!");
        } else {
            menuContext.showMessage("Failed to add the customer.");
        }
        menuContext.keep();
    }

}
