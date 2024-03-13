package carsharing.gui;

public class CliMenuContext extends BaseMenuContext {

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showTitle(String title) {
        showMessage(title);
    }
}