package api;

import org.jooq.SQLDialect;
import org.jooq.conf.Settings;

import static com.harmonyapi.Application.dotenv;

public class GlobalValues {

    public static final String USER = dotenv.get("HARMONY_DB_USER");
    public static final String PASSWORD = dotenv.get("HARMONY_DB_PASSWORD");
    public static final String URL = dotenv.get("HARMONY_DB_URL");
    public static final SQLDialect DIALECT = SQLDialect.MARIADB;
    public static final Settings SETTINGS = new Settings().withExecuteLogging(false);

    public static final Integer PAGE_SIZE = 20;
}
