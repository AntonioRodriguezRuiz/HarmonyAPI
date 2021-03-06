package populator.movies;

import api.GlobalValues;
import api.helpers.request.MovieRequestHelper;
import api.services.MediaService;
import info.movito.themoviedbapi.TmdbMovies;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.springframework.web.server.ResponseStatusException;
import populator.Global;
import populator.genres.GenrePopulator;
import populator.people.PeoplePopulator;
import src.main.java.model.tables.pojos.Media;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static populator.Global.TMDB;
import static populator.Global.TMDB_IMAGE_URL;
import static src.main.java.model.Tables.MEDIA;
import static src.main.java.model.Tables.MOVIES;

/**
 * MoviePopulator
 * Project HarmonyAPI
 * Created: 2022-05-05
 *
 * @author juagallop1
 **/
public class MoviePopulator {

    private static MediaService mediaService = new MediaService();
    private static TmdbMovies moviesApi = TMDB.getMovies();
    public static final ProgressBarBuilder pbb = new ProgressBarBuilder()
        .setTaskName("Populating movies...")
        .setStyle(ProgressBarStyle.COLORFUL_UNICODE_BLOCK)
        .setUpdateIntervalMillis(100)
        .setMaxRenderedLength(100)
        .setUnit(" movies", 1);

    private static List<FetchedMovie> fetchIds() throws IOException, ParseException {
        var gzFile = new File("files/movie_ids.json.gz");
        var jsonFile = new File(gzFile.getPath().replace(".gz", ""));

        if(!jsonFile.exists()) {
            var i = 0;
            var date = LocalDate.now();
            while (!gzFile.exists()) {
                date = LocalDate.now().minusDays(i);
                var url = new URL(
                    "https://files.tmdb.org/p/exports/" +
                        String.format("movie_ids_%02d_%02d_%s.json.gz", date.getDayOfMonth(), date.getMonthValue(), date.getYear())
                );
                try (var in = url.openStream()) {
                    Files.copy(in, gzFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    i++;
                    System.out.println("Could not download file for date " + date);
                }
            }
            Global.gunzip(gzFile.getPath());
            Files.delete(gzFile.toPath());
        }

        List<FetchedMovie> movies = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(jsonFile))) {
            String str;
            JSONParser parser = new JSONParser();
            while ((str = br.readLine()) != null) {
                var js = (JSONObject) parser.parse(str);
                movies.add(
                    new FetchedMovie(
                        Math.toIntExact((Long) js.get("id")),
                        (Boolean) js.get("adult"),
                        (String) js.get("original_title"),
                        (Double) js.get("popularity"),
                        (Boolean) js.get("video")
                    )
                );
            }
        }
        catch (IOException e) {
            System.out.println("Error while reading file " + jsonFile.getPath() + ": " + e.getMessage());
        }
        return movies;
    }

    private static List<Media> getAll() throws SQLException {
        List<Media> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            result = create.select()
                .from(MEDIA)
                .naturalJoin(MOVIES)
                .fetchInto(Media.class);
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return result;
    }

    private static void add(List<FetchedMovie> movies) throws SQLException {
        for (var fetchedMovie : ProgressBar.wrap(movies, pbb)) {
            var tmdbMovie = moviesApi.getMovie(fetchedMovie.id(), "en", TmdbMovies.MovieMethod.credits);
            var mrh = new MovieRequestHelper(
                1,
                null,
                tmdbMovie.getTitle(),
                tmdbMovie.getReleaseDate().isEmpty() ? "1901-01-01" : tmdbMovie.getReleaseDate(),
                tmdbMovie.getPosterPath() == null ? null : TMDB_IMAGE_URL + tmdbMovie.getPosterPath(),
                tmdbMovie.getBackdropPath() == null ? null : TMDB_IMAGE_URL + tmdbMovie.getBackdropPath(),
                tmdbMovie.getOverview(),
                tmdbMovie.getId()
            );
            var dbMovie = mediaService.postMovie(mrh);
            GenrePopulator.addGenresTMDB(tmdbMovie.getGenres(), dbMovie);
            PeoplePopulator.addMoviePeople(tmdbMovie.getCredits(), dbMovie);
        }
    }

    public static void populate(Integer limit) throws IOException, ParseException, SQLException {
        var ids = getAll()
            .stream()
            .map(Media::getExternalid)
            .toList();
        var movies = fetchIds().stream()
            .filter(movie -> movie.id() != null && !ids.contains(movie.id()))
            .sorted((m1, m2) -> m2.popularity().compareTo(m1.popularity()))
            .limit(limit)
            .toList();
        add(movies);
    }
}
