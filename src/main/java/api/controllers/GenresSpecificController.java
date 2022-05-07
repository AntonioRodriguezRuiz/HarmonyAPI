package api.controllers;

import api.helpers.request.UseridBodyHelper;
import api.middlewares.GenresMiddlewares;
import api.middlewares.UserMiddlewares;
import api.services.GenresSpecificService;
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
@RequestMapping("/api/v1/genres/{id}")
public class GenresSpecificController {

    @Autowired
    GenresSpecificService genresSpecificService;

    @Operation(summary = "Deletes a of genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping
    public ResponseEntity deleteGenre(@PathVariable Integer id, @RequestBody UseridBodyHelper useridBody) throws SQLException {
        UserMiddlewares.isAdmin(useridBody.userid());
        GenresMiddlewares.doesNotExistsGenre(id);
        genresSpecificService.deleteGenre(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
