package carsharing.gui.command.impl.car;

import carsharing.dao.CarDao;
import carsharing.dao.impl.DbCarDao;
import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.gui.menu.impl.customer.ChooseCarForRentingMenu;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;

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
