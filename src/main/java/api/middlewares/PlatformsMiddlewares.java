package api.middlewares;

import database.DatabaseConnection;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

import static src.main.java.model.Tables.PLATFORMS;

public class PlatformsMiddlewares {

    public static void existsPlatform(String name, Integer platformid) throws SQLException {
        Result<Record> platformList = null;
        try {
            var create = DatabaseConnection.create();

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
        try {
            var create = DatabaseConnection.create();

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
