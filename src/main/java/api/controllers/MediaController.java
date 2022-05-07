package api.controllers;

import api.GlobalValues;
import api.helpers.request.BookRequestHelper;
import api.helpers.request.MovieRequestHelper;
import api.helpers.request.SeriesRequestHelper;
import api.helpers.request.VideogameRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.SortField;
import org.jooq.TableLike;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Genres;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.tables.Genres.GENRES;
import static src.main.java.model.tables.Media.MEDIA;


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
            isValidGenre(genreParam);
        }

        TableLike typeTable = null;
        if(typeParam!=null){
            switch (typeParam){
                case "movie": typeTable = src.main.java.model.Tables.MOVIES; break;
                case "series": typeTable = src.main.java.model.Tables.SERIES; break;
                case "videogame": typeTable = src.main.java.model.Tables.VIDEOGAMES; break;
                case "book": typeTable = src.main.java.model.Tables.BOOKS; break;
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
        if(movie.getTitle()== null || movie.getReleasedate()==null || movie.getSynopsis()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postMovie(movie), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a movie in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/movies")
    public ResponseEntity<MovieRequestHelper> putMovie(@RequestBody MovieRequestHelper movie) throws SQLException {
        UserMiddlewares.isAdmin(movie.getUserid());
        if(movie.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putMovie(movie);
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
        if(series.getTitle()== null || series.getReleasedate()==null || series.getSynopsis()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postSeries(series), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a series in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/series")
    public ResponseEntity<SeriesRequestHelper> putSeries(@RequestBody SeriesRequestHelper series) throws SQLException {
        UserMiddlewares.isAdmin(series.getUserid());
        if(series.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putSeries(series);
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
        if(book.getTitle()== null || book.getReleasedate()==null || book.getSynopsis()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postBook(book), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a book in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/books")
    public ResponseEntity<BookRequestHelper> putBook(@RequestBody BookRequestHelper book) throws SQLException {
        UserMiddlewares.isAdmin(book.getUserid());
        if(book.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putBook(book);
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
        if(videogame.getTitle()== null || videogame.getReleasedate()==null || videogame.getSynopsis()==null || videogame.getCompany()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postVideogame(videogame), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a videogame in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/videogames")
    public ResponseEntity<VideogameRequestHelper> putVideogame(@RequestBody VideogameRequestHelper videogame) throws SQLException {
        UserMiddlewares.isAdmin(videogame.getUserid());
        if(videogame.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putVideogame(videogame);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void isValidGenre(String genreString) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            List<Genres> result = create.select()
                    .from(GENRES)
                    .where(GENRES.NAME.eq(genreString))
                    .fetchInto(Genres.class);

            if(result.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }
}
