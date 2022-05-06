package api.controllers;

import api.helpers.request.*;
import api.helpers.response.*;
import api.middlewares.UserMiddlewares;
import api.services.GenresSpecificService;
import api.services.MediaSpecificService;
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

@RestController
@RequestMapping("/api/v1/genres/{id}")
public class GenresSpecificController {

    @Autowired
    GenresSpecificService genresSpecificService;

    @Operation(summary = "Deletes a type of genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping
    public ResponseEntity deleteGenre(@PathVariable Integer id, @RequestBody UseridBodyHelper useridBody) throws SQLException {
        UserMiddlewares.isAdmin(useridBody.userid());
        genresSpecificService.deleteGenre(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
