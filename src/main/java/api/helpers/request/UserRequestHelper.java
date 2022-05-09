package api.helpers.request;

import org.jooq.Record;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static src.main.java.model.Tables.USERS;

/**
 * UserRequestHelper
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/
public record UserRequestHelper(String username, String email, String password) {
    public void validate() {
        if (this.username == null || this.password == null || this.email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username, email and password are required");
        }
    }
    public UserRequestHelper(Record oldUser, UserRequestHelper user) {
        this(
            user.username() == null ? oldUser.get(USERS.USERNAME) : user.username(),
            user.email() == null ? oldUser.get(USERS.EMAIL) : user.email(),
            user.password() == null ? oldUser.get(USERS.PASSWORD) : user.password()
        );
    }
}

