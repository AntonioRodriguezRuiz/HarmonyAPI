package api.helpers.response;

import org.jooq.Record;

import java.time.LocalDateTime;

import static src.main.java.model.Tables.REVIEWS;

public record ReviewResponseHelper(Integer reviewid, Integer userid,
                                  Integer mediaid, LocalDateTime creationDate, Double rating,
                                  String review, Integer likes) {
    public static ReviewResponseHelper of(Record record){
        return new ReviewResponseHelper(
                record.getValue(REVIEWS.REVIEWID),
                record.getValue(REVIEWS.USERID),
                record.getValue(REVIEWS.MEDIAID),
                record.getValue(REVIEWS.CREATIONDATE),
                record.getValue(REVIEWS.RATING),
                record.getValue(REVIEWS.REVIEW),
                record.getValue(REVIEWS.LIKES)
                );
    }
}
