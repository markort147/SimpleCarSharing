package simplecarsharing.gui.menu.impl.customer;

import simplecarsharing.gui.command.impl.car.ListCarsForRentingCommand;
import simplecarsharing.gui.menu.Menu;
import simplecarsharing.model.Company;
import simplecarsharing.model.Customer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChooseCompanyForRentingMenu extends Menu {

    private List<Company> companyList;
    private final Customer customer;

    public ChooseCompanyForRentingMenu(List<Company> companyList, Customer customer) {
        this.companyList = companyList;
        this.customer = customer;
        title = "Choose a company:";
        buildMenu();
    }

    @Override
    protected void buildMenu() {
        AtomicInteger counter = new AtomicInteger(1);
        if (companyList == null) {
            companyList = new ArrayList<>();
        }
        companyList.forEach(company -> options.put(
                counter.getAndIncrement(),
                new AbstractMap.SimpleEntry<>(
                        company.getName(),
                        new ListCarsForRentingCommand(company, customer)
                )));
        options.put(0, backCommand);
    }
}
