package api.services;

import api.GlobalValues;
import api.helpers.enums.TrackerState;
import api.helpers.request.TrackerRequestHelper;
import api.helpers.response.TrackerResponseHelper;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.MEDIA;
import static src.main.java.model.Tables.TRACKERS;

/**
 * TrackerService
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/
@Service
public class TrackerService {

    public List<TrackerResponseHelper> getTracking(Integer userId, TrackerState state) throws SQLException {
        List<TrackerResponseHelper> trackers = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
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
            if (state != null) {
                trackers = trackers.stream()
                    .filter(t -> t.state() == state)
                    .toList();
            }
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
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            src.main.java.model.Routines.newtracker(
                create.configuration(),
                userId,
                tracker.mediaId(),
                tracker.state().ordinal()
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
                    .into(src.main.java.model.tables.pojos.Media.class)
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
