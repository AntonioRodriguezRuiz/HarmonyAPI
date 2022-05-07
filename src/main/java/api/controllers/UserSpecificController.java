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
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<UserResponseHelper> getUser(@PathVariable Integer userId) throws SQLException {
        UserMiddlewares.userExists(userId);
        return new ResponseEntity<>(userSpecificService.getUser(userId), HttpStatus.OK);
    }

    @Operation(summary = "Modifies an user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User modified"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PutMapping
    public ResponseEntity<UserRequestHelper> putUser(@PathVariable Integer userId, @RequestBody UserRequestHelper user) throws SQLException {
        UserMiddlewares.userExists(userId);
        UserMiddlewares.isAccountOwner(userId);
        userSpecificService.putUser(userId, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deletes an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<UserResponseHelper> deleteUser(@PathVariable Integer userId) throws SQLException {
        UserMiddlewares.userExists(userId);
        UserMiddlewares.isAccountOwnerOrAdmin(userId);
        userSpecificService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
