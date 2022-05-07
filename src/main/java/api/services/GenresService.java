package api.services;

import api.GlobalValues;
import api.helpers.request.*;
import api.helpers.response.GenreResponseHelper;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Genres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.GENRES;
import static src.main.java.model.Tables.MEDIA;
import src.main.java.model.Routines;

@Service
public class GenresService {

    public List<Genres>getAllGenres(){
        List<Genres> genresList=null;

        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            genresList=create.select()
                            .from(GENRES)
                            .fetchInto(Genres.class);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return genresList;
    }

    public GenreResponseHelper postGenre(GenreRequestHelper genre) throws SQLException {
        GenreResponseHelper newGenre=null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Routines.newgenre(create.configuration(), genre.getName());

            Integer newGenreID=create.select().from(GENRES).orderBy(GENRES.GENREID.desc()).limit(1).fetch().
                    get(0).get(GENRES.GENREID);

            GenresSpecificService genresSpecificService = new GenresSpecificService();
            newGenre = genresSpecificService.getGenre(newGenreID);

        } catch (ResponseStatusException | SQLException e) {
        if (e instanceof ResponseStatusException) {
            throw e;
        }
        e.printStackTrace();
    }
        return newGenre;
    }
}
