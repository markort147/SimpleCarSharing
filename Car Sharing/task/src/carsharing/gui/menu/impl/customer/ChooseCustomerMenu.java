package carsharing.gui.menu.impl.customer;

import carsharing.gui.menu.Menu;
import carsharing.model.Customer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChooseCustomerMenu extends Menu {

    private List<Customer> customers;

    public ChooseCustomerMenu(List<Customer> customers) {
        this.customers = customers;
        title = "Choose a customer:";
        buildMenu();
    }

    @Override
    protected void buildMenu() {
        AtomicInteger counter = new AtomicInteger(1);
        if (customers == null) {
            customers = new ArrayList<>();
        }
        customers.forEach(customer -> options.put(
                counter.getAndIncrement(),
                new AbstractMap.SimpleEntry<>(
                        customer.getName(),
                        context -> context.navigateTo(new CustomerMenu(customer))
                )));
        options.put(0, backCommand);
    }
}
