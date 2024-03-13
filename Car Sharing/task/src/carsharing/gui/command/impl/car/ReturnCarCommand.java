package carsharing.gui.command.impl.car;

import carsharing.dao.CustomerDao;
import carsharing.dao.impl.DbCustomerDao;
import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.model.Customer;

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
