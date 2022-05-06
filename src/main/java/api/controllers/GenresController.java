package api.controllers;

import api.GlobalValues;
import api.helpers.request.BookRequestHelper;
import api.helpers.request.GenreRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.GenresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.SortField;
import org.jooq.TableLike;
import org.jooq.impl.DSL;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static src.main.java.model.tables.Genres.GENRES;
import static src.main.java.model.tables.Media.MEDIA;
import src.main.java.model.tables.pojos.Genres;

@RestController
@RequestMapping("/api/v1/genres")
public class GenresController {
    @Autowired
    GenresService genresService;

    @Operation(summary = "Get all genres and sort by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful")})
    @GetMapping
    public Result<Record> getAllGenres() throws SQLException {
        return GenresService.getAllGenres();

    }

    @Operation(summary = "post a new genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping("/genres")
    public ResponseEntity postGenre (@RequestBody GenreRequestHelper genre) throws SQLException {
        UserMiddlewares.isAdmin(genre.getUserid());
        if(genre.getName()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(genresService.postGenre(genre), HttpStatus.CREATED);
    }

}
