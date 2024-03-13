package simplecarsharing.gui.command.impl.company;

import simplecarsharing.dao.CompanyDao;
import simplecarsharing.dao.impl.DbCompanyDao;
import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.model.Company;

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
