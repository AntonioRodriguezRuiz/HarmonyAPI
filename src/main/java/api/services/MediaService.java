package api.services;

import api.GlobalValues;
import api.helpers.request.*;
import api.helpers.response.MediaResponseHelper;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.*;
import src.main.java.model.Routines;

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

    public MediaResponseHelper postMedia(MediaRequestHelper media, Table table) throws SQLException {
        MediaResponseHelper newMedia = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            MovieRequestHelper movie = null;
            SeriesRequestHelper series = null;
            BookRequestHelper book = null;
            VideogameRequestHelper videogame = null;

            String aux = table.getName();
            switch (table.getName()) {
                case "movies":
                    movie = (MovieRequestHelper) media;
                    Routines.newmovie(create.configuration(),
                            movie.getTitle(),
                            movie.getReleasedate(),
                            movie.getCoverimage(),
                            movie.getBackgroundimage(),
                            movie.getSynopsis());
                    break;
                case "series":
                    series = (SeriesRequestHelper) media;
                    Routines.newseries(create.configuration(),
                            series.getTitle(),
                            series.getReleasedate(),
                            series.getCoverimage(),
                            series.getBackgroundimage(),
                            series.getSynopsis());
                    break;
                case "books":
                    book = (BookRequestHelper) media;
                    Routines.newbook(create.configuration(),
                            book.getTitle(),
                            book.getReleasedate(),
                            book.getCoverimage(),
                            book.getBackgroundimage(),
                            book.getSynopsis(),
                            book.getCollection());
                    break;
                case "videogames":
                    videogame = (VideogameRequestHelper) media;
                    Routines.newvideogame(create.configuration(),
                            videogame.getTitle(),
                            videogame.getReleasedate(),
                            videogame.getCoverimage(),
                            videogame.getBackgroundimage(),
                            videogame.getSynopsis(),
                            videogame.getCompany());
                    break;
            }

            Integer newMediaid = create.select()
                                .from(MEDIA)
                                .naturalJoin(table)
                                .orderBy(MEDIA.MEDIAID.desc())
                                .limit(1)
                                .fetch().get(0).get(MEDIA.MEDIAID);

            MediaSpecificService mediaSpecificService = new MediaSpecificService();
            newMedia = mediaSpecificService.getMedia(newMediaid);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return newMedia;
    }

    public void putMedia(MediaRequestHelper media, Table table, Media oldMedia) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            MediaRequestHelper newMedia = new MediaRequestHelper(null, null, null, null, null, null, null);
            newMedia.setMediaid(media.getMediaid());

            newMedia.setTitle(media.getTitle() != null ? media.getTitle() : oldMedia.getTitle());
            newMedia.setReleasedate(media.getReleasedate() != null ? media.getReleasedate() : oldMedia.getReleasedate());
            newMedia.setCoverimage(media.getCoverimage() != null ? media.getCoverimage() : oldMedia.getCoverimage());
            newMedia.setBackgroundimage(media.getBackgroundimage() != null ? media.getBackgroundimage() : oldMedia.getBackgroundimage());
            newMedia.setSynopsis(media.getSynopsis() != null ? media.getSynopsis() : oldMedia.getSynopsis());

            create.update(MEDIA)
                    .set(MEDIA.TITLE, newMedia.getTitle())
                    .set(MEDIA.RELEASEDATE, newMedia.getReleasedate())
                    .set(MEDIA.COVERIMAGE, newMedia.getCoverimage())
                    .set(MEDIA.BACKGROUNDIMAGE, newMedia.getBackgroundimage())
                    .set(MEDIA.SYNOPSIS, newMedia.getSynopsis())
                    .where(MEDIA.MEDIAID.eq(newMedia.getMediaid()))
                    .execute();

            if(table.getName().equals("books")){
                BookRequestHelper book = (BookRequestHelper) media;
                if(book.getCollection()!=null)
                    create.update(BOOKS)
                            .set(BOOKS.COLLECTION, book.getCollection())
                            .where(BOOKS.MEDIAID.eq(media.getMediaid()))
                            .execute();
            } else if(table.getName().equals("videogames")){
                VideogameRequestHelper videogame = (VideogameRequestHelper) media;
                if(videogame.getCompany()!=null)
                    create.update(VIDEOGAMES)
                            .set(VIDEOGAMES.COMPANY, videogame.getCompany())
                            .where(VIDEOGAMES.MEDIAID.eq(media.getMediaid()))
                            .execute();
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public MediaResponseHelper postMovie(MovieRequestHelper movie) throws SQLException {
        canBePost(movie);
        return postMedia(movie, MOVIES);
    }

    public void putMovie(MovieRequestHelper movie) throws SQLException {
        putMedia(movie, MOVIES, canBePut(movie, MOVIES));
    }

    public MediaResponseHelper postSeries(SeriesRequestHelper series) throws SQLException {
        canBePost(series);
        return postMedia(series, SERIES);
    }

    public void putSeries(SeriesRequestHelper series) throws SQLException {
        putMedia(series, SERIES, canBePut(series, SERIES));
    }

    public MediaResponseHelper postBook(BookRequestHelper book) throws SQLException {
        canBePost(book);
        return postMedia(book, BOOKS);
    }

    public void putBook(BookRequestHelper book) throws SQLException{
        putMedia(book, BOOKS, canBePut(book, BOOKS));
    }

    public MediaResponseHelper postVideogame(VideogameRequestHelper videogame) throws SQLException {
        canBePost(videogame);
        return postMedia(videogame, VIDEOGAMES);
    }

    public void putVideogame(VideogameRequestHelper videogame) throws SQLException{
        putMedia(videogame, VIDEOGAMES, canBePut(videogame, VIDEOGAMES));
    }

    public void canBePost(MediaRequestHelper media) throws SQLException {
        try(Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            List<Media> oldMedia = create.select()
                    .from(MEDIA)
                    .where(MEDIA.TITLE.eq(media.getTitle())
                            .and(MEDIA.RELEASEDATE.eq(media.getReleasedate())))
                    .fetchInto(Media.class);

            if(!oldMedia.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public Media canBePut(MediaRequestHelper media, Table table) throws SQLException {
        Media oldMedia = null;
        try(Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);

            List<Media> oldMediaList = create.select()
                    .from(MEDIA)
                    .naturalJoin(table)
                    .where(MEDIA.MEDIAID.eq(media.getMediaid()))
                    .fetchInto(Media.class);

            if (oldMediaList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            oldMedia = oldMediaList.get(0);

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
        return oldMedia;
    }

}
