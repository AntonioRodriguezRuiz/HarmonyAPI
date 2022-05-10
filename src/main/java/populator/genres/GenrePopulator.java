package populator.genres;

import api.helpers.request.GenreRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.services.GenresService;
import api.services.MediaSpecificService;
import info.movito.themoviedbapi.model.Genre;
import src.main.java.model.tables.pojos.Genres;

import java.sql.SQLException;
import java.util.List;

/**
 * GenrePopulator
 * Project HarmonyAPI
 * Created: 2022-05-08
 *
 * @author juagallop1
 **/
public class GenrePopulator {
    private static GenresService genresService = new GenresService();
    private static MediaSpecificService mediaSpecificService = new MediaSpecificService();
    private static List<Genres> allGenres;

    private static Integer addGenre(String genreName) throws SQLException {
        var genre = allGenres.stream()
            .filter(g -> g.getName().equals(genreName.toLowerCase()))
            .findFirst();
        if (genre.isPresent()) {
            return genre.get().getGenreid();
        } else {
            return genresService.postGenre(
                new GenreRequestHelper(null, null, genreName.toLowerCase())
            ).getGenreid();
        }
    }

    public static void addGenresTMDB(List<Genre> genres, MediaResponseHelper dbMovie) {
        allGenres = genresService.getAllGenres();
         genres.stream()
            .map(genre -> {
                try {
                    return addGenre(genre.getName());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            })
            .forEach(genreId -> {
                try {
                    mediaSpecificService.addGenre(dbMovie.getMediaid(), new GenreRequestHelper(null, genreId, null));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }
}
