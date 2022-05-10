package api.middlewares;

import database.DatabaseConnection;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

import static src.main.java.model.Tables.*;

public class VideogamesMiddlewares {

    public static void isVideogame(Integer mediaId) throws SQLException {
        if(mediaId==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MediaId cannot be null");
        }
        try {
            var create = DatabaseConnection.create();
            if (create.select()
                    .from(MEDIA)
                    .naturalJoin(VIDEOGAMES)
                    .where(MEDIA.MEDIAID.eq(mediaId))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Media is not a videogame");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }

    }

    public static void hasPlatform(Integer mediaId, Integer platformid) throws SQLException {
        if(mediaId==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MediaId cannot be null");
        }
        try {
            var create = DatabaseConnection.create();
            if (create.select()
                    .from(MEDIA)
                    .naturalJoin(VIDEOGAMES)
                    .naturalJoin(VIDEOGAMEPLATFORMS)
                    .where(MEDIA.MEDIAID.eq(mediaId)
                            .and(VIDEOGAMEPLATFORMS.PLATFORMID.eq(platformid)))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Videogame does not have this platform");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void doesNotHavePlatform(Integer mediaId, Integer platformid) throws SQLException {
        if(mediaId==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MediaId cannot be null");
        }
        try {
            var create = DatabaseConnection.create();
            if (!create.select()
                    .from(MEDIA)
                    .naturalJoin(VIDEOGAMES)
                    .naturalJoin(VIDEOGAMEPLATFORMS)
                    .where(MEDIA.MEDIAID.eq(mediaId)
                            .and(VIDEOGAMEPLATFORMS.PLATFORMID.eq(platformid)))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Videogame already has this platform");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

}
