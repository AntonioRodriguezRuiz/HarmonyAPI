package api.Controllers;

import api.BodyRequestHelpers.MovieHelper;
import api.BodyRequestHelpers.SeriesHelper;
import api.GlobalValues;
import api.Services.MediaService;
import api.Middlewares.UserMiddlewares;
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
import src.main.java.HarmonyDatabase.Tables;
import src.main.java.HarmonyDatabase.tables.pojos.Genres;
import src.main.java.HarmonyDatabase.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static src.main.java.HarmonyDatabase.Tables.GENRES;
import static src.main.java.HarmonyDatabase.Tables.MEDIA;


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
                case "movie": typeTable = Tables.MOVIES; break;
                case "series": typeTable = Tables.SERIES; break;
                case "videogame": typeTable = Tables.VIDEOGAMES; break;
                case "book": typeTable = Tables.BOOKS; break;
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
    public ResponseEntity<MovieHelper> postMovie(@RequestBody MovieHelper movie) throws SQLException {
        UserMiddlewares.isAdmin(movie.getUserid());
        if(movie.getTitle()== null || movie.getReleasedate()==null || movie.getSynopsis()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<MovieHelper>(mediaService.postMovie(movie), HttpStatus.CREATED);
    }

    @PutMapping("/movies")
    public ResponseEntity<MovieHelper> putMovie(@RequestBody MovieHelper movie) throws SQLException {
        UserMiddlewares.isAdmin(movie.getUserid());
        if(movie.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putMovie(movie);
        return new ResponseEntity<MovieHelper>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/series")
    public ResponseEntity<SeriesHelper> postSeries(@RequestBody SeriesHelper series) throws SQLException {
        UserMiddlewares.isAdmin(series.getUserid());
        if(series.getTitle()== null || series.getReleasedate()==null || series.getSynopsis()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<SeriesHelper>(mediaService.postSeries(series), HttpStatus.CREATED);
    }

    @PutMapping("/series")
    public ResponseEntity<SeriesHelper> putSeries(@RequestBody SeriesHelper series) throws SQLException {
        UserMiddlewares.isAdmin(series.getUserid());
        if(series.getMediaid()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        mediaService.putSeries(series);
        return new ResponseEntity<SeriesHelper>(HttpStatus.NO_CONTENT);
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
