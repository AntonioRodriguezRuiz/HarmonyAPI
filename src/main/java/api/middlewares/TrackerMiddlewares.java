package api.middlewares;

import api.GlobalValues;
import api.helpers.enums.TrackerState;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.TRACKERS;

public class TrackerMiddlewares {
    public static void trackerExists(Integer trackerId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (create.select()
                    .from(TRACKERS)
                    .where(TRACKERS.TRACKERID.eq(trackerId))
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

    public static void statusUnchanged(Integer mediaId,Integer userId, TrackerState state) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            List<Integer> stateList = create.select()
                    .from(TRACKERS)
                    .where(TRACKERS.USERID.eq(userId))
                    .and(TRACKERS.MEDIAID.eq(mediaId))
                    .and(TRACKERS.ACTIVE.eq((byte) 1))
                    .and(TRACKERS.STATE.eq(state.ordinal()))
                    .fetch(TRACKERS.STATE);

            if(!stateList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User already has an active tracker with this state for this media");
            }

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }
}
