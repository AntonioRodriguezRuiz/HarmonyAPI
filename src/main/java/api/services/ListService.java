package api.services;

import api.GlobalValues;
import api.helpers.request.ListRequestHelper;
import api.helpers.response.ListResponseHelper;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.main.java.model.Tables.*;

/**
 * ListService
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/
@Service
public class ListService {
    public List<ListResponseHelper> getLists(Integer userId) throws SQLException {
        List<ListResponseHelper> lists = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            lists = create.select()
                .from(LISTS)
                .where(LISTS.USERID.eq(userId))
                .fetch()
                .map(l -> new ListResponseHelper(
                    l,
                    create.select()
                        .from(MEDIA)
                        .join(LISTMEDIA)
                        .on(MEDIA.MEDIAID.eq(LISTMEDIA.MEDIAID))
                        .where(LISTMEDIA.LISTID.eq(l.get(LISTS.LISTID)))
                        .fetchInto(Media.class)
                ));
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return lists;

    }

    public ListResponseHelper postList(Integer userId, ListRequestHelper list) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            Routines.newlist(
                create.configuration(),
                userId,
                list.listName(),
                list.icon()
            );
            return new ListResponseHelper(
                create.select()
                    .from(LISTS)
                    .where(LISTS.USERID.eq(userId))
                    .orderBy(LISTS.LISTID.desc())
                    .limit(1)
                    .fetch()
                    .get(0),
                new ArrayList<>()
            );
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return null;
    }
}
