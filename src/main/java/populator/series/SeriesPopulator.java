package populator.series;

import database.DatabaseConnection;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static populator.Global.gunzip;
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
    private static List<FetchedSeries> fetchIds() throws IOException, ParseException {
        var date = LocalDate.now().minusDays(2);
        var gzFile = new File(String.format("files/tv_series_ids_%02d_%02d_%s.json.gz", date.getDayOfMonth(), date.getMonthValue(), date.getYear()));
        var jsonFile = new File(gzFile.getPath().replace(".gz", ""));

        if(!jsonFile.exists()) {
            var url = new URL("https://files.tmdb.org/p/exports/" + gzFile.getName());
            try (var in = url.openStream()) {
                Files.copy(in, gzFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            gunzip(gzFile.getPath());
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
            System.out.println(" -> Error while reading file " + jsonFile.getPath() + ": " + e.getMessage());
        }

        return series;
    }

    private static List<Media> getAll() throws SQLException {
        List<Media> result = new ArrayList<>();
        try {
            var create = DatabaseConnection.create();
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



    public static void populate() throws IOException, ParseException, SQLException {
        System.out.println("Populating series...");
        var ids = getAll()
            .stream()
            .map(Media::getExternalid)
            .toList();
        var movies = fetchIds().stream()
            .filter(movie -> movie.id() != null && !ids.contains(movie.id()))
            .sorted((m1, m2) -> m2.popularity().compareTo(m1.popularity()))
            .toList();
//        add(movies);
    }
}
