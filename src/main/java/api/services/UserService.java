package api.services;

import api.helpers.request.UserRequestHelper;
import api.helpers.response.UserResponseHelper;
import database.DatabaseConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;

import java.sql.SQLException;

import static src.main.java.model.Tables.USERS;

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
        try {
            var create = DatabaseConnection.create();

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