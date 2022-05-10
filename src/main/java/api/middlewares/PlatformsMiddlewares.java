package api.middlewares;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.PLATFORMS;

public class PlatformsMiddlewares {

    public static void existsPlatform(String name, Integer platformid) throws SQLException {
        Result<Record> platformList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

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

            if(platformList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void doesNotexistsPlatform(String name, Integer platformid) throws SQLException {
        Result<Record> platformList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

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

            if(!platformList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

}
