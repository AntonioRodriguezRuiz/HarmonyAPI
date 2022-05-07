package api.middlewares;

import api.GlobalValues;
import api.helpers.request.UserRequestHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Admins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.*;

public class UserMiddlewares {

    public static void userExists(Integer userid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (create.select()
                .from(USERS)
                .where(USERS.USERID.eq(userid))
                .fetch()
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void userExists(UserRequestHelper user) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (create.select()
                .from(USERS)
                .where(USERS.USERNAME.eq(user.username()))
                .fetch()
                .isNotEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken");
            } else if (create.select()
                .from(USERS)
                .where(USERS.EMAIL.eq(user.email()))
                .fetch()
                .isNotEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email address already in use");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void isAdmin(Integer userid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (create.select()
                .from(ADMINS)
                .where(ADMINS.USERID.eq(userid))
                .fetchInto(Admins.class)
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void isOwnerOfReview(Integer userid, Integer reviewid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> review = create.select()
                                        .from(REVIEWS)
                                        .where(REVIEWS.REVIEWID.eq(reviewid))
                                        .fetch();

            if(review.isEmpty()){
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Result<Record> reviewUser = create.select()
                    .from(REVIEWS)
                    .where(REVIEWS.REVIEWID.eq(reviewid)
                            .and(REVIEWS.USERID.eq(userid)))
                    .fetch();

            if(reviewUser.isEmpty()){
                throw  new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }


    public static void isOwnerOfLike(Integer userid, Integer reviewid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> review = create.select()
                    .from(REVIEWLIKES)
                    .where(REVIEWLIKES.REVIEWID.eq(reviewid))
                    .fetch();

            if(review.isEmpty()){
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Result<Record> reviewUser = create.select()
                    .from(REVIEWLIKES)
                    .where(REVIEWLIKES.REVIEWID.eq(reviewid)
                            .and(REVIEWLIKES.USERID.eq(userid)))
                    .fetch();

            if(reviewUser.isEmpty()){
                throw  new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
  }

    public static void existsUser(Integer userid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> userList = create.select()
                    .from(USERS)
                    .where(USERS.USERID.eq(userid))
                    .fetch();

            if(userList.isEmpty()){
                throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

    public static void isAccountOwner(Integer userid) throws SQLException {
        // TODO: implement
    }


    public static void isAccountOwnerOrAdmin(Integer id) throws SQLException {
        try {
            isAccountOwner(id);
        }
        catch (ResponseStatusException e){
            isAdmin(id);
        }
    }
}
