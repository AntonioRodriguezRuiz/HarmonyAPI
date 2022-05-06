package api.controllers;

import api.helpers.response.ListResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.ListSpecificService;
import api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

/**
 * ListSpecificController
 * Project HarmonyAPI
 * Created: 2022-05-06
 *
 * @author juagallop1
 **/

@RestController
@RequestMapping("/api/v1/user/{userId}/lists/{listId}")
public class ListSpecificController {

    @Autowired
    private ListSpecificService listSpecificService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get a specific list", description = "Get a specific list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List fetched successfully"),
        @ApiResponse(responseCode = "403", description = "User not authorized to access the list"),
        @ApiResponse(responseCode = "404", description = "User or list not found"),
    })
    @GetMapping
    public ResponseEntity<ListResponseHelper> getList(@PathVariable Integer userId, @PathVariable Integer listId) throws SQLException {
        if (!userService.userExists(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!listSpecificService.listExists(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserMiddlewares.isListOwner(userId, listId);
        return new ResponseEntity<>(listSpecificService.getList(listId), HttpStatus.OK);
    }
}
