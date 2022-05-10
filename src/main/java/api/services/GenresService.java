package api.services;

import api.helpers.request.GenreRequestHelper;
import api.helpers.response.GenreResponseHelper;
import database.DatabaseConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Genres;

import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.GENRES;

@Service
public class GenresService {

    public List<Genres>getAllGenres(){
        List<Genres> genresList=null;

        try {
            var create = DatabaseConnection.create();
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
        try {
            var create = DatabaseConnection.create();

            Routines.newgenre(create.configuration(), genre.getName());

            newGenre = new GenreResponseHelper(create.select()
                    .from(GENRES)
                    .orderBy(GENRES.GENREID.desc())
                    .fetch()
                    .get(0));

        } catch (ResponseStatusException | SQLException e) {
        if (e instanceof ResponseStatusException) {
            throw e;
        }
        e.printStackTrace();
    }
        return newGenre;
    }
}
