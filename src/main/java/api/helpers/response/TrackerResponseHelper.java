package api.helpers.response;

import api.helpers.enums.TrackerState;
import org.jooq.Record;
import src.main.java.model.tables.pojos.Media;

import java.time.LocalDateTime;

import static src.main.java.model.Tables.TRACKERS;

/**
 * TrackerResponseHelper
 * Project HarmonyAPI
 * Created: 2022-05-05
 *
 * @author juagallop1
 **/
public record TrackerResponseHelper(Integer id, Integer userId, TrackerState state, Boolean active, LocalDateTime creationDate, Media media) {
    public TrackerResponseHelper(Record tracker, Media media) {
        this(
            tracker.get(TRACKERS.TRACKERID),
            tracker.get(TRACKERS.USERID),
            TrackerState.of(tracker.get(TRACKERS.STATE)),
            tracker.get(TRACKERS.ACTIVE).intValue()!=0,
            tracker.get(TRACKERS.CREATIONDATE),
            media
        );
    }
}
