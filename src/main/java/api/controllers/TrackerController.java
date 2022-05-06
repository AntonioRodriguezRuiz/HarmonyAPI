package api.controllers;

import api.helpers.enums.TrackerState;
import api.helpers.request.TrackerRequestHelper;
import api.helpers.response.TrackerResponseHelper;
import api.services.MediaService;
import api.services.UserService;
import api.services.UserSpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

/**
 * TrackerController
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/

@RestController
@RequestMapping("/api/v1/user/{id}/tracking")
public class TrackerController {

    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserSpecificService userSpecificService;

    @GetMapping
    public List<TrackerResponseHelper> getTracking(@PathVariable Integer id) throws SQLException {
        return userSpecificService.getTracking(id);
    }

    @PostMapping
    public TrackerResponseHelper postTracker(@PathVariable Integer id, @RequestBody TrackerRequestHelper tracker) throws SQLException {
        if (TrackerState.of(tracker.state()) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!mediaService.mediaExists(tracker.mediaId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!userService.userExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userSpecificService.postTracker(id, tracker);
    }
}
