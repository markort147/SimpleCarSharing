package carsharing.gui.command.impl.car;

import carsharing.dao.CustomerDao;
import carsharing.dao.impl.DbCustomerDao;
import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.gui.menu.impl.customer.CustomerMenu;
import carsharing.model.Car;
import carsharing.model.Customer;

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
