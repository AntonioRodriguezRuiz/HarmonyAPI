package api.services;

import api.GlobalValues;
import api.helpers.request.UserRequestHelper;
import api.helpers.response.UserResponseHelper;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.*;

/**
 * UserService
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/
@Service
public class UserService {
    public boolean userExists(UserRequestHelper user) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            return !create.select()
                .from(USERS)
                .where(USERS.USERNAME.eq(user.username()))
                .or(USERS.EMAIL.eq(user.email()))
                .fetch()
                .isEmpty();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return false;
    }

    public boolean userExists(Integer userId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            return !create.select()
                .from(USERS)
                .where(USERS.USERID.eq(userId))
                .fetch()
                .isEmpty();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return false;
    }

    public UserResponseHelper postUser(UserRequestHelper user) throws SQLException {
        UserResponseHelper newUser = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Routines.newuser(
                create.configuration(),
                user.username(),
                user.email(),
                user.password(),
                Byte.valueOf("0")
            );

            newUser = new UserResponseHelper(create.select()
                .from(USERS)
                .orderBy(USERS.USERID.desc())
                .fetch().get(0));
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }

        return newUser;
    }
}