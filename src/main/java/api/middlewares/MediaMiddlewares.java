package api.middlewares;

import api.GlobalValues;
import api.helpers.request.PeopleMediaRequestHelper;
import api.services.MediaSpecificService;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.standard.Media;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.*;
import static src.main.java.model.Tables.PEOPLE;

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
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
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

    public static void hasGenre(Integer id, Integer genreid) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
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
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
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
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

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

    public static void persoInMedia(Integer id, PeopleMediaRequestHelper person, Table table) throws SQLException {
        Result<Record> peopleList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

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
}
