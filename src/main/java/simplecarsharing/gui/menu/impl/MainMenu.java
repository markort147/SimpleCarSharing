package simplecarsharing.gui.menu.impl;

import simplecarsharing.gui.command.impl.customer.InsertCustomerCommand;
import simplecarsharing.gui.command.impl.customer.ListCustomersCommand;
import simplecarsharing.gui.menu.Menu;
import simplecarsharing.gui.menu.impl.manager.ManagerMenu;

import java.util.AbstractMap;

public class MainMenu extends Menu {

    @Override
    protected void buildMenu() {
        options.put(1, new AbstractMap.SimpleEntry<>("Log in as a manager", context -> context.navigateTo(new ManagerMenu())));
        options.put(2, new AbstractMap.SimpleEntry<>("Log in as a customer", new ListCustomersCommand()));
        options.put(3, new AbstractMap.SimpleEntry<>("Create a customer", new InsertCustomerCommand()));
        options.put(0, exitCommand);
    }
}
