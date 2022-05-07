package api.controllers;

import api.helpers.request.ListMediaRequestHelper;
import api.helpers.request.ListRequestHelper;
import api.helpers.response.ListResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.ListSpecificService;
import api.services.MediaSpecificService;
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

    @Autowired
    private MediaSpecificService mediaSpecificService;

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
        if (!listSpecificService.listExists(listId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserMiddlewares.isListOwner(userId, listId);
        return new ResponseEntity<>(listSpecificService.getList(listId), HttpStatus.OK);
    }

    @Operation(summary = "Add a media to the list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Media added successfully"),
        @ApiResponse(responseCode = "400", description = "Media already in the list"),
        @ApiResponse(responseCode = "403", description = "User not authorized to access the list"),
        @ApiResponse(responseCode = "404", description = "User, list or media not found"),
    })
    @PostMapping
    public ResponseEntity<ListResponseHelper> addMedia(@PathVariable Integer userId, @PathVariable Integer listId, @RequestBody ListMediaRequestHelper media) throws SQLException {
        if (!userService.userExists(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!listSpecificService.listExists(listId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found");
        }
        if (mediaSpecificService.existsMedia(media.mediaId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found");
        }
        if (listSpecificService.isMediaInList(listId, media.mediaId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Media already in list");
        }
        UserMiddlewares.isListOwner(userId, listId);
        return new ResponseEntity<>(listSpecificService.addMedia(listId, media), HttpStatus.OK);
    }

    @Operation(summary = "Updates information on the list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List updated successfully"),
        @ApiResponse(responseCode = "403", description = "User not authorized to access the list"),
        @ApiResponse(responseCode = "404", description = "User or list not found")
    })
    @PutMapping
    public ResponseEntity<ListResponseHelper> putList(@PathVariable Integer userId, @PathVariable Integer listId, @RequestBody ListRequestHelper list) throws SQLException {
        if (!userService.userExists(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!listSpecificService.listExists(listId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found");
        }
        UserMiddlewares.isListOwner(userId, listId);
        return new ResponseEntity<>(listSpecificService.putList(listId, list), HttpStatus.OK);
    }
}
