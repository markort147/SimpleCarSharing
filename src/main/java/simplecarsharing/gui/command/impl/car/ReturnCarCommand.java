package simplecarsharing.gui.command.impl.car;

import simplecarsharing.dao.CustomerDao;
import simplecarsharing.dao.impl.DbCustomerDao;
import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.model.Customer;

public class ReturnCarCommand implements Command {

    private final Customer customer;

    public ReturnCarCommand(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void execute(BaseMenuContext menuContext) {
        if (customer.getRentedCar() != null) {
            customer.setRentedCar(null);
            CustomerDao customerDao = new DbCustomerDao();
            customerDao.update(customer);
            menuContext.showMessage("You've returned a rented car!");
        } else {
            menuContext.showMessage("You didn't rent a car!");
        }
        menuContext.keep();
    }
}
