package api.services;

import api.GlobalValues;
import api.helpers.response.ListResponseHelper;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static src.main.java.model.Tables.*;
import src.main.java.model.tables.pojos.Media;

/**
 * ListSpecificService
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/
@Service
public class ListSpecificService {
    public boolean listExists(Integer listId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            return !create.select()
                .from(LISTS)
                .where(LISTS.LISTID.eq(listId))
                .fetch()
                .isEmpty();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return false;
    }

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
}
