package simplecarsharing.gui.command.impl.car;

import simplecarsharing.dao.CustomerDao;
import simplecarsharing.dao.impl.DbCustomerDao;
import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.gui.menu.impl.customer.CustomerMenu;
import simplecarsharing.model.Car;
import simplecarsharing.model.Customer;

public class RentCarCommand implements Command {

    private final Car car;
    private final Customer customer;

    public RentCarCommand(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
    }

    @Override
    public void execute(BaseMenuContext menuContext) {
        customer.setRentedCar(car);
        CustomerDao customerDao = new DbCustomerDao();
        customerDao.update(customer);
        menuContext.showMessage(String.format("You rented '%s'", car.getName()));
        menuContext.backTo(CustomerMenu.class);
    }
}
