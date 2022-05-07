package api.middlewares;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.MEDIA;
import static src.main.java.model.Tables.SEASONS;
import static src.main.java.model.tables.Series.SERIES;

/**
 * SeasonMiddlewares
 * Project HarmonyAPI
 * Created: 2022-05-07
 *
 * @author juagallop1
 **/
public class SeasonMiddlewares {
    public static void seasonExists(Integer seasonId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (create.select()
                .from(SERIES)
                .fetch()
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Season does not exist");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
    public static void isSeasonOf(Integer mediaId, Integer seasonid) throws SQLException {
        if(mediaId==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MediaId cannot be null");
        }
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (create.select()
                .from(MEDIA)
                .naturalJoin(SERIES)
                .join(SEASONS)
                .on(SERIES.SERIESID.eq(SEASONS.SERIESID))
                .where(MEDIA.MEDIAID.eq(mediaId))
                .and(SEASONS.SEASONID.eq(seasonid))
                .fetch()
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Season is not of this media");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
