package api.controllers;

import api.helpers.request.ListRequestHelper;
import api.helpers.response.ListResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.ListService;
import api.services.UserService;
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
import java.util.List;

/**
 * ListController
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/

@RestController
@RequestMapping("/api/v1/user/{userId}/lists")
public class ListController {

    @Autowired
    private ListService listService;
    @Autowired
    private UserService userService;

    @Operation(summary = "Get all lists of a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lists found", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ListResponseHelper>> getLists(@PathVariable Integer userId) throws SQLException {
        UserMiddlewares.userExists(userId);
        return new ResponseEntity<>(listService.getLists(userId), HttpStatus.OK);
    }

    @Operation(summary = "Creates a new list for the user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "List created", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ListResponseHelper> postList(@PathVariable Integer userId, @RequestBody ListRequestHelper list) throws SQLException {
        UserMiddlewares.userExists(userId);
        return new ResponseEntity<>(listService.postList(userId, list), HttpStatus.CREATED);
    }
}
