package carsharing.gui.command.impl.car;

import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.model.Customer;

public class ShowRentedCarCommand implements Command {
    private final Customer customer;

    public ShowRentedCarCommand(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void execute(BaseMenuContext menuContext) {
        if (customer.getRentedCar() != null) {
            menuContext.showMessage("Your rented car:\n" +
                    customer.getRentedCar().getName() +
                    "Company:\n" +
                    customer.getRentedCar().getCompany().getName());
        } else {
            menuContext.showMessage("You didn't rent a car!");
        }
        menuContext.keep();
    }
}
