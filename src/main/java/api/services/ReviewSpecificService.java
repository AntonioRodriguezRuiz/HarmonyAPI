package api.services;

import api.GlobalValues;
import api.helpers.request.ReviewRequestHelper;
import api.helpers.response.ReviewResponseHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.MEDIA;
import static src.main.java.model.Tables.REVIEWS;

@Service
public class ReviewSpecificService {

    public ReviewResponseHelper getReview(Integer id) throws SQLException {
        ReviewResponseHelper reviewResult = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Record review = create.select()
                    .from(REVIEWS)
                    .where(REVIEWS.REVIEWID.eq(id))
                    .fetch().get(0);

            reviewResult = ReviewResponseHelper.of(review);
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }

        return reviewResult;
    }

    public void deleteReview(Integer id) {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            create.deleteFrom(REVIEWS)
                    .where(REVIEWS.REVIEWID.eq(id)).execute();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
