package api.middlewares;

import api.GlobalValues;
import api.helpers.request.PeopleMediaRequestHelper;
import api.services.MediaSpecificService;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.*;
import static src.main.java.model.tables.Series.SERIES;

public class EpisodeMiddlewares {

    public static void episodeExists(Integer episodeId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (create.select()
                    .from(EPISODES)
                    .where(EPISODES.EPISODEID.eq(episodeId))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Episode does not exist");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void episodeDoesNotExists(Integer seasonId, Integer episodeNo) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (!create.select()
                    .from(EPISODES)
                    .where(EPISODES.SEASONID.eq(seasonId)
                            .and(EPISODES.EPISODENO.eq(episodeNo)))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Episode already exist");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void isEpisodeOf(Integer seasonid, Integer episodeid) throws SQLException {
        if(seasonid==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seasonid cannot be null");
        }
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            if (create.select()
                    .from(SEASONS)
                    .naturalJoin(EPISODES)
                    .where(SEASONS.SEASONID.eq(seasonid))
                    .and(EPISODES.EPISODEID.eq(episodeid))
                    .fetch()
                    .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Episode is not of this season");
            }
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void personNotInEpisode(Integer episodeid, PeopleMediaRequestHelper person) throws SQLException {
        Result<Record> peopleList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            peopleList = create.select()
                    .from(PEOPLE)
                    .naturalJoin(PEOPLEEPISODES)
                    .naturalJoin(EPISODES)
                    .where(PEOPLEEPISODES.EPISODEID.eq(episodeid)
                            .and(PEOPLE.PERSONID.eq(person.getPersonid()))
                            .and(PEOPLEEPISODES.ROLE.eq(person.getRole())
                                    .and(PEOPLEEPISODES.ROLETYPE.eq(person.getRoleTypeByte()))))
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

    public static void persoInEpisode(Integer episodeid, PeopleMediaRequestHelper person) throws SQLException {
        Result<Record> peopleList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            peopleList = create.select()
                    .from(PEOPLE)
                    .naturalJoin(PEOPLEEPISODES)
                    .naturalJoin(EPISODES)
                    .where(PEOPLEEPISODES.EPISODEID.eq(episodeid)
                            .and(PEOPLE.PERSONID.eq(person.getPersonid()))
                            .and(PEOPLEEPISODES.ROLE.eq(person.getRole())
                                    .and(PEOPLEEPISODES.ROLETYPE.eq(person.getRoleTypeByte()))))
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
