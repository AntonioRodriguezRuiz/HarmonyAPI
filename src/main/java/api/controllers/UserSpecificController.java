package api.controllers;

import api.helpers.request.UserRequestHelper;
import api.helpers.response.UserResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.UserService;
import api.services.UserSpecificService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

/**
 * UserSpecificCOntroller
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/

@RestController
@RequestMapping("/api/v1/user/{userId}")
public class UserSpecificController {

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
    public ResponseEntity<UserResponseHelper> getUser(@PathVariable Integer userId) throws SQLException {
        if (!userService.userExists(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userSpecificService.getUser(userId), HttpStatus.OK);
    }

    @Operation(summary = "Modifies an user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User modified", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PutMapping
    public ResponseEntity<UserResponseHelper> putUser(@PathVariable Integer userId, @RequestBody UserRequestHelper user) throws SQLException {
        if (!userService.userExists(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserMiddlewares.isAccountOwner(userId);
        return new ResponseEntity<>(userSpecificService.putUser(userId, user), HttpStatus.OK);
    }

    @Operation(summary = "Deletes an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<UserResponseHelper> deleteUser(@PathVariable Integer userId) throws SQLException {
        if (!userService.userExists(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserMiddlewares.isAccountOwnerOrAdmin(userId);
        userSpecificService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
