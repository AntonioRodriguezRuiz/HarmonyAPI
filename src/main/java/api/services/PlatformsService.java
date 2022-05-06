package api.services;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static src.main.java.model.Tables.PLATFORMS;
import src.main.java.model.tables.pojos.Platforms;


@Service
public class PlatformsService {

    public List<Platforms> getAllPlatforms() {
        List<Platforms> platformsList = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            platformsList = create.select(PLATFORMS.fields())
                    .from(PLATFORMS)
                    .fetchInto(Platforms.class);

        } catch (Exception exception){
            exception.printStackTrace();
        }
        return platformsList;
    }
}
