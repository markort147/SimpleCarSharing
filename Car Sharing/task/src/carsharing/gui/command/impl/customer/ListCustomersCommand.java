package carsharing.gui.command.impl.customer;

import carsharing.dao.CustomerDao;
import carsharing.dao.impl.DbCustomerDao;
import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.gui.menu.impl.customer.ChooseCustomerMenu;
import carsharing.model.Customer;

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
