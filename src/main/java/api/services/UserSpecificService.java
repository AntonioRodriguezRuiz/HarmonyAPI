package api.services;

import api.GlobalValues;
import api.helpers.response.TrackerResponseHelper;
import api.helpers.response.UserResponseHelper;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.*;
import src.main.java.model.tables.pojos.Media;

/**
 * UserSpecificService
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/
@Service
public class UserSpecificService {
    public UserResponseHelper getUser(Integer userId) throws SQLException {
        UserResponseHelper user = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            user = new UserResponseHelper(create.select()
                .from(USERS)
                .where(USERS.USERID.eq(userId))
                .fetch().get(0));
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return user;
    }

    public List<TrackerResponseHelper> getTracking(Integer userId) throws SQLException {
        List<TrackerResponseHelper> trackers = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            trackers = create.select()
                .from(TRACKERS)
                .where(TRACKERS.USERID.eq(userId))
                .fetch()
                .stream()
                .map(t -> new TrackerResponseHelper(
                    t,
                    create.select()
                        .from(MEDIA)
                        .where(MEDIA.MEDIAID.eq(t.get(TRACKERS.MEDIAID)))
                        .fetch().get(0)
                        .into(Media.class)
                ))
                .toList();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return trackers;
    }
}
