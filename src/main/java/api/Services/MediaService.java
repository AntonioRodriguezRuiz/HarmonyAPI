package api.Services;

import api.BodyRequestHelpers.MovieHelper;
import api.GlobalValues;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.HarmonyDatabase.tables.pojos.Media;
import src.main.java.HarmonyDatabase.Routines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static src.main.java.HarmonyDatabase.Tables.*;

@Service
public class MediaService {

    public List<Media> getAllMedia(String search, TableLike type, SortField order, String genre, Integer offset) {
        List<Media> result = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            if (type==null && order==null && genre==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .where(MEDIA.TITLE.contains(search))
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (order==null && genre==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search))
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (type==null && order==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(MEDIAGENRES)
                        .naturalJoin(GENRES)
                        .where(MEDIA.TITLE.contains(search).and(GENRES.NAME.eq(genre)))
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (type==null && genre==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .where(MEDIA.TITLE.contains(search))
                        .orderBy(order)
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (genre==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search))
                        .orderBy(order)
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (type==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(MEDIAGENRES)
                        .naturalJoin(GENRES)
                        .where(MEDIA.TITLE.contains(search).and(GENRES.NAME.eq(genre)))
                        .orderBy(order)
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);

            } else if (order==null){
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(MEDIAGENRES)
                        .naturalJoin(GENRES)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search).and(GENRES.NAME.eq(genre)))
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);
            } else{
                result = create.select(MEDIA.fields())
                        .from(MEDIA)
                        .naturalJoin(MEDIAGENRES)
                        .naturalJoin(GENRES)
                        .naturalJoin(type)
                        .where(MEDIA.TITLE.contains(search).and(GENRES.NAME.eq(genre)))
                        .orderBy(order)
                        .offset(offset)
                        .limit(GlobalValues.PAGE_SIZE)
                        .fetchInto(Media.class);
            }

        } catch (Exception exception){
            exception.printStackTrace();
        }
        return result;
    }

    public MovieHelper postMovie(MovieHelper movie) throws SQLException {
        try(Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            List<Media> newMedia = create.select()
                    .from(MEDIA)
                    .where(MEDIA.TITLE.eq(movie.getTitle())
                                    .and(MEDIA.RELEASEDATE.eq(LocalDate.parse(movie.getReleasedate()))))
                    .fetchInto(Media.class);

            if(!newMedia.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newmovie(create.configuration(),
                    movie.getTitle(),
                    LocalDate.parse(movie.getReleasedate()),
                    movie.getCoverimage(),
                    movie.getBackgroundimage(),
                    movie.getSynopsis());

            Record newMovie = create.select()
                    .from(MEDIA)
                    .naturalJoin(MOVIES)
                    .orderBy(MEDIA.MEDIAID.desc())
                    .limit(1)
                    .fetch().get(0);

            movie.setCoverimage(newMovie.getValue(MEDIA.COVERIMAGE));
            movie.setBackgroundimage(newMovie.getValue(MEDIA.BACKGROUNDIMAGE));
            movie.setMediaid(newMovie.getValue(MEDIA.MEDIAID));
            movie.setMovieid(newMovie.getValue(MOVIES.MOVIEID));

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
        return movie;
    }
}
