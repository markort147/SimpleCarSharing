package simplecarsharing.gui.command.impl.car;

import simplecarsharing.dao.CarDao;
import simplecarsharing.dao.impl.DbCarDao;
import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.model.Car;
import simplecarsharing.model.Company;

import java.util.Scanner;

public class InsertCarCommand implements Command {

    private final Company company;

    public InsertCarCommand(Company company) {
        this.company = company;
    }

    @Override
    public void execute(BaseMenuContext menuContext) {
        System.out.println("Enter the car name:");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        CarDao carDao = new DbCarDao();
        Car newCar = new Car();
        newCar.setName(name);
        newCar.setCompany(this.company);
        boolean success = carDao.insert(newCar);

        if(success) {
            menuContext.showMessage("The car was added!");
        } else {
            menuContext.showMessage("Failed to add the car.");
        }
        menuContext.keep();
    }

}
