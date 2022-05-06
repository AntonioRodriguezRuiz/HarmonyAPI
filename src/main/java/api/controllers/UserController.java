package api.controllers;

import api.helpers.request.UserRequestHelper;
import api.helpers.response.UserResponseHelper;
import api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.sql.SQLException;

/**
 * UserController
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserResponseHelper> postUser(@RequestBody UserRequestHelper user) throws SQLException {
        if (user.username() == null || user.password() == null || user.email() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (userService.userExists(user)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.postUser(user), HttpStatus.CREATED);
    }
}
