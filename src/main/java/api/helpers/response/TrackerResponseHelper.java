package api.helpers.response;

import api.helpers.enums.TrackerState;
import org.jooq.Record;

import static src.main.java.model.Tables.TRACKERS;
import src.main.java.model.tables.pojos.Media;

import java.time.LocalDateTime;

/**
 * TrackerResponseHelper
 * Project HarmonyAPI
 * Created: 2022-05-05
 *
 * @author juagallop1
 **/
public record TrackerResponseHelper(Integer id, Media media, Integer userId, TrackerState state, LocalDateTime creationDate) {
    public TrackerResponseHelper(Record tracker, Media media) {
        this(
            tracker.get(TRACKERS.TRACKERID),
            media,
            tracker.get(TRACKERS.USERID),
            TrackerState.of(tracker.get(TRACKERS.STATE)),
            tracker.get(TRACKERS.CREATIONDATE)
        );
    }
}
