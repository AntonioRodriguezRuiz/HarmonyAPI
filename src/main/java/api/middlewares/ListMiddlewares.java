package api.middlewares;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.LISTMEDIA;
import static src.main.java.model.Tables.LISTS;

/**
 * ListMiddlewares
 * Project HarmonyAPI
 * Created: 2022-05-07
 *
 * @author juagallop1
 **/
public class ListMiddlewares {
    public static void listExists(Integer listId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (create.select()
                .from(LISTS)
                .where(LISTS.LISTID.eq(listId))
                .fetch()
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void isMediaInList(Integer listId, Integer mediaId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (create.select()
                .from(LISTS)
                .join(LISTMEDIA)
                .on(LISTS.LISTID.eq(LISTMEDIA.LISTID))
                .where(LISTS.LISTID.eq(listId)
                    .and(LISTMEDIA.MEDIAID.eq(mediaId)))
                .fetch().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Media is not in list");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void isMediaNotInList(Integer listId, Integer mediaId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (create.select()
                .from(LISTS)
                .join(LISTMEDIA)
                .on(LISTS.LISTID.eq(LISTMEDIA.LISTID))
                .where(LISTS.LISTID.eq(listId)
                    .and(LISTMEDIA.MEDIAID.eq(mediaId)))
                .fetch().isNotEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Media is already in list");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static boolean isListOwner(Integer userid, Integer listId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (!create.select()
                .from(LISTS)
                .where(LISTS.LISTID.eq(listId))
                .fetch()
                .get(0)
                .get(LISTS.USERID)
                .equals(userid)) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the owner of this list");
                }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
        return false;
    }
}
