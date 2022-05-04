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
    public UserResponseHelper postUser(UserRequestHelper user) throws SQLException {
        UserResponseHelper newUser = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            Routines.newuser(
                create.configuration(),
                user.username(),
                user.email(),
                user.password(),
                Byte.valueOf("0")
            );

            var newUserRecord = create.select()
                .from(USERS)
                .orderBy(USERS.USERID.desc())
                .limit(1)
                .fetch().get(0);

            newUser = new UserResponseHelper(
                newUserRecord.get(USERS.USERNAME),
                newUserRecord.get(USERS.EMAIL),
                newUserRecord.get(USERS.PASSWORD),
                newUserRecord.get(USERS.CREATIONDATE)
            );
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }

        return newUser;
    }
}