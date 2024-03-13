package carsharing.gui.command.impl.car;

import carsharing.dao.CarDao;
import carsharing.dao.impl.DbCarDao;
import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.model.Car;
import carsharing.model.Company;

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
