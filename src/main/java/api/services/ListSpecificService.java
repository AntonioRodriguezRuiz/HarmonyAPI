package api.services;

import api.GlobalValues;
import api.helpers.request.ListMediaRequestHelper;
import api.helpers.request.ListRequestHelper;
import api.helpers.response.ListResponseHelper;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.*;

/**
 * ListSpecificService
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/
@Service
public class ListSpecificService {
    public ListResponseHelper getList(Integer listId) throws SQLException {
        ListResponseHelper list = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            list = new ListResponseHelper(
                create.select()
                    .from(LISTS)
                    .where(LISTS.LISTID.eq(listId))
                    .fetch()
                    .get(0),
                create.select()
                    .from(MEDIA)
                    .join(LISTMEDIA)
                    .on(MEDIA.MEDIAID.eq(LISTMEDIA.MEDIAID))
                    .where(LISTMEDIA.LISTID.eq(listId))
                    .fetchInto(Media.class)
            );
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return list;
    }

    public ListResponseHelper addMedia(Integer listId, ListMediaRequestHelper media) throws SQLException {
        ListResponseHelper list = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            Routines.newlistmedia(
                create.configuration(),
                listId,
                media.mediaId()
            );
            list = getList(listId);
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return list;
    }

    public void putList(Integer listId, ListRequestHelper list) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            var oldlist = getList(listId);
            var newlist = new ListRequestHelper(oldlist, list);

            create.update(LISTS)
                .set(LISTS.LISTNAME, newlist.listName())
                .set(LISTS.ICON, newlist.icon())
                .where(LISTS.LISTID.eq(listId))
                .execute();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public void deleteList(Integer listId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            create.delete(LISTS)
                .where(LISTS.LISTID.eq(listId))
                .execute();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public void deleteMedia(Integer listId, Integer mediaId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            create.delete(LISTMEDIA)
                .where(LISTMEDIA.LISTID.eq(listId))
                    .and(LISTMEDIA.MEDIAID.eq(mediaId))
                .execute();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
