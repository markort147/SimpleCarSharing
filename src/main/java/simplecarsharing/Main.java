package simplecarsharing;

import simplecarsharing.gui.BaseMenuContext;
import simplecarsharing.gui.CliMenuContext;
import simplecarsharing.gui.menu.impl.MainMenu;
import simplecarsharing.db.DbClient;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Map<String, String> cliArgs = convertArgsToMap(args);
        Configs.init(cliArgs);

        Class.forName ("org.h2.Driver");
        DbClient dbClient = DbClient.getInstance();
//        dbClient.eraseDatabaseSchema();
        dbClient.initDatabaseSchema();

        BaseMenuContext menuContext = new CliMenuContext();
        menuContext.navigateTo(new MainMenu());
    }

    private static Map<String, String> convertArgsToMap(String[] args) {
        return IntStream.range(0, args.length)
                .filter(i -> args[i].startsWith("-"))
                .boxed()
                .collect(Collectors.toMap(
                        i -> args[i].substring(1),
                        i -> (i == args.length - 1 || args[i + 1].startsWith("-")) ? "" : args[i + 1],
                        (oldValue, newValue) -> newValue,
                        HashMap::new
                ));
    }
}