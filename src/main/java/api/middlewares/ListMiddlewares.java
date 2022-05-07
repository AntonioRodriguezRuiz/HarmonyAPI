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

import static src.main.java.model.Tables.*;

/**
 * ListMiddlewares
 * Project HarmonyAPI
 * Created: 2022-05-07
 *
 * @author juagallop1
 **/
public class ListMiddlewares {
    public static void isMediaInList(Integer listId, Integer mediaId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (create.select()
                .from(LISTS)
                .join(LISTMEDIA)
                .on(LISTS.LISTID.eq(LISTMEDIA.LISTID))
                .where(LISTS.LISTID.eq(listId)
                    .and(LISTMEDIA.MEDIAID.eq(mediaId)))
                .fetch().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Media is not in list");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }
}
