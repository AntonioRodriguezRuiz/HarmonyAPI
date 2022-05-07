package api.helpers.response;

import java.time.LocalDate;

import static src.main.java.model.Tables.USERS;

import api.helpers.request.UserRequestHelper;
import org.jooq.Record;

/**
 * UserResponseHelper
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/
public record UserResponseHelper(String username, String email, String password, LocalDate creationDate) {
    public UserResponseHelper(Record user) {
        this(
            user.get(USERS.USERNAME),
            user.get(USERS.EMAIL),
            user.get(USERS.PASSWORD),
            user.get(USERS.CREATIONDATE)
        );
    }

    public UserResponseHelper(Record oldUser, UserRequestHelper user) {
        this(
            user.username() == null ? oldUser.get(USERS.USERNAME) : user.username(),
            user.email() == null ? oldUser.get(USERS.EMAIL) : user.email(),
            user.password() == null ? oldUser.get(USERS.PASSWORD) : user.password(),
            oldUser.get(USERS.CREATIONDATE)
        );
    }
}
