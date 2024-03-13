package carsharing.gui.menu.impl.manager;

import carsharing.gui.command.impl.car.InsertCarCommand;
import carsharing.gui.command.impl.car.ListCarsForManagementCommand;
import carsharing.gui.menu.Menu;
import carsharing.model.Company;

import java.util.AbstractMap;

public class CompanyManagementMenu extends Menu {

    private final Company company;

    public CompanyManagementMenu(Company company) {
        this.company = company;
        buildMenu();
    }

    @Override
    protected void buildMenu() {
        if(company != null) {
            options.put(1, new AbstractMap.SimpleEntry<>("Car list", new ListCarsForManagementCommand(company)));
            options.put(2, new AbstractMap.SimpleEntry<>("Create a car", new InsertCarCommand(company)));
        }
        options.put(0, new AbstractMap.SimpleEntry<>("Back", context -> context.backTo(ManagerMenu.class)));
    }
}
