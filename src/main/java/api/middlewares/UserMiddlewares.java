package api.middlewares;

import api.GlobalValues;
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
import java.util.List;

import static src.main.java.model.Tables.*;
import static src.main.java.model.Tables.USERS;

public class UserMiddlewares {

    public static void isAdmin(Integer userid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            List<Admins> result = create.select()
                    .from(ADMINS)
                    .where(ADMINS.USERID.eq(userid))
                    .fetchInto(Admins.class);

            if(result.isEmpty()){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
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

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

}
