package simplecarsharing.gui.command.impl.car;

import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.dao.CarDao;
import simplecarsharing.dao.impl.DbCarDao;
import simplecarsharing.model.Car;
import simplecarsharing.model.Company;

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
