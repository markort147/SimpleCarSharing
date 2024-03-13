package simplecarsharing.gui.menu.impl.manager;

import simplecarsharing.gui.command.impl.company.InsertCompanyCommand;
import simplecarsharing.gui.command.impl.company.ListCompaniesForManagementCommand;
import simplecarsharing.gui.menu.Menu;

import java.util.AbstractMap;

public class ManagerMenu extends Menu {
    @Override
    protected void buildMenu() {
        options.put(1, new AbstractMap.SimpleEntry<>("Company list", new ListCompaniesForManagementCommand()));
        options.put(2, new AbstractMap.SimpleEntry<>("Create a company", new InsertCompanyCommand()));
        options.put(0, backCommand);
    }
}
