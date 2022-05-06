package api.controllers;

import api.helpers.enums.TrackerState;
import api.helpers.request.TrackerRequestHelper;
import api.helpers.response.TrackerResponseHelper;
import api.services.MediaService;
import api.services.TrackerService;
import api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private TrackerService trackerService;

    @Operation(summary = "Get all trackers for a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trackers found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping
    public ResponseEntity<List<TrackerResponseHelper>> getTracking(@PathVariable Integer id) throws SQLException {
        if (!userService.userExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trackerService.getTracking(id), HttpStatus.OK);
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
        return trackerService.postTracker(id, tracker);
    }
}
