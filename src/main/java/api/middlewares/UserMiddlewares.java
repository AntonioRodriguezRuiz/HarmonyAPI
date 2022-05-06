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

    public static boolean isListOwner(Integer userid, Integer listid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (!create.select()
                .from(LISTS)
                .where(LISTS.LISTID.eq(listid))
                .fetch()
                .get(0)
                .get(LISTS.USERID)
                .equals(userid)) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
        return false;
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
