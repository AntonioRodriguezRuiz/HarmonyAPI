package api.middlewares;

import database.DatabaseConnection;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

import static src.main.java.model.Tables.REVIEWLIKES;
import static src.main.java.model.Tables.REVIEWS;

public class ReviewMiddlewares{
    public static void isOwnerOfReview(Integer userid, Integer reviewid) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            Result<Record> reviewUser = create.select()
                    .from(REVIEWS)
                    .where(REVIEWS.REVIEWID.eq(reviewid)
                            .and(REVIEWS.USERID.eq(userid)))
                    .fetch();

            if(reviewUser.isEmpty()){
                throw  new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the owner of the review can perform operations over it");
            }

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void isOwnerOfLike(Integer userid, Integer reviewid) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            Result<Record> reviewUser = create.select()
                    .from(REVIEWLIKES)
                    .where(REVIEWLIKES.REVIEWID.eq(reviewid)
                            .and(REVIEWLIKES.USERID.eq(userid)))
                    .fetch();

            if(reviewUser.isEmpty()){
                throw  new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the owner of the like can delete it");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void existsReview(Integer id) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            Result<Record> reviewList = create.select()
                    .from(REVIEWS)
                    .where(REVIEWS.REVIEWID.eq(id))
                    .fetch();

            if(reviewList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review does not exist");
            }


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static Result<Record> existsLike(Integer id, Integer userid, Integer likeid) throws SQLException {
        Result<Record> likeList = null;
        try {
            var create = DatabaseConnection.create();

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
}