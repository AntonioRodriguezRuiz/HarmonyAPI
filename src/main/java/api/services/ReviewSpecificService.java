package api.services;

import api.GlobalValues;
import api.helpers.request.UseridBodyHelper;
import api.helpers.response.ReviewResponseHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import src.main.java.model.Routines;

import static src.main.java.model.Tables.*;

@Service
public class ReviewSpecificService {

    private Result<Record> existsReview(Integer id) throws SQLException {
        Result<Record> reviewList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            reviewList = create.select()
                    .from(REVIEWS)
                    .where(REVIEWS.MEDIAID.eq(id))
                    .fetch();


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return reviewList;
    }

    private Result<Record> existsLike(Integer id, Integer userid, Integer likeid) throws SQLException {
        Result<Record> likeList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            if(likeid==null){
                likeList = create.select()
                        .from(REVIEWLIKES)
                        .where(REVIEWLIKES.REVIEWID.eq(id)
                                .and(REVIEWLIKES.USERID.eq(userid)))
                        .fetch();
            } else{
                likeList = create.select()
                        .from(REVIEWLIKES)
                        .where(REVIEWLIKES.REVIEWLIKEID.eq(likeid))
                        .fetch();
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return likeList;
    }

    public ReviewResponseHelper getReview(Integer id) throws SQLException {
        ReviewResponseHelper reviewResult = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> reviewList = existsReview(id);

            if(reviewList.isEmpty()){
                throw new ResponseStatusException((HttpStatus.NOT_FOUND));
            }

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

            Result<Record> reviewList = existsReview(id);

            if(reviewList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(REVIEWS)
                    .where(REVIEWS.REVIEWID.eq(id)).execute();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public ReviewResponseHelper postLike(Integer id, UseridBodyHelper user) throws SQLException{
        Record record = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> reviewList = existsReview(id);

            if(reviewList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Result<Record> likeList = existsLike(id, user.userid(), null);

            if (!likeList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.likereview(create.configuration(),
                    user.userid(),
                    id);

            record = create.select()
                    .from(REVIEWS)
                    .naturalJoin(REVIEWLIKES)
                    .orderBy(REVIEWLIKES.REVIEWLIKEID.desc())
                    .fetch().get(0);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return ReviewResponseHelper.of(record);
    }

    public void deleteLike(Integer likeid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            Result<Record> likeList = existsLike(null, null, likeid);

            if (likeList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(REVIEWLIKES)
                    .where(REVIEWLIKES.REVIEWLIKEID.eq(likeid))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
