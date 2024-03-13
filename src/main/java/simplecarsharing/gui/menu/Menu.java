package simplecarsharing.gui.menu;

import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.command.Command;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Menu {

    protected final Map<Integer, Map.Entry<String, Command>> options = new LinkedHashMap<>();
    private boolean isBuilt = false;
    protected final Map.Entry<String, Command> backCommand = new AbstractMap.SimpleEntry<>("Back", BaseMenuContext::goBack);
    protected final Map.Entry<String, Command> backToMainMenuCommand = new AbstractMap.SimpleEntry<>("Back", BaseMenuContext::reset);
    protected final Map.Entry<String, Command> exitCommand = new AbstractMap.SimpleEntry<>("Exit", BaseMenuContext::exitApplication);

    protected String title;

    protected abstract void buildMenu();

    public void display(BaseMenuContext menuContext) {
        if (!isBuilt) {
            buildMenu();
            isBuilt = true;
        }

        if (title != null) menuContext.showTitle(title);

        while (menuContext.isRunning()) {
            System.out.println();
            options.forEach((digit, command) -> menuContext.showMessage(digit + ". " + command.getKey()));

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (options.containsKey(choice)) {
                    options.get(choice).getValue().execute(menuContext);
                } else {
                    menuContext.showMessage("Invalid option, try again.");
                }
            } else {
                menuContext.showMessage("Invalid option, try again.");
            }

            scanner.nextLine();
        }
    }
}