package carsharing.gui.command.impl.company;

import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.gui.menu.impl.customer.ChooseCompanyForRentingMenu;
import carsharing.dao.CompanyDao;
import carsharing.dao.impl.DbCompanyDao;
import carsharing.model.Company;
import carsharing.model.Customer;

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
