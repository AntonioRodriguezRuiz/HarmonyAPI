package populator.series;

import api.GlobalValues;
import api.helpers.request.EpisodeRequestHelper;
import api.helpers.request.SeasonRequestHelper;
import api.helpers.request.SeriesRequestHelper;
import api.services.MediaService;
import api.services.MediaSpecificService;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.TmdbTvEpisodes;
import info.movito.themoviedbapi.TmdbTvSeasons;
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
import static src.main.java.model.Tables.SERIES;

/**
 * SeriesPopulator
 * Project HarmonyAPI
 * Created: 2022-05-07
 *
 * @author juagallop1
 **/
public class SeriesPopulator {

    private static final MediaService mediaService = new MediaService();
    private static final MediaSpecificService mediaSpecificService = new MediaSpecificService();
    private static final TmdbTV seriesApi = TMDB.getTvSeries();
    private static final TmdbTvSeasons seasonApi = TMDB.getTvSeasons();
    private static final TmdbTvEpisodes episodeApi = TMDB.getTvEpisodes();
    public static final ProgressBarBuilder pbb = new ProgressBarBuilder()
        .setTaskName("Populating series...")
        .setStyle(ProgressBarStyle.COLORFUL_UNICODE_BLOCK)
        .setUpdateIntervalMillis(100)
        .setMaxRenderedLength(100)
        .setUnit(" series", 1);

    private static List<FetchedSeries> fetchIds() throws IOException, ParseException {
        var gzFile = new File("files/tv_series_ids.json.gz");
        var jsonFile = new File(gzFile.getPath().replace(".gz", ""));

        if(!jsonFile.exists()) {
            var i = 0;
            var date = LocalDate.now();
            while (!gzFile.exists()) {
                date = LocalDate.now().minusDays(i);
                var url = new URL(
                    "https://files.tmdb.org/p/exports/" +
                        String.format("tv_series_ids_%02d_%02d_%s.json.gz", date.getDayOfMonth(), date.getMonthValue(), date.getYear())
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

        List<FetchedSeries> series = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(jsonFile))) {
            String str;
            JSONParser parser = new JSONParser();
            while ((str = br.readLine()) != null) {
                var js = (JSONObject) parser.parse(str);
                series.add(
                    new FetchedSeries(
                        Math.toIntExact((Long) js.get("id")),
                        (String) js.get("original_name"),
                        (Double) js.get("popularity")
                    )
                );
            }
        }
        catch (IOException e) {
            System.out.println("Error while reading file " + jsonFile.getPath() + ": " + e.getMessage());
        }

        return series;
    }

    private static List<Media> getAll() throws SQLException {
        List<Media> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            result = create.select(MEDIA.fields())
                .from(MEDIA)
                .naturalJoin(SERIES)
                .fetchInto(Media.class);
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return result;
    }

    private static void add(List<FetchedSeries> series) throws SQLException {
        for (var serie : ProgressBar.wrap(series, pbb)) {
            var tmdbSerie = seriesApi.getSeries(serie.id(), "en", TmdbTV.TvMethod.credits);
            var tmdbSerieCredits = tmdbSerie.getCredits();
            var srh = new SeriesRequestHelper(
                1,
                null,
                tmdbSerie.getName(),
                tmdbSerie.getFirstAirDate().isEmpty() ? "1901-01-01" : tmdbSerie.getFirstAirDate(),
                tmdbSerie.getPosterPath() == null ? null : TMDB_IMAGE_URL + tmdbSerie.getPosterPath(),
                tmdbSerie.getBackdropPath() == null ? null : TMDB_IMAGE_URL + tmdbSerie.getBackdropPath(),
                tmdbSerie.getOverview(),
                tmdbSerie.getId()
            );
            var dbSerie = mediaService.postSeries(srh);
            GenrePopulator.addGenresTMDB(tmdbSerie.getGenres(), dbSerie);
            for (var season : tmdbSerie.getSeasons()) {
                var tmdbSeason = seasonApi.getSeason(
                    serie.id(),
                    season.getSeasonNumber(),
                    "en",
                    TmdbTvSeasons.SeasonMethod.credits
                );
                var tmdbSeasonCredits = tmdbSeason.getCredits();
                var ssrh = new SeasonRequestHelper(
                    1,
                    dbSerie.getMediaid(),
                    null,
                    tmdbSeason.getSeasonNumber(),
                    tmdbSeason.getEpisodes().size()
                );
                var dbSeason = mediaSpecificService.postSeason(dbSerie.getMediaid(), ssrh);
                for (var episode : tmdbSeason.getEpisodes()) {
                    var tmdbEpisode = episodeApi.getEpisode(
                        serie.id(),
                        season.getSeasonNumber(),
                        episode.getEpisodeNumber(),
                        "en",
                        TmdbTvEpisodes.EpisodeMethod.credits
                    );
                    var erh = new EpisodeRequestHelper(
                        1,
                        null,
                        tmdbEpisode.getEpisodeNumber(),
                        tmdbEpisode.getName()
                    );
                    var dbEpisode = mediaSpecificService.postEpisode(
                        dbSerie.getMediaid(),
                        dbSeason.getSeasonid(),
                        erh
                    );
                    PeoplePopulator.addEpisodePeople(
                        dbSeason.getSeasonid(),
                        dbEpisode.getEpisodeid(),
                        tmdbEpisode.getCredits(),
                        tmdbSeasonCredits,
                        tmdbSerieCredits,
                        dbEpisode
                    );
                }
            }
        }
    }

    public static void populate(Integer limit) throws IOException, ParseException, SQLException {
        var ids = getAll()
            .stream()
            .map(Media::getExternalid)
            .toList();
        var series = fetchIds().stream()
            .filter(movie -> movie.id() != null && !ids.contains(movie.id()))
            .sorted((m1, m2) -> m2.popularity().compareTo(m1.popularity()))
            .limit(limit)
            .toList();
        add(series);
    }
}
