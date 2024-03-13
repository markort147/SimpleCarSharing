package simplecarsharing.gui.command.impl.customer;

import simplecarsharing.dao.CustomerDao;
import simplecarsharing.dao.impl.DbCustomerDao;
import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.gui.menu.impl.customer.ChooseCustomerMenu;
import simplecarsharing.model.Customer;

import java.util.List;

public class ListCustomersCommand implements Command {

    @Override
    public void execute(BaseMenuContext menuContext) {
        CustomerDao customerDao = new DbCustomerDao();
        List<Customer> customers = customerDao.getAllAsList();

        if (customers.isEmpty()) {
            menuContext.showMessage("The customer list is empty!");
            menuContext.keep();
        } else {
            menuContext.navigateTo(new ChooseCustomerMenu(customers));
        }
        menuContext.keep();
    }
}
