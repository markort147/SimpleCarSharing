package carsharing.gui.menu.impl.customer;

import carsharing.gui.command.impl.car.RentCarCommand;
import carsharing.gui.menu.Menu;
import carsharing.gui.menu.impl.customer.CustomerMenu;
import carsharing.model.Car;
import carsharing.model.Customer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChooseCarForRentingMenu extends Menu {

    private List<Car> cars;
    private final Customer customer;
    public ChooseCarForRentingMenu(List<Car> cars, Customer customer) {
        this.cars = cars;
        this.customer = customer;
        title = "Choose a car:";
    }

    @Override
    protected void buildMenu() {
        AtomicInteger counter = new AtomicInteger(1);
        if (cars == null) {
            cars = new ArrayList<>();
        }
        cars.forEach(car -> options.put(
                counter.getAndIncrement(),
                new AbstractMap.SimpleEntry<>(
                        car.getName(),
                        new RentCarCommand(car, customer)
                )));
        options.put(0, new AbstractMap.SimpleEntry<>("back", context -> context.backTo(CustomerMenu.class)));

    }
}
