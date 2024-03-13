package carsharing.gui.menu.impl.customer;

import carsharing.gui.command.impl.company.ListCompaniesForRentingCommand;
import carsharing.gui.command.impl.car.ReturnCarCommand;
import carsharing.gui.command.impl.car.ShowRentedCarCommand;
import carsharing.gui.menu.Menu;
import carsharing.model.Customer;

import java.util.AbstractMap;

public class CustomerMenu extends Menu {

    private final Customer customer;

    public CustomerMenu(Customer customer) {
        this.customer = customer;
    }

    @Override
    protected void buildMenu() {
        options.put(1, new AbstractMap.SimpleEntry<>("Rent a car", new ListCompaniesForRentingCommand(customer)));
        options.put(2, new AbstractMap.SimpleEntry<>("Return a rented car", new ReturnCarCommand(customer)));
        options.put(3, new AbstractMap.SimpleEntry<>("My rented car", new ShowRentedCarCommand(customer)));
        options.put(0, backToMainMenuCommand);
    }
}
