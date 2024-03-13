package carsharing.gui.command;

import carsharing.gui.BaseMenuContext;

@FunctionalInterface
public interface Command {
    void execute(BaseMenuContext baseMenuContext);
}
