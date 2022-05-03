package api.controllers;

import api.GlobalValues;
import api.helpers.request.BookRequestHelper;
import api.helpers.request.MovieRequestHelper;
import api.helpers.request.SeriesRequestHelper;
import api.helpers.request.VideogameRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.MediaService;
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
@RequestMapping("/api/v1/media")
public class MediaController {

    @Autowired
    MediaService mediaService;

    @GetMapping
    public List<Media> getAllMedia(@RequestParam Map<String, String> allRequestParams) throws SQLException {
        String search = allRequestParams.containsKey("search") ? allRequestParams.get("search") : "";

        String genreString = allRequestParams.containsKey("genre") ? allRequestParams.get("genre"): null;
        if(genreString!=null){
            isValidGenre(genreString);
        }

        String typeString = allRequestParams.containsKey("type") ? allRequestParams.get("type") : null;
        TableLike typeTable = null;
        if(typeString!=null){
            switch (typeString){
                case "movie": typeTable = src.main.java.model.Tables.MOVIES; break;
                case "series": typeTable = src.main.java.model.Tables.SERIES; break;
                case "videogame": typeTable = src.main.java.model.Tables.VIDEOGAMES; break;
                case "book": typeTable = src.main.java.model.Tables.BOOKS; break;
                default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        String orderString =  allRequestParams.containsKey("order") ? allRequestParams.get("order") : null;
        SortField orderField = null;
        if(orderString!=null){
            switch (orderString){
                case "title": orderField = MEDIA.TITLE.asc(); break;
                case "releaseDate": orderField = MEDIA.RELEASEDATE.desc(); break;
                case "rating": orderField = MEDIA.AVGRATING.desc(); break;
                default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        Integer page = allRequestParams.containsKey("page") ? Integer.valueOf(allRequestParams.get("page")) : 0;
        Integer offset = page * GlobalValues.PAGE_SIZE;

        return mediaService.getAllMedia(search, typeTable, orderField, genreString, offset);
    }

    @PostMapping("/movies")
    public ResponseEntity<MediaResponseHelper> postMovie(@RequestBody MovieRequestHelper movie) throws SQLException {
        UserMiddlewares.isAdmin(movie.getUserid());
        if(movie.getTitle()== null || movie.getReleasedate()==null || movie.getSynopsis()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postMovie(movie), HttpStatus.CREATED);
    }

    @PutMapping("/movies")
    public ResponseEntity<MovieRequestHelper> putMovie(@RequestBody MovieRequestHelper movie) throws SQLException {
        UserMiddlewares.isAdmin(movie.getUserid());
        if(movie.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putMovie(movie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/series")
    public ResponseEntity<MediaResponseHelper> postSeries(@RequestBody SeriesRequestHelper series) throws SQLException {
        UserMiddlewares.isAdmin(series.getUserid());
        if(series.getTitle()== null || series.getReleasedate()==null || series.getSynopsis()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postSeries(series), HttpStatus.CREATED);
    }

    @PutMapping("/series")
    public ResponseEntity<SeriesRequestHelper> putSeries(@RequestBody SeriesRequestHelper series) throws SQLException {
        UserMiddlewares.isAdmin(series.getUserid());
        if(series.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putSeries(series);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/books")
    public ResponseEntity<MediaResponseHelper> postBook(@RequestBody BookRequestHelper book) throws SQLException {
        UserMiddlewares.isAdmin(book.getUserid());
        if(book.getTitle()== null || book.getReleasedate()==null || book.getSynopsis()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/books")
    public ResponseEntity<BookRequestHelper> putBook(@RequestBody BookRequestHelper book) throws SQLException {
        UserMiddlewares.isAdmin(book.getUserid());
        if(book.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putBook(book);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/videogames")
    public ResponseEntity<MediaResponseHelper> postVideogame(@RequestBody VideogameRequestHelper videogame) throws SQLException {
        UserMiddlewares.isAdmin(videogame.getUserid());
        if(videogame.getTitle()== null || videogame.getReleasedate()==null || videogame.getSynopsis()==null || videogame.getCompany()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mediaService.postVideogame(videogame), HttpStatus.CREATED);
    }

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
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
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
