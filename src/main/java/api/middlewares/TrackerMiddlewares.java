package api.middlewares;

import api.helpers.enums.TrackerState;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * TrackerMiddlewares
 * Project HarmonyAPI
 * Created: 2022-05-07
 *
 * @author juagallop1
 **/
public class TrackerMiddlewares {
    public static void validate(Integer state) {
        if (state != null && TrackerState.of(state) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state");
        }
    }
}
