package api.controllers;

import api.helpers.enums.TrackerState;
import api.helpers.request.TrackerRequestHelper;
import api.helpers.response.TrackerResponseHelper;
import api.middlewares.MediaMiddlewares;
import api.middlewares.TrackerMiddlewares;
import api.middlewares.UserMiddlewares;
import api.services.MediaService;
import api.services.TrackerService;
import api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/v1/user/{userId}/tracking")
public class TrackerController {

    @Autowired
    private TrackerService trackerService;

    @Operation(summary = "Get all trackers for a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trackers found"),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("{history}")
    public ResponseEntity<List<TrackerResponseHelper>> getTracking( @PathVariable Integer userId,
                                                                    @RequestParam Boolean history,
                                                                    @RequestParam(name = "state", required = false) TrackerState state
                                                                    ) throws SQLException {
        UserMiddlewares.userExists(userId);
        return new ResponseEntity<>(trackerService.getTracking(userId, state, history), HttpStatus.OK);
    }

    @Operation(summary = "Creates a new tracker")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tracker created"),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
        @ApiResponse(responseCode = "409", description = "Tracker already exists", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<TrackerResponseHelper> postTracker(@PathVariable Integer userId, @RequestBody TrackerRequestHelper tracker) throws SQLException {
        UserMiddlewares.userExists(userId);
        MediaMiddlewares.mediaExists(tracker.mediaId());
        TrackerMiddlewares.statusUnchanged(tracker.mediaId(), userId, tracker.state());
        return new ResponseEntity<>(trackerService.postTracker(userId, tracker), HttpStatus.OK);
    }
}
