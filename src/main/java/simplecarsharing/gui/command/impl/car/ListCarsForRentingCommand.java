package simplecarsharing.gui.command.impl.car;

import simplecarsharing.dao.CarDao;
import simplecarsharing.dao.impl.DbCarDao;
import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.gui.menu.impl.customer.ChooseCarForRentingMenu;
import simplecarsharing.model.Car;
import simplecarsharing.model.Company;
import simplecarsharing.model.Customer;

import java.util.List;

public class ListCarsForRentingCommand implements Command {

    private final Company company;
    private final Customer customer;

    public ListCarsForRentingCommand(Company company, Customer customer) {
        this.company = company;
        this.customer = customer;
    }

    @Override
    public void execute(BaseMenuContext baseMenuContext) {
        CarDao carDao = new DbCarDao();
        List<Car> cars = carDao.getAvailableCarsByCompany(company);

        if (cars.isEmpty()) {
            baseMenuContext.showMessage(String.format("No available cars in the '%s' company", company.getName()));
        } else {
            baseMenuContext.navigateTo(new ChooseCarForRentingMenu(cars, customer));
        }

        baseMenuContext.keep();
    }

}
