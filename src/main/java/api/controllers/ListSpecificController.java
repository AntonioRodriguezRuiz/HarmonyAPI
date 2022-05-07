package api.controllers;

import api.helpers.request.ListMediaRequestHelper;
import api.helpers.request.ListRequestHelper;
import api.helpers.response.ListResponseHelper;
import api.middlewares.ListMiddlewares;
import api.middlewares.MediaMiddlewares;
import api.middlewares.UserMiddlewares;
import api.services.ListSpecificService;
import api.services.MediaSpecificService;
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
        @ApiResponse(responseCode = "403", description = "User not authorized to access the list", content = @Content),
        @ApiResponse(responseCode = "404", description = "User or list not found", content = @Content),
    })
    @GetMapping
    public ResponseEntity<ListResponseHelper> getList(@PathVariable Integer userId, @PathVariable Integer listId) throws SQLException {
        UserMiddlewares.userExists(userId);
        ListMiddlewares.listExists(listId);
        ListMiddlewares.isListOwner(userId, listId);
        return new ResponseEntity<>(listSpecificService.getList(listId), HttpStatus.OK);
    }

    @Operation(summary = "Add a media to the list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Media added successfully"),
        @ApiResponse(responseCode = "400", description = "Media already in the list", content = @Content),
        @ApiResponse(responseCode = "403", description = "User not authorized to access the list", content = @Content),
        @ApiResponse(responseCode = "404", description = "User, list or media not found", content = @Content),
    })
    @PostMapping
    public ResponseEntity<ListResponseHelper> addMedia(@PathVariable Integer userId, @PathVariable Integer listId, @RequestBody ListMediaRequestHelper media) throws SQLException {
        UserMiddlewares.userExists(userId);
        ListMiddlewares.listExists(listId);
        MediaMiddlewares.mediaExists(media.mediaId());
        ListMiddlewares.isListOwner(userId, listId);
        ListMiddlewares.isMediaInList(listId, media.mediaId());
        return new ResponseEntity<>(listSpecificService.addMedia(listId, media), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates information on the list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List updated successfully"),
        @ApiResponse(responseCode = "403", description = "User not authorized to access the list", content = @Content),
        @ApiResponse(responseCode = "404", description = "User or list not found", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ListResponseHelper> putList(@PathVariable Integer userId, @PathVariable Integer listId, @RequestBody ListRequestHelper list) throws SQLException {
        UserMiddlewares.userExists(userId);
        ListMiddlewares.listExists(listId);
        ListMiddlewares.isListOwner(userId, listId);
        return new ResponseEntity<>(listSpecificService.putList(listId, list), HttpStatus.OK);
    }

    @Operation(summary = "Deletes the list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List deleted successfully"),
        @ApiResponse(responseCode = "403", description = "User not authorized to access the list", content = @Content),
        @ApiResponse(responseCode = "404", description = "User or list not found", content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<ListResponseHelper> deleteList(@PathVariable Integer userId, @PathVariable Integer listId) throws SQLException {
        UserMiddlewares.userExists(userId);
        ListMiddlewares.listExists(listId);
        ListMiddlewares.isListOwner(userId, listId);
        listSpecificService.deleteList(listId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deletes the media from the list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Media deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Media not found in list", content = @Content),
        @ApiResponse(responseCode = "403", description = "User not authorized to access the list", content = @Content),
        @ApiResponse(responseCode = "404", description = "User or list not found", content = @Content)
    })
    @DeleteMapping("/{mediaId}")
    public ResponseEntity<ListResponseHelper> deleteMedia(@PathVariable Integer userId, @PathVariable Integer listId, @PathVariable Integer mediaId) throws SQLException {
        UserMiddlewares.userExists(userId);
        ListMiddlewares.listExists(listId);
        ListMiddlewares.isListOwner(userId, listId);
        ListMiddlewares.isMediaNotInList(listId, mediaId);
        listSpecificService.deleteMedia(listId, mediaId);
        return getList(userId, listId);
    }
}
