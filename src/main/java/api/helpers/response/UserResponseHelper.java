package api.helpers.response;

import java.time.LocalDate;

/**
 * UserResponseHelper
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/
public record UserResponseHelper(String username, String email, String password, LocalDate creationDate) {

}
