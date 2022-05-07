package api.helpers.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
}

