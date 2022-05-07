package api.controllers;

import api.helpers.request.UseridBodyHelper;
import api.middlewares.UserMiddlewares;
import api.services.PlatformsSpecificService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/platforms/{id}")
public class PlatformsSpecificController {

    @Autowired
    PlatformsSpecificService platformsService;

    @Operation(summary = "Deletes platform from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping
    public ResponseEntity deletePlatform(@PathVariable Integer id, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        platformsService.deletePlatform(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
