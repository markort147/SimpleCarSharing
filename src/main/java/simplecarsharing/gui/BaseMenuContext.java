package simplecarsharing.gui;

import simplecarsharing.gui.menu.Menu;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class BaseMenuContext {
    private final Deque<Menu> menuStack = new ArrayDeque<>();
    private boolean isRunning = true;

    public abstract void showMessage(String message);

    public void navigateTo(Menu menu) {
        if (menuStack.isEmpty() || this.getCurrentMenu() != menu) {
            menuStack.push(menu);
        }
        menu.display(this);
    }

    public void goBack() {
        if (menuStack.size() > 1) {
            menuStack.pop();
            keep();
        }
    }

    public void keep() {
        getCurrentMenu().display(this);
    }

    public void reset() {
        Menu firstMenu = menuStack.peekLast();
        menuStack.clear();
        navigateTo(firstMenu);
    }

    public void exitApplication() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    protected Menu getCurrentMenu() {
        return menuStack.peek();
    }

    public void backTo(Class<? extends Menu> menuClass) {
        if (menuStack.size() > 1) {
            Menu menu = menuStack.pop();
            if (menu.getClass().equals(menuClass)) {
                navigateTo(menu);
            } else {
                backTo(menuClass);
            }
        } else {
            reset();
        }
    }

    public abstract void showTitle(String title);
}