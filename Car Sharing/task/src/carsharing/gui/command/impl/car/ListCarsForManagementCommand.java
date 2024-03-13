package carsharing.gui.command.impl.car;

import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.dao.CarDao;
import carsharing.dao.impl.DbCarDao;
import carsharing.model.Car;
import carsharing.model.Company;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListCarsForManagementCommand implements Command {

    private final Company company;

    public ListCarsForManagementCommand(Company company) {
        this.company = company;
    }

    @Override
    public void execute(BaseMenuContext baseMenuContext) {
        CarDao carDao = new DbCarDao();
        List<Car> cars = carDao.getAllByCompany(this.company);

        if (cars.isEmpty()) {
            baseMenuContext.showMessage("The car list is empty!");
        } else {
            AtomicInteger counter = new AtomicInteger(1);
            cars.forEach(car -> System.out.println(counter.getAndIncrement() + ". " + car.getName()));
        }

        baseMenuContext.keep();
    }

}
