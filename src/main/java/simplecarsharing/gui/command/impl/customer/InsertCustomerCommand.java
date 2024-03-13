package simplecarsharing.gui.command.impl.customer;

import simplecarsharing.dao.CustomerDao;
import simplecarsharing.dao.impl.DbCustomerDao;
import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.model.Customer;

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
