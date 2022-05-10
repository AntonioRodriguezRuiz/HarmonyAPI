package api.middlewares;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.MEDIA;
import static src.main.java.model.Tables.SERIES;

/**
 * SeriesMiddleware
 * Project HarmonyAPI
 * Created: 2022-05-07
 *
 * @author juagallop1
 **/
public class SeriesMiddlewares {
    public static void isSeries(Integer mediaId) throws SQLException {
        if(mediaId==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MediaId cannot be null");
        }
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (create.select()
                .from(MEDIA)
                .naturalJoin(SERIES)
                .where(MEDIA.MEDIAID.eq(mediaId))
                .fetch()
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Media is not a series");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }

    }

    public static void isNotSeries(Integer mediaId) throws SQLException {
        if(mediaId==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MediaId cannot be null");
        }
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (!create.select()
                    .from(MEDIA)
                    .naturalJoin(SERIES)
                    .where(MEDIA.MEDIAID.eq(mediaId))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Media cannot be a series");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }

    }

}
