package carsharing.gui.menu.impl.manager;

import carsharing.gui.command.impl.company.InsertCompanyCommand;
import carsharing.gui.command.impl.company.ListCompaniesForManagementCommand;
import carsharing.gui.menu.Menu;

import java.util.AbstractMap;

public class ManagerMenu extends Menu {
    @Override
    protected void buildMenu() {
        options.put(1, new AbstractMap.SimpleEntry<>("Company list", new ListCompaniesForManagementCommand()));
        options.put(2, new AbstractMap.SimpleEntry<>("Create a company", new InsertCompanyCommand()));
        options.put(0, backCommand);
    }
}
