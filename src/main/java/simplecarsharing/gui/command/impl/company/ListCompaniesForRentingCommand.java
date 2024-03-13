package simplecarsharing.gui.command.impl.company;

import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.gui.menu.impl.customer.ChooseCompanyForRentingMenu;
import simplecarsharing.dao.CompanyDao;
import simplecarsharing.dao.impl.DbCompanyDao;
import simplecarsharing.model.Company;
import simplecarsharing.model.Customer;

import java.util.List;

public class ListCompaniesForRentingCommand implements Command {

    private final Customer customer;

    public ListCompaniesForRentingCommand(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void execute(BaseMenuContext menuContext) {
        if(customer.getRentedCar() != null) {
            menuContext.showMessage("You've already rented a car!");
            menuContext.keep();
        } else {
            CompanyDao companyDao = new DbCompanyDao();
            List<Company> companyList = companyDao.getAllAsList();

            if (companyList.isEmpty()) {
                menuContext.showMessage("The company list is empty!");
                menuContext.keep();
            } else {
                menuContext.navigateTo(new ChooseCompanyForRentingMenu(companyList, customer));
            }
        }
    }
}
