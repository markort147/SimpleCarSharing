package carsharing.db;

import carsharing.Configs;

import java.sql.*;

public class DbClient {

    private static final String DB_TYPE = "jdbc:h2:";
    private static final String DB_PATH = "./src/carsharing/db/";

    private final String url;

    private static DbClient instance;

    private DbClient() {
        String databaseName = Configs.getInstance().getDatabaseName();
        this.url = DB_TYPE + DB_PATH + databaseName;
    }

    public static DbClient getInstance() {
        if (instance == null) {
            instance = new DbClient();
        }
        return instance;
    }

    public void eraseDatabaseSchema() {
        execute("""
                DROP TABLE IF EXISTS CUSTOMER;
                DROP TABLE IF EXISTS CAR;
                DROP TABLE IF EXISTS COMPANY;
                """);
    }

    public void initDatabaseSchema() {
        execute("""
                CREATE TABLE IF NOT EXISTS COMPANY (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    NAME VARCHAR(50) UNIQUE NOT NULL
                );
                CREATE TABLE IF NOT EXISTS CAR (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    NAME VARCHAR(50) UNIQUE NOT NULL,
                    COMPANY_ID INT NOT NULL,
                    CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)
                );
                CREATE TABLE IF NOT EXISTS CUSTOMER (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    NAME VARCHAR(50) UNIQUE NOT NULL,
                    RENTED_CAR_ID INT,
                    CONSTRAINT fk_car FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID)
                );
                """);
    }

    public int executeUpdate(String sql) {
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()) {
            conn.setAutoCommit(true);
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean execute(String sql) {
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()) {
            conn.setAutoCommit(true);
            return st.execute(sql);
        } catch (SQLException e) {
//            System.out.println("ERROR - " + e);
            return false;
        }
    }

    public int executeAsInt(String sql) {
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()) {
            conn.setAutoCommit(true);
            try(ResultSet resultSet = st.executeQuery(sql)) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
//            System.out.println("ERROR - " + e);
            return 0;
        }
    }

    public boolean executeQueryAndHandle(String sql, ResultSetHandler handler) {
        try (Connection conn = DriverManager.getConnection(url);
             Statement st = conn.createStatement()) {
            conn.setAutoCommit(true);
            try (ResultSet rs = st.executeQuery(sql)) {
                handler.handle(rs);
            }
            return true;
        } catch (SQLException e) {
//            System.out.println("ERROR - " + e);
            return false;
        }
    }

    public int executeSingleInsert(String sql) {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(true);
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated key.");
                }
            }
        } catch (SQLException e) {
//            System.out.println("ERROR - " + e);
            return -1;
        }
    }

}
