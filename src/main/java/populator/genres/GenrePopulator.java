package populator.genres;
import api.helpers.request.GenreRequestHelper;
import api.helpers.response.MediaResponseHelper;
import api.services.GenresService;
import api.services.MediaSpecificService;
import info.movito.themoviedbapi.model.MovieDb;

import java.sql.SQLException;

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

    private static Integer addGenre(String genreName) throws SQLException {
        var genre = genresService.getAllGenres().stream()
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

    public static void addMovieGenres(MovieDb tmdbMovie, MediaResponseHelper dbMovie) {
        tmdbMovie.getGenres().stream()
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
