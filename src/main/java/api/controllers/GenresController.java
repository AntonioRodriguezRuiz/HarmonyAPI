package api.controllers;

import api.helpers.request.GenreRequestHelper;
import api.middlewares.GenresMiddlewares;
import api.middlewares.UserMiddlewares;
import api.services.GenresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Genres;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenresController {
    @Autowired
    GenresService genresService;

    @Operation(summary = "Get all genres and sort by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful")})
    @GetMapping
    public List<Genres> getAllGenres() throws SQLException {
        return genresService.getAllGenres();

    }

    @Operation(summary = "Post a new genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping
    public ResponseEntity postGenre (@RequestBody GenreRequestHelper genre) throws SQLException {
        UserMiddlewares.isAdmin(genre.getUserid());
        GenresMiddlewares.doesNotExistsGenre(genre.getName());
        return new ResponseEntity(genresService.postGenre(genre), HttpStatus.CREATED);
    }

}
