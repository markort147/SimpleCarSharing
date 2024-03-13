package simplecarsharing.gui.command;

import simplecarsharing.gui.BaseMenuContext;

@FunctionalInterface
public interface Command {
    void execute(BaseMenuContext baseMenuContext);
}
