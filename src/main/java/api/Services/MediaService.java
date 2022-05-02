package api.Services;

import api.BodyRequestHelpers.MovieHelper;
import api.BodyRequestHelpers.SeriesHelper;
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

            List<Media> oldMovie = create.select()
                    .from(MEDIA)
                    .where(MEDIA.TITLE.eq(movie.getTitle())
                            .and(MEDIA.RELEASEDATE.eq(movie.getReleasedate())))
                    .fetchInto(Media.class);

            if(!oldMovie.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newmovie(create.configuration(),
                        movie.getTitle(),
                        movie.getReleasedate(),
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

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
        return movie;
    }

    public void putMovie(MovieHelper movie) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            List<Media> oldMovieList = create.select()
                    .from(MEDIA)
                    .naturalJoin(MOVIES)
                    .where(MEDIA.MEDIAID.eq(movie.getMediaid()))
                    .fetchInto(Media.class);

            if (oldMovieList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Media oldMovie = oldMovieList.get(0);
            MovieHelper newMovie = new MovieHelper();
            newMovie.setMediaid(movie.getMediaid());

            newMovie.setTitle(movie.getTitle() != null ? movie.getTitle() : oldMovie.getTitle());
            newMovie.setReleasedate(movie.getReleasedate() != null ? movie.getReleasedate() : oldMovie.getReleasedate());
            newMovie.setCoverimage(movie.getCoverimage() != null ? movie.getCoverimage() : oldMovie.getCoverimage());
            newMovie.setBackgroundimage(movie.getBackgroundimage() != null ? movie.getBackgroundimage() : oldMovie.getBackgroundimage());
            newMovie.setSynopsis(movie.getSynopsis() != null ? movie.getSynopsis() : oldMovie.getSynopsis());

            create.update(MEDIA)
                    .set(MEDIA.TITLE, newMovie.getTitle())
                    .set(MEDIA.RELEASEDATE, newMovie.getReleasedate())
                    .set(MEDIA.COVERIMAGE, newMovie.getCoverimage())
                    .set(MEDIA.BACKGROUNDIMAGE, newMovie.getBackgroundimage())
                    .set(MEDIA.SYNOPSIS, newMovie.getSynopsis())
                    .where(MEDIA.MEDIAID.eq(newMovie.getMediaid()))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public SeriesHelper postSeries(SeriesHelper series) throws SQLException {
        try(Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            List<Media> oldSeries = create.select()
                    .from(MEDIA)
                    .where(MEDIA.TITLE.eq(series.getTitle())
                            .and(MEDIA.RELEASEDATE.eq(series.getReleasedate())))
                    .fetchInto(Media.class);

            if(!oldSeries.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newseries(create.configuration(),
                    series.getTitle(),
                    series.getReleasedate(),
                    series.getCoverimage(),
                    series.getBackgroundimage(),
                    series.getSynopsis());

            Record newSeries = create.select()
                    .from(MEDIA)
                    .naturalJoin(SERIES)
                    .orderBy(MEDIA.MEDIAID.desc())
                    .limit(1)
                    .fetch().get(0);

            series.setCoverimage(newSeries.getValue(MEDIA.COVERIMAGE));
            series.setBackgroundimage(newSeries.getValue(MEDIA.BACKGROUNDIMAGE));
            series.setMediaid(newSeries.getValue(MEDIA.MEDIAID));

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
        return series;
    }

    public void putSeries(SeriesHelper series) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            List<Media> oldSeriesList = create.select()
                    .from(MEDIA)
                    .naturalJoin(SERIES)
                    .where(MEDIA.MEDIAID.eq(series.getMediaid()))
                    .fetchInto(Media.class);

            if (oldSeriesList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Media oldSeries = oldSeriesList.get(0);
            SeriesHelper newSeries = new SeriesHelper();
            newSeries.setMediaid(series.getMediaid());

            newSeries.setTitle(series.getTitle() != null ? series.getTitle() : oldSeries.getTitle());
            newSeries.setReleasedate(series.getReleasedate() != null ? series.getReleasedate() : oldSeries.getReleasedate());
            newSeries.setCoverimage(series.getCoverimage() != null ? series.getCoverimage() : oldSeries.getCoverimage());
            newSeries.setBackgroundimage(series.getBackgroundimage() != null ? series.getBackgroundimage() : oldSeries.getBackgroundimage());
            newSeries.setSynopsis(series.getSynopsis() != null ? series.getSynopsis() : oldSeries.getSynopsis());

            create.update(MEDIA)
                    .set(MEDIA.TITLE, newSeries.getTitle())
                    .set(MEDIA.RELEASEDATE, newSeries.getReleasedate())
                    .set(MEDIA.COVERIMAGE, newSeries.getCoverimage())
                    .set(MEDIA.BACKGROUNDIMAGE, newSeries.getBackgroundimage())
                    .set(MEDIA.SYNOPSIS, newSeries.getSynopsis())
                    .where(MEDIA.MEDIAID.eq(newSeries.getMediaid()))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
