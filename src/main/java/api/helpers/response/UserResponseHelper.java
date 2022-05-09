package api.helpers.response;

import org.jooq.Record;

import java.time.LocalDate;

import static src.main.java.model.Tables.USERS;

/**
 * UserResponseHelper
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/
public record UserResponseHelper(String username, String email, LocalDate creationDate) {
    public UserResponseHelper(Record user) {
        this(
            user.get(USERS.USERNAME),
            user.get(USERS.EMAIL),
            user.get(USERS.CREATIONDATE)
        );
    }
}
