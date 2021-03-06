package api.services;

import api.GlobalValues;
import api.helpers.request.PlatformRequestHelper;
import api.helpers.response.PlatformResponseHelper;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Platforms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.PLATFORMS;


@Service
public class PlatformsService {
    
    public List<Platforms> getAllPlatforms() {
        List<Platforms> platformsList = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

            platformsList = create.select(PLATFORMS.fields())
                    .from(PLATFORMS)
                    .fetchInto(Platforms.class);

        } catch (Exception exception){
            exception.printStackTrace();
        }
        return platformsList;
    }

    public PlatformResponseHelper postPlatform(PlatformRequestHelper platform) throws SQLException {
        PlatformResponseHelper newPlatform = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

            Routines.newplatform(create.configuration(),
                                platform.getName());

            Platforms result = create.select()
                    .from(PLATFORMS)
                    .orderBy(PLATFORMS.PLATFORMID.desc())
                    .fetchInto(Platforms.class).get(0);

            newPlatform = new PlatformResponseHelper(result.getPlatformid(), result.getPlatformname());

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return newPlatform;
    }
}
