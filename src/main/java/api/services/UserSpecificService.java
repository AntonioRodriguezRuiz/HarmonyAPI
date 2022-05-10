package api.services;

import api.GlobalValues;
import api.helpers.request.UserRequestHelper;
import api.helpers.response.UserResponseHelper;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.USERS;

/**
 * UserSpecificService
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/
@Service
public class UserSpecificService {
    public UserResponseHelper getUser(Integer userId) throws SQLException {
        UserResponseHelper user = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            user = new UserResponseHelper(create.select()
                .from(USERS)
                .where(USERS.USERID.eq(userId))
                .fetch().get(0)
            );
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return user;
    }

    public void putUser(Integer id, UserRequestHelper user) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            var oldUser = create.select()
                .from(USERS)
                .where(USERS.USERID.eq(id))
                .fetch().get(0);

            var newUser = new UserRequestHelper(oldUser, user);

            create.update(USERS)
                .set(USERS.USERNAME, newUser.username())
                .set(USERS.EMAIL, newUser.email())
                .set(USERS.PASSWORD, newUser.password())
                .where(USERS.USERID.eq(id))
                .execute();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(Integer id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            create.deleteFrom(USERS)
                .where(USERS.USERID.eq(id))
                .execute();
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
