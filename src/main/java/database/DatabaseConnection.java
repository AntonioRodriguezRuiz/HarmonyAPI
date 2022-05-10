package database;


import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.harmonyapi.Application.dotenv;

/**
 * DatabaseConnection
 * Project HarmonyAPI
 * Created: 2022-05-10
 *
 * @author juagallop1
 **/
public class DatabaseConnection {

    public static final String USER = dotenv.get("HARMONY_DB_USER");
    public static final String PASSWORD = dotenv.get("HARMONY_DB_PASSWORD");
    public static final String URL = dotenv.get("HARMONY_DB_URL");
    public static final SQLDialect DIALECT = SQLDialect.MARIADB;
    public static final Settings SETTINGS = new Settings().withExecuteLogging(false);

    public static DSLContext create() throws SQLException {
        try {
            return DSL.using(
                DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
                ),
                SQLDialect.MARIADB,
                SETTINGS
            );
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
