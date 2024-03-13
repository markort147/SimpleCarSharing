package carsharing.gui.command.impl.company;

import carsharing.dao.CompanyDao;
import carsharing.dao.impl.DbCompanyDao;
import carsharing.gui.BaseMenuContext;
import carsharing.gui.command.Command;
import carsharing.gui.menu.impl.manager.ChooseCompanyForManagementMenu;
import carsharing.model.Company;

import java.util.List;

public class ListCompaniesForManagementCommand implements Command {

    @Override
    public void execute(BaseMenuContext menuContext) {
        CompanyDao companyDao = new DbCompanyDao();
        List<Company> companyList = companyDao.getAllAsList();

        if (companyList.isEmpty()) {
            menuContext.showMessage("The company list is empty!");
            menuContext.keep();
        } else {
            menuContext.navigateTo(new ChooseCompanyForManagementMenu(companyList));
        }
    }
}
