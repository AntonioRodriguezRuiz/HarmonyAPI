package api.controllers;

import api.GlobalValues;
import api.helpers.request.BookRequestHelper;
import api.helpers.request.MovieRequestHelper;
import api.helpers.request.SeriesRequestHelper;
import api.helpers.request.VideogameRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.middlewares.GenresMiddlewares;
import api.middlewares.MediaMiddlewares;
import api.middlewares.UserMiddlewares;
import api.services.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jooq.SortField;
import org.jooq.TableLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.*;
import static src.main.java.model.tables.Media.MEDIA;
import src.main.java.model.Tables;


@RestController
@RequestMapping("/api/v1/media")
public class MediaController {

    @Autowired
    MediaService mediaService;

    @Operation(summary = "Get all media and filter by media type, genre, title and sort by parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful")})
    @GetMapping
    public List<Media> getAllMedia(@RequestParam(name="search", required = false) String searchParam,
                                   @RequestParam(name="type", required = false) String typeParam,
                                   @RequestParam(name="order", required = false) String orderParam,
                                   @RequestParam(name="genre", required = false) String genreParam,
                                   @RequestParam(name="page", required = false) String pageParam)
                                    throws SQLException {

        String search = searchParam!=null ? searchParam : "";

        if(genreParam!=null){
            GenresMiddlewares.existsGenre(genreParam);
        }

        TableLike typeTable = null;
        if(typeParam!=null){
            switch (typeParam){
                case "movie": typeTable = MOVIES; break;
                case "series": typeTable = Tables.SERIES; break;
                case "videogame": typeTable = Tables.VIDEOGAMES; break;
                case "book": typeTable = Tables.BOOKS; break;
                default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        SortField orderField = null;
        if(orderParam!=null){
            switch (orderParam){
                case "-title": orderField = MEDIA.TITLE.desc(); break;
                case "-releaseDate": orderField = MEDIA.RELEASEDATE.desc(); break;
                case "-rating": orderField = MEDIA.AVGRATING.desc(); break;
                case "+title": orderField = MEDIA.TITLE.asc(); break;
                case "+releaseDate": orderField = MEDIA.RELEASEDATE.asc(); break;
                case "+rating": orderField = MEDIA.AVGRATING.asc(); break;
                default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        Integer page = pageParam!=null ? Integer.valueOf(pageParam) : 1;
        Integer offset = page * GlobalValues.PAGE_SIZE - GlobalValues.PAGE_SIZE;

        return mediaService.getAllMedia(search, typeTable, orderField, genreParam, offset);
    }

    @Operation(summary = "Post a new movie to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping("/movies")
    public ResponseEntity<MediaResponseHelper> postMovie(@RequestBody MovieRequestHelper movie) throws SQLException {
        UserMiddlewares.isAdmin(movie.getUserid());
        movie.validate();
        return new ResponseEntity<>(mediaService.postMovie(movie), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a movie in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/movies")
    public ResponseEntity<MovieRequestHelper> putMovie(@RequestBody MovieRequestHelper movie) throws SQLException {
        UserMiddlewares.isAdmin(movie.getUserid());
        Media oldMedia = MediaMiddlewares.mediaExists(movie.getMediaid(), MOVIES);
        mediaService.putMovie(movie, oldMedia);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Operation(summary = "Post a new series to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping("/series")
    public ResponseEntity<MediaResponseHelper> postSeries(@RequestBody SeriesRequestHelper series) throws SQLException {
        UserMiddlewares.isAdmin(series.getUserid());
        series.validate();
        return new ResponseEntity<>(mediaService.postSeries(series), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a series in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/series")
    public ResponseEntity<SeriesRequestHelper> putSeries(@RequestBody SeriesRequestHelper series) throws SQLException {
        UserMiddlewares.isAdmin(series.getUserid());
        Media oldMedia = MediaMiddlewares.mediaExists(series.getMediaid(), SERIES);
        mediaService.putSeries(series, oldMedia);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Post a new book to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping("/books")
    public ResponseEntity<MediaResponseHelper> postBook(@RequestBody BookRequestHelper book) throws SQLException {
        UserMiddlewares.isAdmin(book.getUserid());
        book.validate();
        return new ResponseEntity<>(mediaService.postBook(book), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a book in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/books")
    public ResponseEntity<BookRequestHelper> putBook(@RequestBody BookRequestHelper book) throws SQLException {
        UserMiddlewares.isAdmin(book.getUserid());
        Media oldMedia = MediaMiddlewares.mediaExists(book.getMediaid(), BOOKS);
        mediaService.putBook(book, oldMedia);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Post a new videogame to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping("/videogames")
    public ResponseEntity<MediaResponseHelper> postVideogame(@RequestBody VideogameRequestHelper videogame) throws SQLException {
        UserMiddlewares.isAdmin(videogame.getUserid());
        videogame.validate();
        return new ResponseEntity<>(mediaService.postVideogame(videogame), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a videogame in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/videogames")
    public ResponseEntity<VideogameRequestHelper> putVideogame(@RequestBody VideogameRequestHelper videogame) throws SQLException {
        UserMiddlewares.isAdmin(videogame.getUserid());
        Media oldMedia = MediaMiddlewares.mediaExists(videogame.getMediaid(), VIDEOGAMES);
        mediaService.putVideogame(videogame, oldMedia);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
