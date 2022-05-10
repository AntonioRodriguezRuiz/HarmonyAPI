package api;

import static com.harmonyapi.Application.dotenv;

public class GlobalValues {

    public static final String USER = dotenv.get("HARMONY_DB_USER");
    public static final String PASSWORD = dotenv.get("HARMONY_DB_PASSWORD");
    public static final String URL = dotenv.get("HARMONY_DB_URL");

    public static final Integer PAGE_SIZE = 20;
}
