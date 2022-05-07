package api.services;

import api.GlobalValues;
import api.helpers.request.PlatformRequestHelper;
import api.helpers.response.PlatformResponseHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
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

    public Result<Record> existsPlatform(String name, Integer platformid) throws SQLException {
        Result<Record> platformList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            if(platformid==null){
                platformList = create.select()
                        .from(PLATFORMS)
                        .where(PLATFORMS.PLATFORMNAME.eq(name))
                        .fetch();
            } else {
                platformList = create.select()
                        .from(PLATFORMS)
                        .where(PLATFORMS.PLATFORMID.eq(platformid))
                        .fetch();
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return platformList;
    }
    
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

    public PlatformResponseHelper postPlatform(PlatformRequestHelper platform) throws SQLException {
        PlatformResponseHelper newPlatform = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> platformList = existsPlatform(platform.getName(), null);

            if(!platformList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

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
