package simplecarsharing;

import java.util.Map;

public class Configs {
    private static final String DB_DEFAULT_NAME = "carsharing";
    private static String databaseName;
    private static Configs instance;

    private Configs() {
    }

    public static void init(Map<String, String> cliArgs) {
        if (databaseName == null) {
            databaseName = cliArgs.getOrDefault("databaseFileName", DB_DEFAULT_NAME);
        }
    }

    public static Configs getInstance() {
        if (instance == null) {
            instance = new Configs();
        }
        return instance;
    }

    public String getDatabaseName() {
        return databaseName;
    }
}
