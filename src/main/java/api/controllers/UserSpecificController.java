package api.controllers;

import api.helpers.enums.TrackerState;
import api.helpers.request.TrackerRequestHelper;
import api.helpers.request.UserRequestHelper;
import api.helpers.response.TrackerResponseHelper;
import api.helpers.response.UserResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.MediaService;
import api.services.UserService;
import api.services.UserSpecificService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.sql.SQLException;
import java.util.List;

/**
 * UserSpecificCOntroller
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/

@RestController
@RequestMapping("/api/v1/user/{id}")
public class UserSpecificController {

    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserSpecificService userSpecificService;

    @Operation(summary = "Gets an user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping
    public ResponseEntity<UserResponseHelper> getUser(@PathVariable Integer id) throws SQLException {
        if (!userService.userExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userSpecificService.getUser(id), HttpStatus.OK);
    }

    @Operation(summary = "Modifies an user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User modified"),
            @ApiResponse(responseCode = "404", description = "User not found")

    })
    @PutMapping
    public ResponseEntity<UserResponseHelper> putUser(@PathVariable Integer id, @RequestBody UserRequestHelper user) throws SQLException {
        if (!userService.userExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserMiddlewares.isAccountOwner(id);
        return new ResponseEntity<>(userSpecificService.putUser(id, user), HttpStatus.OK);
    }

    @GetMapping("/tracking")
    public List<TrackerResponseHelper> getTracking(@PathVariable Integer id) throws SQLException {
        return userSpecificService.getTracking(id);
    }

    @PostMapping("/tracking")
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
