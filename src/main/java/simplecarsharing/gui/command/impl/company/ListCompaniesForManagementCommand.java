package simplecarsharing.gui.command.impl.company;

import simplecarsharing.dao.CompanyDao;
import simplecarsharing.dao.impl.DbCompanyDao;
import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;
import simplecarsharing.gui.menu.impl.manager.ChooseCompanyForManagementMenu;
import simplecarsharing.model.Company;

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
