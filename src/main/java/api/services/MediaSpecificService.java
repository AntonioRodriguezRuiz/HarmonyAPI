package api.services;

import api.GlobalValues;
import api.helpers.request.SeasonRequestHelper;
import api.helpers.response.*;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Genres;
import src.main.java.model.tables.pojos.Platforms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.*;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Seasons;
@Service
public class MediaSpecificService {
    public MediaResponseHelper getMedia(Integer id) throws SQLException {
        MovieResponseHelper movieResult = null;
        SeriesResponseHelper seriesResult = null;
        BookResponseHelper bookResult = null;
        VideogameResponseHelper videogameResult = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            Table type = getType(id);

            Record media = create.select()
                    .from(MEDIA)
                    .naturalJoin(type)
                    .where(MEDIA.MEDIAID.eq(id))
                    .fetch().get(0);

            List<Genres> genresList = create.select(GENRES.fields())
                    .from(GENRES)
                    .naturalJoin(MEDIAGENRES)
                    .where(MEDIAGENRES.MEDIAID.eq(id))
                    .fetchInto(Genres.class);


            switch (type.getName()){
                case "movies": movieResult = new MovieResponseHelper(media, genresList); break;
                case "series": Integer noSeasons = create.select()
                                                                .from(SEASONS)
                                                                .naturalJoin(SERIES)
                                                                .where(SERIES.MEDIAID.eq(id))
                                                                .fetch().size();
                                seriesResult = new SeriesResponseHelper(media, genresList, noSeasons);
                                break;
                case "books": bookResult = new BookResponseHelper(media, genresList); break;
                case "videogames": List<Platforms> platforms = create.select(PLATFORMS.fields())
                                                                    .from(PLATFORMS)
                                                                    .naturalJoin(VIDEOGAMEPLATFORMS)
                                                                    .naturalJoin(VIDEOGAMES)
                                                                    .where(VIDEOGAMES.MEDIAID.eq(id))
                                                                    .fetchInto(Platforms.class);
                                    videogameResult = new VideogameResponseHelper(media, genresList, platforms);
                                    break;
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }

        if(movieResult!=null){
            return movieResult;
        } else if (seriesResult!=null) {
            return seriesResult;
        } else if (bookResult!=null) {
            return bookResult;
        } else{
            return videogameResult;
        }

    }


    public void deleteMedia(Integer id) {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            create.deleteFrom(MEDIA)
                    .where(MEDIA.MEDIAID.eq(id)).execute();

        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public Table getType(Integer id) throws SQLException {
        Table type = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            List<Record> isMovie = create.select()
                    .from(MEDIA)
                    .naturalJoin(MOVIES)
                    .where(MEDIA.MEDIAID.eq(id))
                    .fetch();

            List<Record> isSeries = create.select()
                    .from(MEDIA)
                    .naturalJoin(SERIES)
                    .where(MEDIA.MEDIAID.eq(id))
                    .fetch();

            List<Record> isBook = create.select()
                    .from(MEDIA)
                    .naturalJoin(BOOKS)
                    .where(MEDIA.MEDIAID.eq(id))
                    .fetch();

            List<Record> isVideogame = create.select()
                    .from(MEDIA)
                    .naturalJoin(VIDEOGAMES)
                    .where(MEDIA.MEDIAID.eq(id))
                    .fetch();

            if(!isMovie.isEmpty()){
                type = MOVIES;
            } else if(!isSeries.isEmpty()){
                type = SERIES;
            } else if(!isBook.isEmpty()){
                type = BOOKS;
            } else if(!isVideogame.isEmpty()){
                type = VIDEOGAMES;
            } else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return type;
    }

    public SeasonResponseHelper postSeason(SeasonRequestHelper season) throws SQLException {
        SeasonResponseHelper result = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            Table type = getType(season.getMediaid());

            if(type!=SERIES){
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            Result<Record> seasonList = create.select()
                            .from(SEASONS)
                    .naturalJoin(MEDIA)
                                    .where(MEDIA.MEDIAID.eq(season.getMediaid())
                                            .and(SEASONS.SEASONNO.eq(season.getSeasonNo())))
                    .fetch();

            if(!seasonList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newseasonbyid(create.configuration(),
                                    season.getMediaid(),
                                    season.getSeasonNo(),
                                    season.getNoEpisodes());

            Record record = create.select()
                    .from(SEASONS)
                    .orderBy(SEASONS.SEASONID.desc())
                    .limit(1)
                    .fetch().get(0);

            result = new SeasonResponseHelper(record);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return result;
    }

    public void putSeason(SeasonRequestHelper season) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            Table type = getType(season.getMediaid());

            if(type!=SERIES){
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            List<Seasons> seasonList = create.select()
                    .from(SEASONS)
                    .where(SEASONS.SEASONID.eq(season.getSeasonid()))
                    .fetchInto(Seasons.class);

            if(seasonList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Seasons oldSeason = seasonList.get(0);

            SeasonRequestHelper newSeason = new SeasonRequestHelper(null, null, null, null, null);
            newSeason.setSeasonNo(season.getSeasonNo()==null ? season.getSeasonNo() : oldSeason.getSeasonno());
            newSeason.setNoEpisodes(season.getNoEpisodes()==null ? season.getNoEpisodes() : oldSeason.getNoepisodes());

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
