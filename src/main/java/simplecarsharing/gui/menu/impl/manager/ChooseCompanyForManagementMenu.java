package simplecarsharing.gui.menu.impl.manager;

import simplecarsharing.gui.menu.Menu;
import simplecarsharing.model.Company;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChooseCompanyForManagementMenu extends Menu {

    private List<Company> companyList;

    public ChooseCompanyForManagementMenu(List<Company> companyList) {
        this.companyList = companyList;
        title = "Choose a company:";
        buildMenu();
    }

    @Override
    protected void buildMenu() {
        AtomicInteger counter = new AtomicInteger(1);
        if (companyList == null) {
            companyList = new ArrayList<>();
        }
        companyList.forEach(company -> options.put(
                counter.getAndIncrement(),
                new AbstractMap.SimpleEntry<>(
                        company.getName(),
                        context -> context.navigateTo(new CompanyManagementMenu(company))
                )));
        options.put(0, backCommand);
    }
}
