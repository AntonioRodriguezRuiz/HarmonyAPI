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
                    .fetch(TRACKERS.STATE);
            Integer lastState = stateList.get(stateList.size()-1);
            switch(lastState){
                case 0:
                    if(state.equals(TrackerState.PLANNING))
                        throw new ResponseStatusException(HttpStatus.CONFLICT);
                case 1:
                    if(state.equals(TrackerState.IN_PROGRESS))
                        throw new ResponseStatusException(HttpStatus.CONFLICT);

                case 2:
                    if(state.equals(TrackerState.COMPLETED))
                        throw new ResponseStatusException(HttpStatus.CONFLICT);
                case 3:
                    if(state.equals(TrackerState.DID_NOT_FINISH))
                        throw new ResponseStatusException(HttpStatus.CONFLICT);
                case 4:
                    if(state.equals(TrackerState.ABANDONED))
                        throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }
}
