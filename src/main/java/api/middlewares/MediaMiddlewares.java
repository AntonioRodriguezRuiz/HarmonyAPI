package api.middlewares;

import api.GlobalValues;
import api.helpers.request.MediaRequestHelper;
import api.helpers.request.PeopleMediaRequestHelper;
import api.services.MediaSpecificService;
import database.DatabaseConnection;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.*;

/**
 * MediaMiddlewares
 * Project HarmonyAPI
 * Created: 2022-05-07
 *
 * @author juagallop1
 **/
public class MediaMiddlewares {
    public static void mediaExists(Integer mediaId) throws SQLException {
        if(mediaId==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MediaId cannot be null");
        }
        try {
            var create = DatabaseConnection.create();
            if (create.select()
                .from(MEDIA)
                .where(MEDIA.MEDIAID.eq(mediaId))
                .fetch()
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Media does not exist");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static Media mediaExists(Integer mediaId, Table table) throws SQLException {
        Media oldMedia = null;
        try(Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            List<Media> oldMediaList = create.select()
                    .from(MEDIA)
                    .naturalJoin(table)
                    .where(MEDIA.MEDIAID.eq(mediaId))
                    .fetchInto(Media.class);

            if (oldMediaList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Media does not exists or is not of the type specified");
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

    public static void MediaDoesNotExist(MediaRequestHelper media) throws SQLException {
        try(Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            List<Media> oldMedia = create.select()
                    .from(MEDIA)
                    .where(MEDIA.TITLE.eq(media.getTitle())
                            .and(MEDIA.RELEASEDATE.eq(media.getReleasedate())))
                    .fetchInto(Media.class);

            if(!oldMedia.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This media already exists in the database");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void hasGenre(Integer id, Integer genreid) throws SQLException {
        try {
            var create = DatabaseConnection.create();
            if (create.select()
                    .from(MEDIA)
                    .naturalJoin(MEDIAGENRES)
                    .where(MEDIA.MEDIAID.eq(id)
                            .and(MEDIAGENRES.GENREID.eq(genreid)))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Media does not have the specified genre");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void doesNotHaveGenre(Integer id, Integer genreid) throws SQLException {
        try {
            var create = DatabaseConnection.create();
            if (!create.select()
                    .from(MEDIA)
                    .naturalJoin(MEDIAGENRES)
                    .where(MEDIA.MEDIAID.eq(id)
                            .and(MEDIAGENRES.GENREID.eq(genreid)))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Media already has the specified genre");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void personNotInMedia(Integer id, PeopleMediaRequestHelper person, Table table) throws SQLException {
        Result<Record> peopleList = null;
        try {
            var create = DatabaseConnection.create();

            MediaSpecificService mediaSpecificService = new MediaSpecificService();
            Table peopleTable = mediaSpecificService.getPeopleTable(table);

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

            if(!peopleList.isEmpty())
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This person already has this role and roleType in this media");

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void personInMedia(Integer id, PeopleMediaRequestHelper person, Table table) throws SQLException {
        Result<Record> peopleList = null;
        try {
            var create = DatabaseConnection.create();

            MediaSpecificService mediaSpecificService = new MediaSpecificService();
            Table peopleTable = mediaSpecificService.getPeopleTable(table);

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

            if(peopleList.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not participate in this media with this role and roleType");

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void doesNotHaveReview(Integer mediaid, Integer userid) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            Result<Record> reviewList = create.select()
                    .from(REVIEWS)
                    .naturalJoin(MEDIA)
                    .where(MEDIA.MEDIAID.eq(mediaid)
                            .and(REVIEWS.USERID.eq(userid)))
                    .fetch();

            if(!reviewList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This person already has a review in this media");
            }


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void hasReview(Integer mediaid, Integer reviewid) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            Result<Record> reviewList = create.select()
                    .from(REVIEWS)
                    .naturalJoin(MEDIA)
                    .where(MEDIA.MEDIAID.eq(mediaid)
                            .and(REVIEWS.REVIEWID.eq(reviewid)))
                    .fetch();

            if(reviewList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review does not belong to this media");
            }


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
