package api.services;

import api.GlobalValues;
import api.helpers.request.TrackerRequestHelper;
import api.helpers.request.UserRequestHelper;
import api.helpers.response.TrackerResponseHelper;
import api.helpers.response.UserResponseHelper;
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
import java.util.List;

import static src.main.java.model.Tables.*;

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
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            user = new UserResponseHelper(create.select()
                .from(USERS)
                .where(USERS.USERID.eq(userId))
                .fetch().get(0)
            );
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return user;
    }

    public UserResponseHelper putUser(Integer id, UserRequestHelper user) throws SQLException {
        UserResponseHelper response = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            var oldUser = create.select()
                .from(USERS)
                .where(USERS.USERID.eq(id))
                .fetch().get(0);

            var newUser = new UserResponseHelper(oldUser, user);

            create.update(USERS)
                .set(USERS.USERNAME, newUser.username())
                .set(USERS.EMAIL, newUser.email())
                .set(USERS.PASSWORD, newUser.password())
                .where(USERS.USERID.eq(id))
                .execute();

            response = new UserResponseHelper(
                create.select()
                    .from(USERS)
                    .where(USERS.USERID.eq(id))
                    .fetch().get(0)
            );

        }
        catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return response;
    }

    public List<TrackerResponseHelper> getTracking(Integer userId) throws SQLException {
        List<TrackerResponseHelper> trackers = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
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

    public TrackerResponseHelper postTracker(Integer userId, TrackerRequestHelper tracker) throws SQLException {
        TrackerResponseHelper response = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            Routines.newtracker(
                create.configuration(),
                userId,
                tracker.mediaId(),
                tracker.state()
            );
            response = new TrackerResponseHelper(
                create.select()
                    .from(TRACKERS)
                    .orderBy(TRACKERS.TRACKERID.desc())
                    .fetch()
                    .get(0),
                create.select()
                    .from(MEDIA)
                    .where(MEDIA.MEDIAID.eq(tracker.mediaId()))
                    .fetch().get(0)
                    .into(Media.class)
            );
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return response;
    }
}
