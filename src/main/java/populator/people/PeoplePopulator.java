package populator.people;

import api.GlobalValues;
import api.helpers.enums.RoleType;
import api.helpers.request.PeopleMediaRequestHelper;
import api.helpers.request.PeopleRequestHelper;
import api.helpers.response.EpisodeResponseHelper;
import api.helpers.response.MediaResponseHelper;
import api.helpers.response.PeopleResponseHelper;
import api.services.MediaSpecificService;
import api.services.PeopleService;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.model.Credits;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import org.apache.commons.lang3.tuple.Pair;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static populator.Global.TMDB;
import static populator.Global.TMDB_IMAGE_URL;
import static src.main.java.model.Tables.*;

/**
 * PeoplePopulator
 * Project HarmonyAPI
 * Created: 2022-05-10
 *
 * @author juagallop1
 **/
public class PeoplePopulator {

    private static MediaSpecificService mediaSpecificService = new MediaSpecificService();
    private static PeopleService peopleService = new PeopleService();
    private static TmdbPeople peopleApi = TMDB.getPeople();
    private static List<People> allPeople;

    private static List<People> getAllPeople() throws SQLException {
        List<People> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            result = create.select()
                .from(PEOPLE)
                .fetchInto(People.class);
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return result;
    }

    private static PeopleMediaRequestHelper addPerson(Integer id, String name, String role, RoleType roleType) {
        var person = allPeople.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst();
        PeopleResponseHelper dbPerson = null;
        if (person.isPresent()) {
            dbPerson = new PeopleResponseHelper(
                person.get().getPersonid(),
                person.get().getName(),
                person.get().getBirthdate(),
                person.get().getPicture()
            );
        } else {
            var tmdbPerson = peopleApi.getPersonInfo(id, "en");
            try {
                dbPerson = peopleService.postPerson(
                    new PeopleRequestHelper(
                        1,
                        null,
                        tmdbPerson.getName(),
                        tmdbPerson.getBirthday().isEmpty() ? null : LocalDate.parse(tmdbPerson.getBirthday()),
                        tmdbPerson.getProfilePath().isEmpty() ? null : TMDB_IMAGE_URL + tmdbPerson.getProfilePath()
                    )
                );
            } catch (SQLException | DataAccessException e) {
                return null;
            }
        }
        return new PeopleMediaRequestHelper(
            1,
            dbPerson.getPersonid(),
            role,
            roleType
        );
    }

    private static List<PeopleMediaRequestHelper> addPerson(PeopleRequestHelper fetchedPerson, List<String> roles, RoleType roleType) {
        var person = allPeople.stream()
            .filter(p -> p.getName().equals(fetchedPerson.getName()))
            .findFirst();
        PeopleResponseHelper dbPerson = null;
        if (person.isPresent()) {
            dbPerson = new PeopleResponseHelper(
                person.get().getPersonid(),
                person.get().getName(),
                person.get().getBirthdate(),
                person.get().getPicture()
            );
        } else {
            try {
                dbPerson = peopleService.postPerson(fetchedPerson);
            } catch (SQLException | DataAccessException e) {
                return null;
            }
        }
        List<PeopleMediaRequestHelper> result = new ArrayList<>();
        if (dbPerson != null) {
            PeopleResponseHelper finalDbPerson = dbPerson;
            roles.forEach(
                role -> result.add(new PeopleMediaRequestHelper(
                    1,
                    finalDbPerson.getPersonid(),
                    role,
                    roleType
                ))
            );
        }
        return result;
    }

    private static PeopleMediaRequestHelper addPerson(PeopleRequestHelper fetchedPerson, String role, RoleType roleType) {
        var person = allPeople.stream()
            .filter(p -> p.getName().equals(fetchedPerson.getName()))
            .findFirst();
        PeopleResponseHelper dbPerson = null;
        if (person.isPresent()) {
            dbPerson = new PeopleResponseHelper(
                person.get().getPersonid(),
                person.get().getName(),
                person.get().getBirthdate(),
                person.get().getPicture()
            );
        } else {
            try {
                dbPerson = peopleService.postPerson(fetchedPerson);
            } catch (SQLException | DataAccessException e) {
                return null;
            }
        }
        if (dbPerson != null) {
            return new PeopleMediaRequestHelper(
                1,
                dbPerson.getPersonid(),
                role,
                roleType
            );
        }
        return null;
    }

    private static PeopleMediaRequestHelper addCast(PersonCast cast) {
        return addPerson(cast.getId(), cast.getName(), cast.getCharacter(), RoleType.CAST);
    }

    private static PeopleMediaRequestHelper addCrew(PersonCrew crew) {
        return addPerson(crew.getId(), crew.getName(), crew.getJob(), RoleType.CREW);
    }

    public static void addMoviePeople(Credits tmdbMovieCredits, MediaResponseHelper dbMovie) throws SQLException {
        allPeople = getAllPeople();
        Stream.concat(tmdbMovieCredits.getCast().stream(), tmdbMovieCredits.getCrew().stream())
            .map(p -> {
                if (p instanceof PersonCast) {
                    return addCast((PersonCast) p);
                } else if (p instanceof PersonCrew) {
                    return addCrew((PersonCrew) p);
                } else {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .forEach(person -> {
                try {
                    mediaSpecificService.addPerson(dbMovie.getMediaid(), person, MOVIES);
                }
                catch (DataAccessException | SQLException e) { }
            });
    }

    public static void addEpisodePeople(
        Integer seasonId,
        Integer episodeId,
        Credits tmdbEpisodeCredits,
        Credits tmdbSeasonCredits,
        Credits tmdbSeriesCredits,
        EpisodeResponseHelper dbEpisode
    ) throws SQLException {
        allPeople = getAllPeople();
        Stream.of(
            tmdbEpisodeCredits.getCast().stream(),
            tmdbEpisodeCredits.getCrew().stream(),
            tmdbSeasonCredits.getCast().stream(),
            tmdbSeasonCredits.getCrew().stream(),
            tmdbSeriesCredits.getCast().stream(),
            tmdbSeriesCredits.getCrew().stream()
        )
            .reduce(Stream::concat)
            .orElseGet(Stream::empty)
            .map(p -> {
                if (p instanceof PersonCast) {
                    return addCast((PersonCast) p);
                } else if (p instanceof PersonCrew) {
                    return addCrew((PersonCrew) p);
                } else {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .forEach(person -> {
                try {
                    mediaSpecificService.addPersonEpisode(dbEpisode.getMediaid(), seasonId, episodeId, person);
                }
                catch (DataAccessException | SQLException e) { }
            });
    }

    public static void addVideogamePeople(Integer videogameId, List<Pair<PeopleRequestHelper, List<String>>> people) throws SQLException {
        allPeople = getAllPeople();
        people.stream()
            .flatMap(pair -> addPerson(pair.getKey(), pair.getValue(), RoleType.CREW).stream())
            .filter(Objects::nonNull)
            .forEach(person -> {
                try {
                    mediaSpecificService.addPerson(videogameId, person, VIDEOGAMES);
                }
                catch (DataAccessException | SQLException e) { }
            });
    }

    public static void addBookPeople(Integer bookId, List<String> people) throws SQLException {
        allPeople = getAllPeople();
        people.stream()
            .map(p -> addPerson(
                new PeopleRequestHelper(
                    1,
                    null,
                    p,
                    null,
                    null
                ),
                "Author",
                RoleType.CREW
            ))
            .filter(Objects::nonNull)
            .forEach(person -> {
                try {
                    mediaSpecificService.addPerson(bookId, person, BOOKS);
                }
                catch (DataAccessException | SQLException e) { }
            });
    }
}
