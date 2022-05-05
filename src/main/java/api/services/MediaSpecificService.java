package api.services;

import api.GlobalValues;
import api.helpers.enums.RoleType;
import api.helpers.request.*;
import api.helpers.response.*;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Genres;
import src.main.java.model.tables.pojos.Platforms;
import src.main.java.model.tables.pojos.People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.main.java.model.Tables.*;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Seasons;
import src.main.java.model.tables.pojos.Episodes;

@Service
public class MediaSpecificService {
    public Table getType(Integer id) throws SQLException {
        Table type = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

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

            if (!isMovie.isEmpty()) {
                type = MOVIES;
            } else if (!isSeries.isEmpty()) {
                type = SERIES;
            } else if (!isBook.isEmpty()) {
                type = BOOKS;
            } else if (!isVideogame.isEmpty()) {
                type = VIDEOGAMES;
            } else {
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

    public Table getPeopleTable(Table table) {
        Table peopleTable = null;
        switch (table.getName()) {
            case "movies":
                peopleTable = PEOPLEMOVIES;
                break;

            case "books":
                peopleTable = PEOPLEBOOKS;
                break;

            case "videogames":
                peopleTable = PEOPLEVIDEOGAMES;
                break;
        }
        return peopleTable;
    }

    private Result<Record> existsMedia(Integer id) throws SQLException {
        Result<Record> mediaList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            mediaList = create.select()
                    .from(MEDIA)
                    .where(MEDIA.MEDIAID.eq(id))
                    .fetch();


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return mediaList;
    }

    public Result<Record> existsSeason(Integer id, Integer seasonid, Integer seasonNo) throws SQLException {
        Result<Record> seasonList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table type = getType(id);

            if (type != SERIES) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            if (seasonid == null) {
                seasonList = create.select()
                        .from(SEASONS)
                        .naturalJoin(SERIES)
                        .naturalJoin(MEDIA)
                        .where(MEDIA.MEDIAID.eq(id)
                                .and(SEASONS.SEASONNO.eq(seasonNo)))
                        .fetch();
            } else {
                seasonList = create.select()
                        .from(MEDIA)
                        .naturalJoin(SERIES)
                        .naturalJoin(SEASONS)
                        .where(MEDIA.MEDIAID.eq(id)
                                .and(SEASONS.SEASONID.eq(seasonid)))
                        .fetch();
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return seasonList;
    }

    public Result<Record> existsEpisode(Integer id, Integer seasonid, Integer episodeid, Integer episodeNo) throws SQLException {
        Result<Record> episodeList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table type = getType(id);

            if (type != SERIES) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            if (episodeid == null) {
                episodeList = create.select()
                        .from(MEDIA)
                        .naturalJoin(SERIES)
                        .naturalJoin(SEASONS)
                        .naturalJoin(EPISODES)
                        .where(MEDIA.MEDIAID.eq(id)
                                .and(EPISODES.SEASONID.eq(seasonid))
                                .and(EPISODES.EPISODENO.eq(episodeNo)))
                        .fetch();
            } else {
                episodeList = create.select()
                        .from(MEDIA)
                        .naturalJoin(SERIES)
                        .naturalJoin(SEASONS)
                        .naturalJoin(EPISODES)
                        .where(MEDIA.MEDIAID.eq(id)
                                .and(EPISODES.SEASONID.eq(seasonid))
                                .and(EPISODES.EPISODEID.eq(episodeid)))
                        .fetch();
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return episodeList;
    }

    public void existsPlatform(Integer platformid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> platformList = create.select()
                    .from(PLATFORMS)
                    .where(PLATFORMS.PLATFORMID.eq(platformid))
                    .fetch();

            if (platformList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public void existsGenre(Integer genreid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> genreList = create.select()
                    .from(GENRES)
                    .where(GENRES.GENREID.eq(genreid))
                    .fetch();

            if (genreList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    private People existsPerson(Integer personid) throws SQLException {
        People personResult = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            List<People> peopleList = create.select()
                    .from(PEOPLE)
                    .where(PEOPLE.PERSONID.eq(personid))
                    .fetchInto(People.class);

            if (peopleList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            personResult = peopleList.get(0);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return personResult;
    }

    private Result<Record> existsMediaPerson(Integer id, PeopleMediaRequestHelper person, Table peopleTable, Table table) throws SQLException {
        Result<Record> peopleList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            if (peopleTable.getName().equals("peopleEpisodes")) {
                peopleList = create.select()
                        .from(PEOPLE)
                        .naturalJoin(PEOPLEEPISODES)
                        .naturalJoin(EPISODES)
                        .where(EPISODES.EPISODEID.eq(id)
                                .and(PEOPLE.PERSONID.eq(person.getPersonid())
                                .and(PEOPLEEPISODES.ROLE.eq(person.getRole()))
                                .and(PEOPLEEPISODES.ROLETYPE.eq(person.getRoleTypeByte()))))
                        .fetch();
            } else {

                peopleList = create.select()
                        .from(PEOPLE)
                        .naturalJoin(peopleTable)
                        .naturalJoin(table)
                        .naturalJoin(MEDIA)
                        .where(MEDIA.MEDIAID.eq(id)
                                .and(PEOPLE.PERSONID.eq(person.getPersonid()))
                                .and(peopleTable.field("role", String.class).eq(person.getRole())
                                .and(peopleTable.field("roletype", Byte.class).eq(person.getRoleTypeByte()))))
                        .fetch();
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return peopleList;
    }

    private Result<Record> existsReview(Integer id, Integer userid) throws SQLException {
        Result<Record> reviewList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            reviewList = create.select()
                    .from(REVIEWS)
                    .where(REVIEWS.MEDIAID.eq(id)
                            .and(REVIEWS.USERID.eq(userid)))
                    .fetch();


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return reviewList;
    }

    public MediaResponseHelper getMedia(Integer id) throws SQLException {
        MovieResponseHelper movieResult = null;
        SeriesResponseHelper seriesResult = null;
        BookResponseHelper bookResult = null;
        VideogameResponseHelper videogameResult = null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

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


            switch (type.getName()) {
                case "movies":
                    movieResult = new MovieResponseHelper(media, genresList);
                    break;
                case "series":
                    Integer noSeasons = create.select()
                            .from(SEASONS)
                            .naturalJoin(SERIES)
                            .where(SERIES.MEDIAID.eq(id))
                            .fetch().size();
                    List<Seasons> seasons = create.select(SEASONS.fields())
                                                    .from(SEASONS)
                                                    .naturalJoin(SERIES)
                                                    .where(SERIES.MEDIAID.eq(id))
                                                    .fetchInto(Seasons.class);
                    seriesResult = new SeriesResponseHelper(media, genresList, noSeasons, seasons);
                    break;
                case "books":
                    bookResult = new BookResponseHelper(media, genresList);
                    break;
                case "videogames":
                    List<Platforms> platforms = create.select(PLATFORMS.fields())
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

        if (movieResult != null) {
            return movieResult;
        } else if (seriesResult != null) {
            return seriesResult;
        } else if (bookResult != null) {
            return bookResult;
        } else {
            return videogameResult;
        }

    }

    public void deleteMedia(Integer id) {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            create.deleteFrom(MEDIA)
                    .where(MEDIA.MEDIAID.eq(id)).execute();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public SeasonResponseHelper postSeason(Integer id, SeasonRequestHelper season) throws SQLException {
        SeasonResponseHelper result = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> seasonList = existsSeason(id, null, season.getSeasonNo());

            if (!seasonList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newseasonbyid(create.configuration(),
                    id,
                    season.getSeasonNo(),
                    season.getNoEpisodes());

            Record record = create.select()
                    .from(SEASONS)
                    .orderBy(SEASONS.SEASONID.desc())
                    .limit(1)
                    .fetch().get(0);

            result = new SeasonResponseHelper(record, new ArrayList<>());

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return result;
    }

    public void putSeason(Integer id, SeasonRequestHelper season) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table type = getType(id);

            if (type != SERIES) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            List<Seasons> seasonList = create.select()
                    .from(SEASONS)
                    .where(SEASONS.SEASONID.eq(season.getSeasonid()))
                    .fetchInto(Seasons.class);

            if (seasonList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Seasons oldSeason = seasonList.get(0);

            SeasonRequestHelper newSeason = new SeasonRequestHelper(null, null, null, null, null);
            newSeason.setSeasonNo(season.getSeasonNo() == null ? season.getSeasonNo() : oldSeason.getSeasonno());
            newSeason.setNoEpisodes(season.getNoEpisodes() == null ? season.getNoEpisodes() : oldSeason.getNoepisodes());

            create.update(SEASONS)
                    .set(SEASONS.SEASONNO, newSeason.getSeasonNo())
                    .set(SEASONS.NOEPISODES, newSeason.getNoEpisodes())
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public PlatformResponseHelper addPlatform(Integer id, PlatformRequestHelper platform) throws SQLException {
        PlatformResponseHelper newVideogamePlatform = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table type = getType(id);

            if (type != VIDEOGAMES) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            existsPlatform(platform.getPlatformid());

            Result<Record> videogamePlatfomsList = create.select()
                    .from(VIDEOGAMEPLATFORMS)
                    .naturalJoin(VIDEOGAMES)
                    .naturalJoin(MEDIA)
                    .where(MEDIA.MEDIAID.eq(id)
                            .and(VIDEOGAMEPLATFORMS.PLATFORMID.eq(platform.getPlatformid())))
                    .fetch();

            if (!videogamePlatfomsList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newvideogameplatformbyid(create.configuration(),
                    id,
                    platform.getPlatformid());
            String name = create.select(PLATFORMS.PLATFORMNAME)
                    .from(PLATFORMS)
                    .where(PLATFORMS.PLATFORMID.eq(platform.getPlatformid()))
                    .fetchInto(String.class).get(0);
            newVideogamePlatform = new PlatformResponseHelper(platform.getPlatformid(), name);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return newVideogamePlatform;
    }

    public void removePlatform(Integer id, Integer platformid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table type = getType(id);

            if (type != VIDEOGAMES) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            List<Integer> videogameidList = create.select(VIDEOGAMES.VIDEOGAMEID)
                    .from(MEDIA)
                    .naturalJoin(VIDEOGAMES)
                    .naturalJoin(VIDEOGAMEPLATFORMS)
                    .where(MEDIA.MEDIAID.eq(id)
                            .and(VIDEOGAMEPLATFORMS.PLATFORMID.eq(platformid)))
                    .fetchInto(Integer.class);

            if (videogameidList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Integer videogameid = videogameidList.get(0);

            create.deleteFrom(VIDEOGAMEPLATFORMS)
                    .where(VIDEOGAMEPLATFORMS.PLATFORMID.eq(platformid)
                            .and(VIDEOGAMEPLATFORMS.VIDEOGAMEID.eq(videogameid)))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public SeasonResponseHelper getSeason(Integer id, Integer seasonid) throws SQLException {
        SeasonResponseHelper seasonResult = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            Result<Record> seasonList = existsSeason(id, seasonid, null);

            if (seasonList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            List<Episodes> episodesList = create.select(EPISODES.fields())
                    .from(EPISODES)
                    .where(EPISODES.SEASONID.eq(seasonid))
                    .fetchInto(Episodes.class);

            seasonResult = new SeasonResponseHelper(seasonList.get(0), episodesList);
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return seasonResult;
    }

    public EpisodeResponseHelper postEpisode(Integer id, Integer seasonid, EpisodeRequestHelper episode) throws SQLException {
        EpisodeResponseHelper newEpisode = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> seasonList = existsSeason(id, seasonid, null);

            if (seasonList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            // checks the episode does not already exists
            Result<Record> episodesList = existsEpisode(id, seasonid, null, episode.getEpisodeNo());

            if (!episodesList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            // creates episode
            Routines.newepisodebyid(create.configuration(),
                    seasonid,
                    episode.getEpisodeName(),
                    episode.getEpisodeNo());

            Record episodeRecord = create.select()
                    .from(MEDIA)
                    .naturalJoin(SERIES)
                    .naturalJoin(SEASONS)
                    .naturalJoin(EPISODES)
                    .orderBy(EPISODES.EPISODEID.desc())
                    .fetch().get(0);

            newEpisode = new EpisodeResponseHelper(episodeRecord);


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return newEpisode;
    }

    public void putEpisode(Integer id, Integer seasonid, EpisodeRequestHelper episode) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            // checks the episode does not already exists
            Result<Record> episodesList = existsEpisode(id, seasonid, episode.getEpisodeid(), null);

            if (episodesList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            EpisodeResponseHelper oldEpisode = new EpisodeResponseHelper(episodesList.get(0));
            EpisodeRequestHelper newEpisode = new EpisodeRequestHelper(null, null, null, null);

            newEpisode.setEpisodeNo(episode.getEpisodeNo() == null ? oldEpisode.getEpisodeNo() : episode.getEpisodeNo());
            newEpisode.setEpisodeName(episode.getEpisodeName() == null ? oldEpisode.getEpisodeName() : episode.getEpisodeName());

            create.update(EPISODES)
                    .set(EPISODES.EPISODENO, newEpisode.getEpisodeNo())
                    .set(EPISODES.EPISODENAME, newEpisode.getEpisodeName())
                    .where(EPISODES.EPISODEID.eq(episode.getEpisodeid()))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public void deleteSeason(Integer id, Integer seasonid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            // checks the episode does not already exists
            Result<Record> seasonList = existsSeason(id, seasonid, null);

            if (seasonList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(SEASONS)
                    .where(SEASONS.SEASONID.eq(seasonid))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public EpisodeResponseHelper getEpisode(Integer id, Integer seasonid, Integer episodeid) throws SQLException {
        Result<Record> episodesList = existsEpisode(id, seasonid, episodeid, null);

        if (episodesList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        EpisodeResponseHelper episodeResult = new EpisodeResponseHelper(episodesList.get(0));
        return episodeResult;
    }

    public void deleteEpisode(Integer id, Integer seasonid, Integer episodeid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            // checks the episode does not already exists
            Result<Record> episodesList = existsEpisode(id, seasonid, episodeid, null);

            if (episodesList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(EPISODES)
                    .where(EPISODES.EPISODEID.eq(episodeid))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public GenreResponseHelper addGenre(Integer id, GenreRequestHelper genre) throws SQLException {
        GenreResponseHelper newMediaGenre = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            existsGenre(genre.getGenreid());

            Result<Record> mediaGenreList = create.select()
                    .from(MEDIA)
                    .naturalJoin(MEDIAGENRES)
                    .where(MEDIA.MEDIAID.eq(id)
                            .and(MEDIAGENRES.GENREID.eq(genre.getGenreid())))
                    .fetch();

            if (!mediaGenreList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newmediagenrebyid(create.configuration(),
                    id,
                    genre.getGenreid());

            Record record = create.select()
                    .from(GENRES)
                    .where(GENRES.GENREID.eq(genre.getGenreid()))
                    .fetch().get(0);

            newMediaGenre = new GenreResponseHelper(record);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return newMediaGenre;
    }

    public void removeGenre(Integer id, Integer genreid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> mediagenreList = create.select()
                    .from(MEDIAGENRES)
                    .where(MEDIAGENRES.MEDIAID.eq(id)
                            .and(MEDIAGENRES.GENREID.eq(genreid)))
                    .fetch();

            if (mediagenreList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(MEDIAGENRES)
                    .where(MEDIAGENRES.MEDIAID.eq(id)
                            .and(MEDIAGENRES.GENREID.eq(genreid)))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public List<PeopleMediaResponseHelper> getPeopleFromMedia(Integer id) throws SQLException {
        List<PeopleMediaResponseHelper> peopleList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table table = getType(id);

            if (table.getName().equals("series")) {
                Result<Record> result = create.select()
                        .from(MEDIA)
                        .naturalJoin(table)
                        .naturalJoin(SEASONS)
                        .naturalJoin(EPISODES)
                        .naturalJoin(PEOPLEEPISODES)
                        .naturalJoin(PEOPLE)
                        .where(MEDIA.MEDIAID.eq(id))
                        .fetch();

                for (Record record : result) {
                    Integer seasonid = record.get(SEASONS.SEASONID);
                    Integer episodeid = record.get(EPISODES.EPISODEID);
                    peopleList.add(new PeopleEpisodeResponseHelper(record, table, seasonid,  episodeid));
                }

            } else{
                Table peopleTable = getPeopleTable(table);
                Result<Record> result = create.select()
                        .from(MEDIA)
                        .naturalJoin(table)
                        .naturalJoin(peopleTable)
                        .naturalJoin(PEOPLE)
                        .where(MEDIA.MEDIAID.eq(id))
                        .fetch();

                for (Record record : result) {
                    peopleList.add(new PeopleMediaResponseHelper(record, table));
                }
            }





        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return peopleList;
    }

    public PeopleMediaResponseHelper addPerson(Integer id, PeopleMediaRequestHelper person) throws SQLException {
        PeopleMediaResponseHelper personResult = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table table = getType(id);

            if (table.getName().equals("series")) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            } else if (table.getName().equals("book") && person.getRoleTypeByte() != 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            MediaResponseHelper mediaData = getMedia(id);
            People personData = existsPerson(person.getPersonid());
            Result<Record> peopleList = existsMediaPerson(id, person, getPeopleTable(table), table);

            if (!peopleList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            switch (table.getName()) {
                case "movies":
                    Routines.newpersonmovie(create.configuration(),
                            personData.getName(), personData.getBirthdate(),
                            mediaData.getTitle(), mediaData.getReleasedate(),
                            person.getRole(), person.getRoleTypeByte()
                    );
                    break;

                case "books":
                    Routines.newpersonbook(create.configuration(),
                            personData.getName(), personData.getBirthdate(),
                            mediaData.getTitle(), mediaData.getReleasedate(),
                            person.getRole()
                    );
                    break;

                case "videogames":
                    Routines.newpersonvideogame(create.configuration(),
                            personData.getName(), personData.getBirthdate(),
                            mediaData.getTitle(), mediaData.getReleasedate(),
                            person.getRole(), person.getRoleTypeByte()
                    );
                    break;
            }

            personResult = new PeopleMediaResponseHelper(id, person.getPersonid(), personData.getName(),
                    personData.getBirthdate(), person.getRole(), person.getRoleType());

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return personResult;
    }

    public void removePerson(Integer id, PeopleMediaRequestHelper person) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table table = getType(id);

            if (table.getName().equals("series")) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }
            Table peopleTable = getPeopleTable(table);
            Result<Record> peopleList = existsMediaPerson(id, person, peopleTable, table);

            if (peopleList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(peopleTable)
                    .where(peopleTable.field("personid"))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public List<PeopleMediaResponseHelper> getPeopleFromEpisode(Integer id, Integer seasonid, Integer episodeid) throws SQLException {
        List<PeopleMediaResponseHelper> peopleList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Table table = getType(id);

            if (!table.getName().equals("series")) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }
            Result<Record> episodeExists = existsEpisode(id, seasonid, episodeid, null);
            if (episodeExists.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Table peopleTable = PEOPLEEPISODES;

            Result<Record> result = create.select()
                    .from(MEDIA)
                    .naturalJoin(SERIES)
                    .naturalJoin(SEASONS)
                    .naturalJoin(EPISODES)
                    .naturalJoin(peopleTable)
                    .naturalJoin(PEOPLE)
                    .where(EPISODES.EPISODEID.eq(episodeid))
                    .fetch();

            for (Record record : result) {
                peopleList.add(new PeopleEpisodeResponseHelper(record, table, seasonid, episodeid));
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return peopleList;
    }

    public PeopleMediaResponseHelper addPersonEpisode(Integer id, Integer seasonid, Integer episodeid, PeopleEpisodeRequestHelper person) throws SQLException {
        PeopleMediaResponseHelper personResult = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            existsEpisode(id, seasonid, episodeid, null);

            Table table = getType(id);

            if (!table.getName().equals("series")) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            People personData = existsPerson(person.getPersonid());
            Result<Record> peopleList = existsMediaPerson(episodeid, person, PEOPLEEPISODES, table);

            if (!peopleList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newpersonepisodebyid(create.configuration(),
                    person.getPersonid(), episodeid,
                    person.getRole(), person.getRoleTypeByte());

            personResult = new PeopleMediaResponseHelper(id, person.getPersonid(), personData.getName(),
                    personData.getBirthdate(), person.getRole(), person.getRoleType());

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return personResult;
    }

    public void removePersonEpisode(Integer id, Integer seasonid, Integer episodeid, PeopleMediaRequestHelper person) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            existsEpisode(id, seasonid, episodeid, null);

            Table table = getType(id);


            if (!table.getName().equals("series")) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }

            Result<Record> peopleList = existsMediaPerson(episodeid, person, PEOPLEEPISODES, table);

            if (peopleList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(PEOPLEEPISODES)
                    .where(PEOPLEEPISODES.PERSONID.eq(person.getPersonid())
                                .and(PEOPLEEPISODES.EPISODEID.eq(episodeid))
                                .and(PEOPLEEPISODES.ROLE.eq(person.getRole()))
                                .and(PEOPLEEPISODES.ROLETYPE.eq(person.getRoleTypeByte())))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public List<ReviewResponseHelper> getReviews(Integer id) throws SQLException {
        List<ReviewResponseHelper> reviews = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> mediaList = existsMedia(id);

            if(mediaList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Result<Record> result = create.select()
                    .from(REVIEWS)
                    .where(REVIEWS.MEDIAID.eq(id))
                    .fetch();

            for (Record record: result) {
                reviews.add(ReviewResponseHelper.of(record));
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return reviews;
    }


    public ReviewResponseHelper addReview(Integer id, ReviewRequestHelper review) throws SQLException {
        Record record = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> reviewList = existsReview(id, review.userid());

            if (!reviewList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }

            Routines.newreview(create.configuration(),
                    review.userid(),
                    id,
                    review.rating(),
                    review.review());

            record = create.select()
                    .from(REVIEWS)
                    .orderBy(REVIEWS.REVIEWID.desc())
                    .fetch().get(0);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return ReviewResponseHelper.of(record);
    }

    public void putReview(Integer id, ReviewRequestHelper review) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Record record = create.select()
                    .from(REVIEWS)
                    .orderBy(REVIEWS.REVIEWID.desc())
                    .fetch().get(0);

            ReviewResponseHelper oldReview = ReviewResponseHelper.of(record);

            create.update(REVIEWS)
                    .set(REVIEWS.RATING, review.rating()==null ? oldReview.rating() : review.rating())
                    .set(REVIEWS.REVIEW, review.review()==null ? oldReview.review() : review.review())
                    .where(REVIEWS.REVIEWID.eq(review.reviewid()))
                    .execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}


