package api.middlewares;

import api.GlobalValues;
import api.helpers.request.UserRequestHelper;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Admins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import static src.main.java.model.Tables.ADMINS;
import static src.main.java.model.Tables.USERS;

public class UserMiddlewares {

    public static void userExists(Integer userid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
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
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
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
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
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

    public static void isNotAdmin(Integer userid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (!create.select()
                .from(ADMINS)
                .where(ADMINS.USERID.eq(userid))
                .fetchInto(Admins.class)
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized to delete admin");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void isAccountOwner(Integer userid, Integer requestId) {
        if (!Objects.equals(userid, requestId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized");
        }
    }

    public static void isAccountOwnerOrAdmin(Integer userid, Integer requestId) throws SQLException {
        try {
            isAccountOwner(userid, requestId);
        }
        catch (ResponseStatusException e){
            isAdmin(requestId);
        }
    }
}
