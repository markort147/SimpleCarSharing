package simplecarsharing.db;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetHandler {
    void handle(ResultSet rs) throws SQLException;
}
