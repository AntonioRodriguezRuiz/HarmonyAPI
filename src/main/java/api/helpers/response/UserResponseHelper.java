package api.helpers.response;

import java.time.LocalDate;

import static src.main.java.model.Tables.USERS;

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
}
