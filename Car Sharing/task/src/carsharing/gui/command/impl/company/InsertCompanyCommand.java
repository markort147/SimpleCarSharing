package carsharing.gui.command.impl.company;

import carsharing.dao.CompanyDao;
import carsharing.dao.impl.DbCompanyDao;
import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.model.Company;

import java.util.Scanner;

public class InsertCompanyCommand implements Command {

    @Override
    public void execute(BaseMenuContext menuContext) {
        menuContext.showMessage("Enter the company name:");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        CompanyDao companyDao = new DbCompanyDao();
        Company newCompany = new Company();
        newCompany.setName(name);
        boolean success = companyDao.insert(newCompany);

        if(success) {
            menuContext.showMessage("The company was created!");
        } else {
            menuContext.showMessage("Failed to create the company.");
        }
        menuContext.keep();
    }

}
