package populator.people;

import api.helpers.enums.RoleType;
import api.helpers.request.PeopleMediaRequestHelper;
import api.helpers.request.PeopleRequestHelper;
import api.helpers.response.PeopleResponseHelper;
import api.services.MediaSpecificService;
import api.services.PeopleService;
import database.DatabaseConnection;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import org.jooq.exception.DataAccessException;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.People;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static populator.Global.TMDB;
import static populator.Global.TMDB_IMAGE_URL;
import static src.main.java.model.Tables.MOVIES;
import static src.main.java.model.Tables.PEOPLE;

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
        try {
            var create = DatabaseConnection.create();
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

    private static PeopleMediaRequestHelper addPerson(Integer id, String name, String role) {
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
                        tmdbPerson.getProfilePath() == null ? null : TMDB_IMAGE_URL + tmdbPerson.getProfilePath()
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
            RoleType.CAST
        );
    }

    private static PeopleMediaRequestHelper addCast(PersonCast cast) {
        return addPerson(cast.getId(), cast.getName(), cast.getCharacter());
    }

    private static PeopleMediaRequestHelper addCrew(PersonCrew crew) {
        return addPerson(crew.getId(), crew.getName(), crew.getJob());
    }

    public static void addMoviePeople(MovieDb tmdbMovie) throws SQLException {
        allPeople = getAllPeople();
        var credits = tmdbMovie.getCredits();
        Stream.concat(credits.getCast().stream(), credits.getCrew().stream())
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
                    mediaSpecificService.addPerson(1, person, MOVIES);
                }
                catch (DataAccessException | SQLException e) { }
            });
    }
}
